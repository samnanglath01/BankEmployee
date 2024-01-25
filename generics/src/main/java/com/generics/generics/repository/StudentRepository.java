package com.generics.generics.repository;

import com.generics.generics.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByFirstName(String firstName);

}
