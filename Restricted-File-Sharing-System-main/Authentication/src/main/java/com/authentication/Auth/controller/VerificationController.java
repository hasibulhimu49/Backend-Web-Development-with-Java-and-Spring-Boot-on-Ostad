package com.authentication.Auth.controller;

import com.authentication.Auth.dto.response.ApiResponse;
import com.authentication.Auth.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VerificationController {

    private final VerificationService verificationService;

    @GetMapping("/verify")
    public ResponseEntity<ApiResponse<Void>> verifyAccount(@RequestParam String token){
        return ResponseEntity.status(HttpStatus.OK).body(verificationService.verify(token));
    }
}
