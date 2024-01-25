package com.ha.java.controller;

import com.ha.java.dto.StudentWithCourse;
import com.ha.java.dto.StudentsDto;
import com.ha.java.entity.Student;
import com.ha.java.mapper.StudentMapper;
import com.ha.java.service.CourseService;
import com.ha.java.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentsDto> createStudent(@RequestBody StudentsDto studentsDto) {
        Student student = StudentMapper.INSTANCE.toStudent(studentsDto);
        StudentsDto studentDto = StudentMapper.INSTANCE.toStudentsDto(studentService.saveStudent(student));
        return ResponseEntity.ok(studentDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentWithCourse> getStudentById(@PathVariable long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentsDto>> getAllStudent() {

        List<Student> students = studentService.getAllStudent();
        return ResponseEntity.ok(StudentMapper.INSTANCE.toStudentDtos(students));
    }

    @GetMapping("find/{name}")
    public ResponseEntity<List<StudentsDto>> getStudentByName(@PathVariable String name) {
        List<Student> students = studentService.getStudentByName(name);
        return ResponseEntity.ok(StudentMapper.INSTANCE.toStudentDtos(students));
    }

    //add course
    @PutMapping("{studentId}/add-course/{courseId}")
    public ResponseEntity<Student> addCourse(@PathVariable long studentId, @PathVariable long courseId){
        return ResponseEntity.ok(studentService.addCourse(studentId, courseId));
    }



}
