package com.ha.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private String courseTitle;

    private String price;

    private String instructor;

}
