package com.manufacto.ManufactureInventory.signuplogin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BrevoEmailService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${brevo.api.url}")
    private String url;

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    @Value("${brevo.sender.name}")
    private String senderName;

    public void sendEmail(String toEmail, String otp) {

        if (toEmail == null || otp == null) {
            throw new RuntimeException("Email or OTP is null");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);
        headers.set("accept", "application/json");

        Map<String, Object> body = new HashMap<>();

        body.put("sender", Map.of("email", senderEmail, "name", senderName));
        body.put("to", List.of(Map.of("email", toEmail)));
        body.put("subject", "OTP Verification");
        body.put("htmlContent", "<h2>Your OTP is: " + otp + "</h2>");

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        restTemplate.postForEntity(url, request, String.class);
    }
}