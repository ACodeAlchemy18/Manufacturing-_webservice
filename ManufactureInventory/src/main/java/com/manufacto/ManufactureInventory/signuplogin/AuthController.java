package com.manufacto.ManufactureInventory.signuplogin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // 📧 SEND EMAIL OTP
    @PostMapping("/send-email-otp")
    public ResponseEntity<?> sendEmailOtp(@RequestBody Map<String, String> req) {
        authService.sendEmailOtp(req.get("email"));
        return ResponseEntity.ok(new SignupResponse("Email OTP sent", true));
    }

   

    // ✅ VERIFY EMAIL OTP
    @PostMapping("/verify-email-otp")
    public ResponseEntity<?> verifyEmailOtp(@RequestBody Map<String, String> req) {
        authService.verifyEmailOtp(req.get("email"), req.get("otp"));
        return ResponseEntity.ok(new SignupResponse("Email verified", true));
    }

 // SEND PHONE OTP
    @PostMapping("/send-phone-otp")
    public ResponseEntity<?> sendPhoneOtp(@RequestBody Map<String, String> req) {
        authService.sendPhoneOtp(req.get("phone"));
        return ResponseEntity.ok(new SignupResponse("Phone OTP sent", true));
    }

    // VERIFY PHONE OTP
    @PostMapping("/verify-phone-otp")
    public ResponseEntity<?> verifyPhoneOtp(@RequestBody Map<String, String> req) {
        authService.verifyPhoneOtp(req.get("phone"), req.get("otp"));
        return ResponseEntity.ok(new SignupResponse("Phone verified", true));
    }

    // 🔑 RESET PASSWORD
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody LoginRequest request) {
        authService.resetPassword(request);
        return ResponseEntity.ok(new SignupResponse("Password updated", true));
    }

    // 📝 SIGNUP
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        authService.registerUser(request);
        return ResponseEntity.ok(new SignupResponse("Signup successful", true));
    }

    // 🔐 LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }
}