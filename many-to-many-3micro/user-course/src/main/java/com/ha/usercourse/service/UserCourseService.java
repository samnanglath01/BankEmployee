package com.ha.usercourse.service;

import com.ha.usercourse.entity.UserCourse;
import com.ha.usercourse.repository.UserCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCourseService {

    private final UserCourseRepository userCourseRepository;

    public UserCourse saveUserCourse(long courseId, long userId) {

        UserCourse user = new UserCourse();
        user.setCourseId(courseId);
        user.setUserId(userId);
        return userCourseRepository.save(user);
    }

    public UserCourse getUserCourseById(Long id) {
        return userCourseRepository.findById(id).orElse(null);
    }

    public List<UserCourse> getUserCourseByUserId(Long id) {
        return userCourseRepository.findByUserId(id).orElse(null);
    }

    public List<UserCourse> getUserCourseByCourseId(Long id) {
        return userCourseRepository.findByCourseId(id).orElse(null);
    }

    public List<UserCourse> getAllUserCourses() {
        return userCourseRepository.findAll();
    }









}
