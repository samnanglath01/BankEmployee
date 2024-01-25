package com.ha.java.repository;

import com.ha.java.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContaining(String name);

    List<Student> findAllById(long id);

}
