package com.authentication.file.entity;

import com.authentication.Auth.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "file_access")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private FileEntry fileEntry;

    @ManyToOne
    private User user;

    private LocalDateTime grantedAt = LocalDateTime.now();
}
