package com.ha.usercourse.repository;

import com.ha.usercourse.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    Optional<List<UserCourse>> findByUserId(Long id);

    Optional<List<UserCourse>> findByCourseId(Long id);

}
