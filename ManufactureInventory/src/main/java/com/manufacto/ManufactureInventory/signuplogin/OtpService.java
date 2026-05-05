package com.manufacto.ManufactureInventory.signuplogin;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OtpService {

    private Map<String, String> emailOtpStore = new HashMap<>();
    private Map<String, String> phoneOtpStore = new HashMap<>();

    private Map<String, LocalDateTime> emailExpiry = new HashMap<>();
    private Map<String, LocalDateTime> phoneExpiry = new HashMap<>();

    public String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    // 📧 EMAIL OTP
    public String sendEmailOtp(String email) {
        String otp = generateOtp();
        emailOtpStore.put(email, otp);
        emailExpiry.put(email, LocalDateTime.now().plusMinutes(5));
        return otp;
    }


    // ✅ VERIFY EMAIL OTP
    public boolean verifyEmailOtp(String email, String otp) {
        if (!emailOtpStore.containsKey(email)) {
            throw new RuntimeException("Email OTP not found");
        }

        if (emailExpiry.get(email).isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Email OTP expired");
        }

        return otp.equals(emailOtpStore.get(email));
    }

    public String sendPhoneOtp(String phone) {
        String otp = generateOtp();
        phoneOtpStore.put(phone, otp);
        phoneExpiry.put(phone, LocalDateTime.now().plusMinutes(5));
        return otp;
    }

    public boolean verifyPhoneOtp(String phone, String otp) {
        if (!phoneOtpStore.containsKey(phone)) {
            throw new RuntimeException("OTP not found");
        }
        if (phoneExpiry.get(phone).isBefore(LocalDateTime.now())) {
            phoneOtpStore.remove(phone);
            phoneExpiry.remove(phone);
            throw new RuntimeException("OTP expired");
        }
        if (!otp.equals(phoneOtpStore.get(phone))) {
            throw new RuntimeException("Invalid OTP");
        }
        phoneOtpStore.remove(phone);
        phoneExpiry.remove(phone);
        return true;
    }


    public void clearEmailOtp(String email) {
        emailOtpStore.remove(email);
        emailExpiry.remove(email);
    }

    public void clearPhoneOtp(String phone) {
        phoneOtpStore.remove(phone);
        phoneExpiry.remove(phone);
    }
}