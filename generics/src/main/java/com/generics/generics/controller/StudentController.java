package com.generics.generics.controller;

import com.generics.generics.entity.Student;
import com.generics.generics.service.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> save(
//                                        @RequestParam("firstName") String firstName,
//                                        @RequestParam("lastName") String lastName,
                                        @RequestParam Map<String, String> map,
                                        @RequestParam("file") MultipartFile file) throws IOException {

        Student student = new Student(map.get("firstName"), map.get("lastName"), file.getBytes());

        return ResponseEntity.ok(studentService.save(student));
    }


    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @DeleteMapping
    public ResponseEntity<String> delete(Student employee) {
        return ResponseEntity.ok(studentService.delete(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        return ResponseEntity.ok(studentService.deleteById(id));
    }


    @GetMapping("/photo/{firstName}")
    public ResponseEntity<?> getPhoto(@PathVariable("firstName") String firstName) {

//        byte[] image = studentService.getPhoto(firstName);
//        HttpHeaders headers = new HttpHeaders();

        Student student = studentService.getStudent(firstName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(student.getImage());
    }

}
