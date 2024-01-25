package com.ha.java.services;

import com.ha.java.entity.Otp;
import com.ha.java.exception.ResourceNotFoundException;
import com.ha.java.repository.OtpRepository;
import com.ha.java.util.EmailUtil;
import com.ha.java.util.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;
    private final OtpUtil otpUtil;
    private final EmailUtil emailUtil;

    public String register(String emailId) {
        String otp = otpUtil.generateOtp();

        try {
            emailUtil.sendOtpEmail(emailId, otp);
        } catch (MessagingException e) {
            throw new ResourceNotFoundException("Unable to send otp please try again");
        }

        Otp otpDb = new Otp();
        otpDb.setEmailId(emailId);
        otpDb.setOtp(otp);
        otpDb.setOtpGeneratedTime(LocalDateTime.now());
        otpRepository.save(otpDb);

        return "Please Verify Email within 1 minute";
    }

    public String verifyAccount(String email, String otp) {
        Otp user = otpRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            otpRepository.save(user);
            return "OTP verified you can login";
        }
        return "Please regenerate otp and try again";
    }

    public String regenerateOtp(String email) {
        Otp user = otpRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new ResourceNotFoundException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        otpRepository.save(user);
        return "Email sent... please verify account within 1 minute";
    }


}
