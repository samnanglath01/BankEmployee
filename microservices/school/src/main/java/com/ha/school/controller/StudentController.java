package com.ha.school.controller;

import com.ha.school.entity.FullSchoolResponse;
import com.ha.school.entity.School;
import com.ha.school.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class StudentController {
    private final SchoolService schoolService;

    public StudentController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<?> saveStudent(@RequestBody School school) {
        return ResponseEntity.ok(schoolService.saveSchool(school));
    }

    @GetMapping("{schoolId}")
    public ResponseEntity<?> findSchoolById(@PathVariable long schoolId) {
        return ResponseEntity.ok(schoolService.findSchoolById(schoolId));
    }

    @GetMapping
    public ResponseEntity<?> findAllSchool() {
        return ResponseEntity.ok(schoolService.findAllSchool());
    }

    @GetMapping("/with-student/{schoolId}")
    public ResponseEntity<FullSchoolResponse> findSchoolWithStudents(@PathVariable long schoolId) {
        return ResponseEntity.ok(schoolService.findSchoolWithStudents(schoolId));
    }


}
