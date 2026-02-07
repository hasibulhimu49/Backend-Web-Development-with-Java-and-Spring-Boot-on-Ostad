package com.authentication.file.repository;

import com.authentication.Auth.entity.User;
import com.authentication.file.entity.FileAccess;
import com.authentication.file.entity.FileEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileAccessRepository extends JpaRepository<FileAccess, Long> {
    // Only this one is needed
    Boolean existsByFileEntryAndUser(FileEntry fileEntry, User user);
}

