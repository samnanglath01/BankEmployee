package com.ha.student.repository;

import com.ha.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllBySchoolId(long schoolId);
}
