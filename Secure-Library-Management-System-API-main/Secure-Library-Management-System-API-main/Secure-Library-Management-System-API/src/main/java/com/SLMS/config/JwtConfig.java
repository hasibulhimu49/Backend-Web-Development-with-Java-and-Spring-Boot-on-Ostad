package com.SLMS.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.jwt.congig")
@Data
public class JwtConfig {
    private String encryptionKey;
    private long tokenExpiration;
}
