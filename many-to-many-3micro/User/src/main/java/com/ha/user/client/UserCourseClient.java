package com.ha.testing.client;

import com.ha.testing.dto.UserCourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "usercourse-sevice",
                url = "http://localhost:8030/usercourse")
public interface UserCourseClient {

        @GetMapping("user/{id}")
        List<UserCourse> getUserCourseByUserId(@PathVariable long id);





}