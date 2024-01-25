package com.ha.java.repository;

import com.ha.java.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByStudentsId(long studentId);

}
