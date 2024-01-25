package com.ha.student.controller;

import com.ha.student.entity.Student;
import com.ha.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @GetMapping
    public ResponseEntity<?> findAllStudent() {
        return ResponseEntity.ok(studentService.findAllStudent());
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<Student>> findStudents(@PathVariable long schoolId) {
        return ResponseEntity.ok(studentService.findStudents(schoolId));
    }


}
