package com.authentication.file.entity;

import com.authentication.Auth.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String filePath;

    private LocalDateTime uploadTime;

    @Column(unique = true)
    private String downloadToken;

    @ManyToOne
    private User owner;
}
