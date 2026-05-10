package com.manufacto.ManufactureInventory.signuplogin;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsService {

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.trial_number}")
    private String fromNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    public void sendSms(String phone, String otp) {

        try {
            Message.creator(
                    new PhoneNumber(phone),       // TO
                    new PhoneNumber(fromNumber),  // FROM (Twilio number)
                    "Your OTP is: " + otp
            ).create();

            System.out.println("✅ SMS sent to: " + phone);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Twilio SMS failed");
        }
    }
}