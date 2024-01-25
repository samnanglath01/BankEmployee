package com.ha.testing.dto;

import com.ha.testing.client.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithCourse {

    private String name;
    private String phoneNumber;

    List<Course> courses;

}
