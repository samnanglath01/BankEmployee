package com.generics.generics.service.impl;

import com.generics.generics.entity.Student;
import com.generics.generics.repository.StudentRepository;
import com.generics.generics.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, Long> implements StudentService {
    public StudentServiceImpl(StudentRepository studentRepository) {
        super(studentRepository);
    }

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public byte[] getPhoto(String firstName) {

        Student student = studentRepository.findByFirstName(firstName).orElseThrow(() -> new RuntimeException("Student not found"));
        System.out.println("Student: " + student);
        return student.getImage();
    }

    @Override
    public Student getStudent(String firstName) {
        Student student = studentRepository.findByFirstName(firstName).orElseThrow(() -> new RuntimeException("Student not found"));
        System.out.println("Student: " + student);
        return student;
    }
}
