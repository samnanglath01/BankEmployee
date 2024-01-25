package com.ha.java.dto;

import com.ha.java.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithCourse {

    private long id;
    private String name;
    private String phoneNumber;

    private Set<Course> courses ;

}
