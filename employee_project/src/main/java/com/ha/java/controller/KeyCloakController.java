package com.ha.java.controller;

import com.ha.java.dto.UserDto;
import com.ha.java.entity.Otp;
import com.ha.java.exception.ResourceNotFoundException;
import com.ha.java.repository.OtpRepository;
import com.ha.java.services.KeyCloakService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/keycloak")
@RequiredArgsConstructor
public class KeyCloakController {

    private final KeyCloakService service;
    private final OtpRepository otpRepository;

    @PostMapping("/register/{otp}")
    public String addUser(@RequestBody UserDto userDTO, @PathVariable String otp){
        Otp user = otpRepository.findByEmailId(userDTO.getEmailId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Please generate otp first for %s", userDTO.getEmailId())));

        if (user.getOtp().equals(otp) &&
                Duration.between(user.getOtpGeneratedTime(), LocalDateTime.now()).getSeconds() < (1 * 60)) {

            service.addUser(userDTO);
            otpRepository.delete(user);
            return "Account Created Successfully";
        }else {
            throw new ResourceNotFoundException("Invalid OTP");
        }

    }


    @GetMapping(path = "/{userName}")
    public List<UserRepresentation> getUser(@PathVariable("userName") String userName){
        return service.getUser(userName);
    }

    @PutMapping(path = "/update/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @RequestBody UserDto userDTO){
        service.updateUser(userId, userDTO);
        return "User Details Updated Successfully.";
    }

    @DeleteMapping(path = "/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        service.deleteUser(userId);
        return "User Deleted Successfully.";
    }

    @GetMapping(path = "/verification-link/{userId}")
    public String sendVerificationLink(@PathVariable("userId") String userId){
        service.sendVerificationLink(userId);
        return "Verification Link Send to Registered E-mail Id.";
    }

    @GetMapping(path = "/reset-password/{userId}")
    public String sendResetPassword(@PathVariable("userId") String userId){
        service.sendResetPassword(userId);
        return "Reset Password Link Send Successfully to Registered E-mail Id.";
    }
}
