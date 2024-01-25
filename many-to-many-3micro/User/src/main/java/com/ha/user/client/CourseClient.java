package com.ha.testing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course-service",
                url = "http://localhost:8020/course")
public interface CourseClient {

        @GetMapping("{id}")
        Course get(@PathVariable("id") Long id);

}
