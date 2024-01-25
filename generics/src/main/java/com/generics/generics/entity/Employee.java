package com.generics.generics.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private String email;
    private String address;
    private Date birdthDate;
    private String company;
    private String role;
    private String experience;

}
