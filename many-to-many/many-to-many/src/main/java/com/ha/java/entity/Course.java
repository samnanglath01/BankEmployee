package com.ha.java.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String courseTitle;

    private String price;

    private String instructor;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

}