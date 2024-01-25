package com.ha.testing.service;

import com.ha.testing.client.Course;
import com.ha.testing.client.CourseClient;
import com.ha.testing.client.UserCourseClient;
import com.ha.testing.dto.UserWithCourse;
import com.ha.testing.entity.User;
import com.ha.testing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserCourseClient userCourseClient;
    private final CourseClient courseClient;



    //-----------------------------------------------------------------------------
    //write me CRUD methods in this service class
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public UserWithCourse getUserWithCourses(Long id) {
        var user = userRepository.findById(id).orElse(null);

        var userCourse = userCourseClient.getUserCourseByUserId(user.getId());

        List<Course> courses = new ArrayList<>();

        userCourse.stream().forEach(course ->
                courses.add(courseClient.get(course.getCourseId())));


        return new UserWithCourse(user.getName(), user.getPhoneNumber(), courses);
    }





}
