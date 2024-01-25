package com.generics.generics.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "students")
@NoArgsConstructor
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
//    private int age;
//    private String phoneNumber;
//    private String email;
//    private String address;
//    private Date birdthDate;
//    private String university;
//    private String major;
//    private String classRoom;
//    private String currentYear;

    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

    public Student(String firstName, String lastName, byte[] image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }
}
