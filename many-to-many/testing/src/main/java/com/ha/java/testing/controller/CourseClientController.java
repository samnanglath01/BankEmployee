package com.ha.java.testing.controller;

import com.ha.java.testing.client.ClientToken;
import com.ha.java.testing.client.CourseClient;
import com.ha.java.testing.dto.CourseDto;
import com.ha.java.testing.dto.CourseWithStudents;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course-client")
@RequiredArgsConstructor
public class CourseClientController {

    private final CourseClient courseClient;

    @PostMapping
    public ResponseEntity<CourseDto> createStudent(@RequestBody CourseDto courseDto, @RequestHeader("Authorization") String token) {
        ClientToken.setToken(token);
        return ResponseEntity.ok(courseClient.createCourse(courseDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseWithStudents> getCourseById(@PathVariable long id, @RequestHeader("Authorization") String token) {
        ClientToken.setToken(token);
        return ResponseEntity.ok(courseClient.getCourseById(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllStudent(@RequestHeader("Authorization") String token) {
        ClientToken.setToken(token);
        return ResponseEntity.ok(courseClient.getAllCourse());
    }

    @GetMapping("student/{studentId}")
    public ResponseEntity<List<CourseDto>> getCourseByStudentId(@PathVariable long studentId, @RequestHeader("Authorization") String token) {
        ClientToken.setToken(token);
        return ResponseEntity.ok(courseClient.getCourseByStudentId(studentId));
    }

}
