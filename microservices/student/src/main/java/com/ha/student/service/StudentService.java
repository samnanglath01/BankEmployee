package com.ha.student.service;

import com.ha.student.entity.Student;
import com.ha.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> findStudents(long schooldId) {
        return studentRepository.findAllBySchoolId(schooldId);
    }

}
