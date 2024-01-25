package com.ha.testing.dto;

import com.ha.testing.client.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRole {

    private String name;

    Course course;
}
