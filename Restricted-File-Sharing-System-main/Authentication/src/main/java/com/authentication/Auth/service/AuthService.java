package com.authentication.Auth.service;

import com.authentication.Auth.dto.request.LoginRequest;
import com.authentication.Auth.dto.request.RegisterRequest;
import com.authentication.Auth.dto.response.ApiResponse;
import jakarta.validation.Valid;

public interface AuthService {
    ApiResponse<Void> createUser(@Valid RegisterRequest request);

    ApiResponse<Void> login(@Valid LoginRequest request);
}
