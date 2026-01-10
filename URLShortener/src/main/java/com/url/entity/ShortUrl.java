package com.url.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "short_url",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "short_url"),
                @UniqueConstraint(columnNames = "original_url")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", nullable = false,length = 2048,unique = true)
    @NotBlank(message = "Url can not empty")
    private String originalUrl;

    @Column(name = "short_url", nullable = false, length = 10,unique = true)
    private String shortUrl;

    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime validity;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createdAt;
}
