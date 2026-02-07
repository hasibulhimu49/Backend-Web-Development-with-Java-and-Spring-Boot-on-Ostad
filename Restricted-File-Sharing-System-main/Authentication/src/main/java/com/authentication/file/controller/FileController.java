package com.authentication.file.controller;

import com.authentication.Auth.dto.response.ApiResponse;
import com.authentication.Auth.entity.User;
import com.authentication.Auth.service.impl.UserService;
import com.authentication.file.dto.request.ShareFileRequest;
import com.authentication.file.dto.response.FileDownloadResponse;
import com.authentication.file.dto.response.FileResponse;
import com.authentication.file.dto.response.FileUploadResponse;
import com.authentication.file.entity.FileEntry;
import com.authentication.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {
    private final FileService fileService;
    private final UserService userService;
    @Value("${app.download.base-path}")
    private String downloadPath;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<FileUploadResponse>> upload(@RequestParam("file") MultipartFile file,
                                                                  @AuthenticationPrincipal UserDetails userDetails) throws IOException{
        User user = userService.getUserByEmail(userDetails.getUsername());
        return ResponseEntity.ok(fileService.storeFile(file,user));
    }


    @GetMapping("/download/{token}")
    public ResponseEntity<Resource> download(@PathVariable String token,
                                             @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        User user = userService.getUserByEmail(userDetails.getUsername());
        File file = fileService.getFileForDownload(token,user);

        Resource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition
                                .attachment()
                                .filename(file.getName(), StandardCharsets.UTF_8)
                                .build()
                                .toString()
                )
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/share")
    public ResponseEntity<?> shareFile(
            @RequestBody ShareFileRequest request,
            @AuthenticationPrincipal UserDetails userDetails) throws AccessDeniedException {

        User owner = userService.getUserByEmail(userDetails.getUsername());
        fileService.shareFile(request.fileId(), request.emails(), owner);

        return ResponseEntity.ok("File shared successfully");
    }


    @GetMapping("/mine")
    public ResponseEntity<ApiResponse<List<FileResponse>>> myFile(@AuthenticationPrincipal UserDetails userDetails){
        User user = userService.getUserByEmail(userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(fileService.myFile(user));
    }
}
