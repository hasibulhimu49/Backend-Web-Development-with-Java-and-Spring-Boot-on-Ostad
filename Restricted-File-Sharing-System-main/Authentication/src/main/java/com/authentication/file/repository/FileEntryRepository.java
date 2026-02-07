package com.authentication.file.repository;

import com.authentication.file.entity.FileEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileEntryRepository extends JpaRepository<FileEntry,Long> {
    Optional<Object> findByDownloadToken(String token);
}
