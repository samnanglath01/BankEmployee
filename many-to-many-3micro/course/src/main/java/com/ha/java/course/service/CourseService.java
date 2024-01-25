package com.ha.java.Bmicroservice.service;

import com.ha.java.Bmicroservice.entity.Course;
import com.ha.java.Bmicroservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }
    


}
