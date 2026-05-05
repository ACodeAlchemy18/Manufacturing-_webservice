package com.manufacto.ManufactureInventory.signuplogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private TokenProvider tokenProvider;
    @Autowired private OtpService otpService;
    @Autowired private BrevoEmailService emailService;
    @Autowired
    private TwilioSmsService twilioSmsService;
    // ================= EMAIL OTP =================
    public void sendEmailOtp(String email) {

        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        String otp = otpService.sendEmailOtp(email);
        emailService.sendEmail(email, otp);
    }

   
    // SEND OTP
    public void sendPhoneOtp(String phone) {

        if (phone == null || phone.isEmpty()) {
            throw new RuntimeException("Phone is required");
        }

        String otp = otpService.sendPhoneOtp(phone);

        System.out.println("📱 GENERATED OTP: " + otp);

        // IMPORTANT: Add +91
        twilioSmsService.sendSms("+91" + phone, otp);
    }

    // VERIFY OTP
    public void verifyPhoneOtp(String phone, String otp) {

        boolean valid = otpService.verifyPhoneOtp(phone, otp);

        if (!valid) {
            throw new RuntimeException("Invalid OTP");
        }
    }
    // ================= VERIFY EMAIL =================
    public void verifyEmailOtp(String email, String otp) {

        boolean valid = otpService.verifyEmailOtp(email, otp);

        if (!valid) {
            throw new RuntimeException("Invalid Email OTP");
        }

        otpService.clearEmailOtp(email);
    }

   
    // ================= RESET PASSWORD =================
    public void resetPassword(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    // ================= SIGNUP =================
    public User registerUser(SignupRequest request) {

    	if (request.getPassword() == null || request.getPassword().isEmpty()) {
    	    throw new RuntimeException("Password is required");
    	}

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        user.setCompanyName(request.getCompanyName());
        user.setIndustryType(request.getIndustryType());
        user.setAddress(request.getAddress());
        user.setPinCode(request.getPinCode());
        user.setState(request.getState());
        user.setCity(request.getCity());
        user.setGst(request.getGst());

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    // ================= LOGIN =================
    public AuthResponseDto loginUser(LoginRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid email or password");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = tokenProvider.generateToken(new UserPrincipal(user));

        return new AuthResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                token,
                "Login successful"
        );
    }
}