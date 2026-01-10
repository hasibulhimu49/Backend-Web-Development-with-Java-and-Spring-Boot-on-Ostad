package com.url.repository;

import com.url.entity.ShortUrl;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<ShortUrl,Long> {
    Optional<ShortUrl> findByShortUrl(String url);

    boolean existsByShortUrl(String shortUrl);

    Optional<ShortUrl> findByOriginalUrl(String s);
}
