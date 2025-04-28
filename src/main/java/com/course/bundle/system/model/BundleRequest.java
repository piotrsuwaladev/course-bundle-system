package com.course.bundle.system.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.Map;

@Data
public class BundleRequest {

    @NotEmpty(message = "Topics map must not be empty.")
    private Map<String, Integer> topics;
}
