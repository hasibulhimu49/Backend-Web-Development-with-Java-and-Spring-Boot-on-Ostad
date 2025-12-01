package com.SLMS.dto.response;

import com.SLMS.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Integer availableCopies;
    private User user;
    private LocalDateTime created;
    private LocalDateTime updated;
}
