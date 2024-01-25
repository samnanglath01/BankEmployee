package com.ha.java.controller;

import com.ha.java.dto.CourseDto;
import com.ha.java.dto.CourseWithStudents;
import com.ha.java.entity.Course;
import com.ha.java.mapper.CourseMapper;
import com.ha.java.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;


    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {

        Course course = CourseMapper.INSTANCE.toCourse(courseDto);
        CourseDto dto = CourseMapper.INSTANCE.toCourseDto(courseService.save(course));
        return ResponseEntity.ok(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<CourseWithStudents> getCourseById(@PathVariable long id) {

        return ResponseEntity.ok(courseService.getCourseWithStudents(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourse() {

        List<CourseDto> courses = CourseMapper.INSTANCE.toCourseDto(courseService.getAllCourse());

        return ResponseEntity.ok(courses);
    }

    @GetMapping("student/{studentId}")
    public ResponseEntity<List<CourseDto>> getCourseByStudentId(@PathVariable long studentId) {

        List<CourseDto> courses = CourseMapper.INSTANCE.toCourseDto(courseService.getCourseByStudentId(studentId));

        return ResponseEntity.ok(courses);
    }

}
