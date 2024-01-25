package com.ha.java.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    private long id;
    @NotEmpty(message = "First name is required")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "([0-9]{9})", message = "INVALID, Phone number should be 9 digits")
    private String phoneNumber;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    private byte[] imagePath;
}
