package com.course.bundle.system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.course.bundle.system.model.ProviderTopicsConfig;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ProviderConfigLoader {

    public ProviderTopicsConfig loadConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("providers.json");
        return mapper.readValue(inputStream, ProviderTopicsConfig.class);
    }
}
