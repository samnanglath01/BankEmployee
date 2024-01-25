package com.ha.java.Bmicroservice.controller;

import com.ha.java.Bmicroservice.entity.Course;
import com.ha.java.Bmicroservice.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    @GetMapping("{id}")
    public ResponseEntity<Course> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @GetMapping("String")
    public String getString() {
        return "Hello from B";
    }







}
