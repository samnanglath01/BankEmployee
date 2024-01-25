package com.ha.java.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {

    Random random = new Random();
    public String generateOtp(){
        int rand = random.nextInt(999999);
        return String.format("%06d", rand);
    }

}
