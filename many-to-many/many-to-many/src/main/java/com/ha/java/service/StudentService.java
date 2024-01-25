package com.ha.java.service;

import com.ha.java.dto.StudentWithCourse;
import com.ha.java.entity.Course;
import com.ha.java.entity.Student;
import com.ha.java.repository.CourseRepository;
import com.ha.java.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    public StudentWithCourse getStudent(long id) {

        Student student = studentRepository.findById(id).orElseThrow();

        List<Course> courses = courseRepository.findByStudentsId(student.getId());

        return new StudentWithCourse(student.getId(), student.getName(), student.getPhoneNumber(), new HashSet<>(courses));
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentByName(String name) {
        return studentRepository.findByNameContaining(name);
    }


    public Student addCourse(long studentId, long courseId){
        Student getExistingStudent = studentRepository.findById(studentId).orElseThrow(() ->
                new RuntimeException("Student not found"));
        Course getExistingCourse = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

//        getExistingStudent.setCourses(new HashSet<>());

        getExistingStudent.getCourses().add(getExistingCourse);

        return studentRepository.save(getExistingStudent);
    }

}
