package com.authentication.Auth.service;

public interface EmailService {
    void sentVerificationMail(String email, String token);

    void sendShereFile(String email, String email1,String token);
}
