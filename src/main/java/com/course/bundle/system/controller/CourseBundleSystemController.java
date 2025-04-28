package com.course.bundle.system.controller;

import com.course.bundle.system.model.BundleRequest;
import com.course.bundle.system.model.BundleResponse;
import com.course.bundle.system.service.CourseBundleSystemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/bundle")
@RequiredArgsConstructor
public class CourseBundleSystemController {

    private final CourseBundleSystemService courseBundleSystemService;

    @PostMapping
    public ResponseEntity<List<BundleResponse>> getBundle(@Valid @RequestBody BundleRequest request) throws Exception {
        return ResponseEntity.ok(courseBundleSystemService.generateBundle(request));
    }
}
