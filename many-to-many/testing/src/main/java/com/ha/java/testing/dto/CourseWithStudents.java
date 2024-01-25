package com.ha.java.testing.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseWithStudents {

    private long id;
    private String courseTitle;

    private String price;

    private String instructor;

    private List<StudentDto> students;

}
