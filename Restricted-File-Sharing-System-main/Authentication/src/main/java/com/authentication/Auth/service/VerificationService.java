package com.authentication.Auth.service;

import com.authentication.Auth.dto.response.ApiResponse;
import com.authentication.Auth.entity.User;

public interface VerificationService {
    void sentVerificationEmail(User user);

    ApiResponse<Void> verify(String token);
}
