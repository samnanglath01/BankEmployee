package com.ha.java.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {

    public String generateOtp(){
        Random random = new Random();
        int rand = random.nextInt(999999);
        String otp = String.format("%06d", rand);
        return otp;
    }

}
