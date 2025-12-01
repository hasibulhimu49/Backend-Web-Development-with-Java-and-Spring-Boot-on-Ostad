package com.SLMS.services;

import com.SLMS.dto.request.LoginRequest;
import com.SLMS.dto.request.RefreshTokenRequest;
import com.SLMS.dto.request.RegisterUserRequest;
import com.SLMS.dto.response.ApiResponse;
import com.SLMS.dto.response.LoginResponse;
import com.SLMS.dto.response.RegisterUserResponse;
import com.SLMS.entity.User;
import com.SLMS.enums.Role;
import com.SLMS.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Ref;

import static com.SLMS.constant.ApplicationConstant.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ApiResponse<RegisterUserResponse> createUser(RegisterUserRequest request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            log.warn(USER_ALREADY_EXISTS);
            throw new EntityExistsException(USER_ALREADY_EXISTS);
        }
        User user = new User();
        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        if(request.getRole().equalsIgnoreCase("admin")){
            user.setRole(Role.ADMIN);
        }
        else if(request.getRole().equalsIgnoreCase("user")){
            user.setRole(Role.USER);
        }

        User userCreated = userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse(userCreated.getId(),userCreated.getFirstname(), userCreated.getLastname(), userCreated.getUsername(),userCreated.getRole());

        ApiResponse<RegisterUserResponse> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED.toString(),
                201,
                USER_REGISTRATION_SUCCESSFUL,
                false,
                response
        );
        return apiResponse;
    }

    public ApiResponse<LoginResponse> login(LoginRequest request) {
        User user1 = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User name not found"+request.getUsername()));
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User  user = (User) authentication.getPrincipal();
        if(user.getUsername().isEmpty()){
            log.error("Username not found");
            throw new UsernameNotFoundException("Username not found");
        }

        String access_token = jwtService.generateToken(user);
        String refresh_token = jwtService.generateRefreshToken(user);

        LoginResponse response = new LoginResponse(access_token,refresh_token);

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(
                HttpStatus.OK.toString(),
                200,
                LOGIN_SUCCESSFUL,
                false,
                response
        );
        return apiResponse;
    }

    public  ApiResponse<LoginResponse> refreshToken(RefreshTokenRequest refreshToken) {
        String username = jwtService.extractUsername(refreshToken.getRefreshToken());
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        if(!jwtService.isTokenValid(refreshToken.getRefreshToken(),user)){
            throw  new RuntimeException("Invalid token");
        }

        String access_token = jwtService.generateToken(user);

        String refresh_token = jwtService.generateRefreshToken(user);
        LoginResponse response = new LoginResponse(access_token,refresh_token);
        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(
                HttpStatus.CREATED.toString(),
                201,
                "Generated new access token",
                false,
                response
        );
        return apiResponse;
    }
}
