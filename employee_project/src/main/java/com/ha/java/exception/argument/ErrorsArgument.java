package com.ha.java.exception.argument;

import com.ha.java.constants.ApplicationConstants;
import lombok.Data;

@Data
public class ErrorsArgument {

    private String firstName = ApplicationConstants.ACCEPTABLE;
    private String lastName = ApplicationConstants.ACCEPTABLE;
    private String phoneNumber = ApplicationConstants.ACCEPTABLE;
    private String email = ApplicationConstants.ACCEPTABLE;

}
