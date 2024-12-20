package com.example.PhoneStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendErrorEmail(String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("hoangtammht@gmail.com");
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}
