package com.ha.java.dto;

import com.ha.java.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseWithStudents {

    private long id;
    private String courseTitle;

    private String price;

    private String instructor;

    private List<Student> students;

}
