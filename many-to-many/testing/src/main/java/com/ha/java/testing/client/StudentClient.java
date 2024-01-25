package com.ha.java.testing.client;

import com.ha.java.testing.dto.StudentDto;
import com.ha.java.testing.dto.StudentWithCourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "student-client", url = "http://localhost:8080/student",
    configuration = FeignConfig.class)
public interface StudentClient {

    @PostMapping
    StudentDto createStudent(@RequestBody StudentDto student);

    @GetMapping("{id}")
    StudentWithCourse getStudentById(@PathVariable long id);

    @GetMapping
    List<StudentDto> getAllStudent();

    @GetMapping("find/{name}")
    List<StudentDto> getStudentByName(@PathVariable String name);

    //add course
    @PutMapping("{studentId}/add-course/{courseId}")
    StudentDto addCourse(@PathVariable long studentId, @PathVariable long courseId);

}
