package com.generics.generics.service;

import com.generics.generics.entity.Student;

import java.util.Optional;

public interface StudentService extends BaseService<Student, Long>{

    byte[] getPhoto(String firstName);
    Student getStudent(String firstName);

}
