package com.ha.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private String userName;
    private String emailId;
    private String password;
    private String firstname;
    private String lastName;

}
