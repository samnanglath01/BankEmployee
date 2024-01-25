package com.ha.school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class FullSchoolResponse {

    private String name;
    private String email;

    List<Student> students;

}
