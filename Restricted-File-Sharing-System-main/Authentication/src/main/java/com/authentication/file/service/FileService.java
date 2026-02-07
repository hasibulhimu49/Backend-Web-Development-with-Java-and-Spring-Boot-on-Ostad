package com.authentication.file.service;

import com.authentication.Auth.dto.response.ApiResponse;
import com.authentication.Auth.entity.User;
import com.authentication.file.dto.response.FileDownloadResponse;
import com.authentication.file.dto.response.FileResponse;
import com.authentication.file.dto.response.FileUploadResponse;
import com.authentication.file.entity.FileEntry;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

public interface FileService {
    ApiResponse<FileUploadResponse> storeFile(MultipartFile file, User user) throws IOException;

    File getFileForDownload(String token,User user) throws IOException;

    ApiResponse<List<FileResponse>> myFile(User user);

    @Transactional
    void shareFile(Long fileId, List<String> emails, User owner) throws AccessDeniedException;
}
