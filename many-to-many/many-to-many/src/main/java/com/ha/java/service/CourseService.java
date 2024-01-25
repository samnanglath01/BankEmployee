package com.ha.java.service;

import com.ha.java.dto.CourseWithStudents;
import com.ha.java.entity.Course;
import com.ha.java.entity.Student;
import com.ha.java.repository.CourseRepository;
import com.ha.java.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public Course save(Course course){
        return courseRepository.save(course);
    }

    public CourseWithStudents getCourseWithStudents(long id){

        Course course = courseRepository.findById(id).orElse(null);

        List<Student> students = studentRepository.findAllById(course.getId());

        return new CourseWithStudents(course.getId(), course.getCourseTitle(), course.getPrice(), course.getInstructor(), students);
    }

    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public List<Course> getCourseByStudentId(long studentId){
        //------------------get student
        return courseRepository.findByStudentsId(studentId);
    }

}
