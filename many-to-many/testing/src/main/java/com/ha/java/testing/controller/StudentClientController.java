package com.ha.java.testing.controller;

import com.ha.java.testing.client.ClientToken;
import com.ha.java.testing.client.StudentClient;
import com.ha.java.testing.dto.StudentDto;
import com.ha.java.testing.dto.StudentWithCourse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student-client")
@RequiredArgsConstructor
public class StudentClientController {

    private final StudentClient studentClient;

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto student, @RequestHeader("Authorization") String token) {
        ClientToken.setToken(token);
        return ResponseEntity.ok(studentClient.createStudent(student));
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentWithCourse> getStudentById(@PathVariable long id, @RequestHeader("Authorization") String token) {
        ClientToken.setToken(token);
        return ResponseEntity.ok(studentClient.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudent(@RequestHeader("Authorization") String token) {
        ClientToken.setToken(token);
        return ResponseEntity.ok(studentClient.getAllStudent());
    }

    @GetMapping("find/{name}")
    public ResponseEntity<List<StudentDto>> getStudentByName(@PathVariable String name, @RequestHeader("Authorization") String token) {
        ClientToken.setToken(token);
        return ResponseEntity.ok(studentClient.getStudentByName(name));
    }

    //add course
    @PutMapping("{studentId}/add-course/{courseId}")
    public ResponseEntity<StudentDto> addCourse(@PathVariable long studentId, @PathVariable long courseId, @RequestHeader("Authorization") String token){
        ClientToken.setToken(token);
        return ResponseEntity.ok(studentClient.addCourse(studentId, courseId));
    }

}
