package com.course.bundle.system.controller;

import com.course.bundle.system.model.BundleRequest;
import com.course.bundle.system.model.BundleResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CourseBundleSystemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnOkWhenValidRequestIsSent() throws Exception {
        BundleRequest request = new BundleRequest();
        request.setTopics(Map.of(
                "math", 50,
                "science", 30,
                "reading", 20,
                "history", 15,
                "art", 10
        ));

        mockMvc.perform(post("/api/bundle")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenEmptyTopicsAreSent() throws Exception {
        BundleRequest request = new BundleRequest();
        request.setTopics(Map.of()); // empty topics

        mockMvc.perform(post("/api/bundle")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnCorrectBundle() throws Exception {
        BundleRequest request = new BundleRequest();
        request.setTopics(Map.of(
                "reading", 20,
                "math", 50,
                "science", 30,
                "history", 15,
                "art", 10
        ));

        String jsonRequest = objectMapper.writeValueAsString(request);

        String jsonResponse = mockMvc.perform(post("/api/bundle")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<BundleResponse> result = objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });

        assertThat(result)
                .extracting(BundleResponse::getProviderName, BundleResponse::getBundleValue)
                .containsExactlyInAnyOrder(
                        org.assertj.core.groups.Tuple.tuple("provider_a", 8.0),
                        org.assertj.core.groups.Tuple.tuple("provider_b", 5.0),
                        org.assertj.core.groups.Tuple.tuple("provider_c", 10.0)
                );
    }
}
