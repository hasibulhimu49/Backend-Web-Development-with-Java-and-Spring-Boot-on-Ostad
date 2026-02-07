package com.authentication.Auth.service.impl;

import com.authentication.Auth.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Override
    public void sentVerificationMail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Verify your account.");

        String link = "http://localhost:8080/api/verify?token=" + token;

        message.setText("""
                Click the link below to verify your account:
                %s
                
                Link expires in 10 minutes.
                """.formatted(link));

        javaMailSender.send(message);
    }

    @Override
    public void sendShereFile(String email, String owner,String downloadToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("A file has been share with you.");

        String downloadLink = "http://localhost:8080/api/files/download/" + downloadToken;

        message.setText("""
                Hello,

                A file has been shared with you by %s.

                Download link:
                %s

                Note:
                - You must be logged in
                - Only authorized users can download the file

                Regards,
                Secure File Sharing System
                """.formatted(owner, downloadLink));

        javaMailSender.send(message);
    }
}
