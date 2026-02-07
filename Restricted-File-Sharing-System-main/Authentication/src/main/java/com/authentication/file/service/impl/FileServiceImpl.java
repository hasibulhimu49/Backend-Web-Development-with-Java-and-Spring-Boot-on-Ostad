package com.authentication.file.service.impl;

import com.authentication.Auth.dto.response.ApiResponse;
import com.authentication.Auth.entity.User;
import com.authentication.Auth.repository.UserRepository;
import com.authentication.Auth.service.EmailService;
import com.authentication.file.dto.response.FileResponse;
import com.authentication.file.dto.response.FileUploadResponse;
import com.authentication.file.entity.FileAccess;
import com.authentication.file.entity.FileEntry;
import com.authentication.file.repository.FileAccessRepository;
import com.authentication.file.repository.FileEntryRepository;
import com.authentication.file.service.FileService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {
    private FileEntryRepository fileEntryRepository;
    private final UserRepository userRepository;
    private final FileAccessRepository fileAccessRepository;
    private final EmailService emailService;
    private final Path uploadDir;

    public FileServiceImpl(FileEntryRepository fileEntryRepository, UserRepository userRepository, FileAccessRepository fileAccessRepository, EmailService emailService, @Value("${file.upload-dir}") String uploadDir) throws IOException {
        this.fileEntryRepository = fileEntryRepository;
        this.userRepository = userRepository;
        this.fileAccessRepository = fileAccessRepository;
        this.emailService = emailService;
        this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.uploadDir);
    }
    @Override
    public ApiResponse<FileUploadResponse> storeFile(MultipartFile file, User owner) throws IOException {

        if (file.getSize() > 50 * 1024 * 1024) {
            throw new IllegalArgumentException("File size exceeds 50MB");
        }

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path target = uploadDir.resolve(filename);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        FileEntry entry = FileEntry.builder()
                .filename(filename)
                .filePath(target.toString())
                .downloadToken(UUID.randomUUID().toString())
                .uploadTime(LocalDateTime.now())
                .owner(owner)
                .build();

        fileEntryRepository.save(entry);

        return new ApiResponse<>(
                "OK",
                200,
                "File uploaded successfully",
                false,
                new FileUploadResponse(entry.getDownloadToken())
        );
    }


    @Override
    public File getFileForDownload(String token, User user) throws AccessDeniedException, FileNotFoundException {

        FileEntry file = (FileEntry) fileEntryRepository.findByDownloadToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Invalid download link"));

        boolean hasAccess =
                file.getOwner().getId().equals(user.getId()) ||
                        fileAccessRepository.existsByFileEntryAndUser(file, user);

        if (!hasAccess) {
            throw new AccessDeniedException("You are not authorized to download this file");
        }

        File diskFile = new File(file.getFilePath());
        if (!diskFile.exists()) {
            throw new FileNotFoundException("File not found on server");
        }

        return diskFile;
    }


    @Override
    public ApiResponse<List<FileResponse>> myFile(User user) {
        List<FileEntry> fileEntries = fileEntryRepository.findAll();
        List<FileResponse> responses = new ArrayList<>();

        for (FileEntry file : fileEntries) {
            boolean hasAccess = file.getOwner().equals(user) ||
                    fileAccessRepository.existsByFileEntryAndUser(file, user);

            if (hasAccess) {
                FileResponse response = new FileResponse(file.getId(), file.getFilePath());
                responses.add(response);
            }
        }

        return new ApiResponse<>(
                HttpStatus.OK.toString(),
                200,
                "All accessible files.",
                false,
                responses
        );
    }



    @Transactional
    @Override
    public void shareFile(Long fileId, List<String> emails, User owner) throws AccessDeniedException {

        FileEntry fileEntry = fileEntryRepository.findById(fileId)
                .orElseThrow(() -> new EntityNotFoundException("File not found"));

        if (!fileEntry.getOwner().getId().equals(owner.getId())) {
            throw new AccessDeniedException("Only the owner can share this file");
        }

        for (String email : emails) {
            User user = userRepository.findByEmailAndVerified(email, true)
                    .orElseThrow(() ->
                            new EntityNotFoundException("User not found or not verified: " + email));

            if (!fileAccessRepository.existsByFileEntryAndUser(fileEntry, user)) {
                fileAccessRepository.save(
                        new FileAccess(null, fileEntry, user, LocalDateTime.now())
                );

                emailService.sendShereFile(
                        user.getEmail(),
                        owner.getEmail(),
                        fileEntry.getDownloadToken()
                );
            }
        }
    }

}
