package com.ha.usercourse.controller;

import com.ha.usercourse.entity.UserCourse;
import com.ha.usercourse.service.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usercourse")
@RequiredArgsConstructor
public class UserCourseController {

    private final UserCourseService userCourseService;


    @PostMapping
    public UserCourse saveUserCourse(@RequestParam long courseId, @RequestParam long userId) {
        return userCourseService.saveUserCourse(courseId, userId);
    }

    @GetMapping("{id}")
    public UserCourse getUserCourseById(@PathVariable long id) {
        return userCourseService.getUserCourseById(id);
    }

    @GetMapping("user/{id}")
    public List<UserCourse> getUserCourseByUserId(@PathVariable long id) {
        return userCourseService.getUserCourseByUserId(id);
    }

    @GetMapping("course/{id}")
    public List<UserCourse> getUserCourseByCourseId(@PathVariable long id) {
        return userCourseService.getUserCourseByCourseId(id);
    }

    @GetMapping
    public List<UserCourse> getAllUserCourses() {
        return userCourseService.getAllUserCourses();
    }


}
