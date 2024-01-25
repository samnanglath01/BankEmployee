package com.ha.java.controller;

import com.ha.java.entity.Otp;
import com.ha.java.repository.OtpRepository;
import com.ha.java.services.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OtpController {

    private final OtpService userService;

    private final OtpRepository otpRepository;

    @PostMapping("/verify-account/{emailId}")
    public ResponseEntity<String> register(@PathVariable String emailId) {

        Otp otpDb = otpRepository.findByEmailId(emailId).orElse(null);
        if(otpDb != null){
            otpRepository.delete(otpDb);
        }

        return new ResponseEntity<>(userService.register(emailId), HttpStatus.OK);
    }

    @PutMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam String email,
                                                @RequestParam String otp) {
        return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
    }
    @PutMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam String email) {
        return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
    }


}
