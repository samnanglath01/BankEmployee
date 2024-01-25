package com.ha.java.Bmicroservice.repository;

import com.ha.java.Bmicroservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
