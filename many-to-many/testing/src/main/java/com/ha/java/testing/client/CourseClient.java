package com.ha.java.testing.client;

import com.ha.java.testing.dto.CourseDto;
import com.ha.java.testing.dto.CourseWithStudents;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "course-client", url = "http://localhost:8080/course",
    configuration = FeignConfig.class)
public interface CourseClient {

    @PostMapping
    CourseDto createCourse(@RequestBody CourseDto courseDto);

    @GetMapping("{id}")
    CourseWithStudents getCourseById(@PathVariable long id);

    @GetMapping
    List<CourseDto> getAllCourse();

    @GetMapping("student/{studentId}")
    List<CourseDto> getCourseByStudentId(@PathVariable long studentId);


}
