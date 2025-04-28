package com.course.bundle.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BundleResponse {

    private String providerName;
    private double bundleValue;
}
