package com.manufacto.ManufactureInventory;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManufactureInventoryApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
        System.setProperty("JWT_EXPIRATION", dotenv.get("JWT_EXPIRATION"));

        System.setProperty("BREVO_API_URL", dotenv.get("BREVO_API_URL"));
        System.setProperty("BREVO_API_KEY", dotenv.get("BREVO_API_KEY"));
        System.setProperty("BREVO_SENDER_EMAIL", dotenv.get("BREVO_SENDER_EMAIL"));
        System.setProperty("BREVO_SENDER_NAME", dotenv.get("BREVO_SENDER_NAME"));

        System.setProperty("TWILIO_ACCOUNT_SID", dotenv.get("TWILIO_ACCOUNT_SID"));
        System.setProperty("TWILIO_AUTH_TOKEN", dotenv.get("TWILIO_AUTH_TOKEN"));
        System.setProperty("TWILIO_TRIAL_NUMBER", dotenv.get("TWILIO_TRIAL_NUMBER"));

        SpringApplication.run(ManufactureInventoryApplication.class, args);
    }
}