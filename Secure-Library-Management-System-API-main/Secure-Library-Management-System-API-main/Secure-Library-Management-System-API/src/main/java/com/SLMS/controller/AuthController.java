package com.SLMS.controller;

import com.SLMS.dto.request.LoginRequest;
import com.SLMS.dto.request.RefreshTokenRequest;
import com.SLMS.dto.request.RegisterUserRequest;
import com.SLMS.dto.response.ApiResponse;
import com.SLMS.dto.response.LoginResponse;
import com.SLMS.dto.response.RegisterUserResponse;
import com.SLMS.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUserResponse>> createUser(@RequestBody RegisterUserRequest request){
        ApiResponse<RegisterUserResponse> registerUser = authService.createUser(request);
        System.out.println(registerUser.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request){
        ApiResponse<LoginResponse> response = authService.login(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(@RequestBody RefreshTokenRequest refreshToken) {
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
}
