package com.SLMS.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateResponse {
    private Long id;
    private String title;
    private String author;
    private Integer availableCopies;
    private LocalDateTime created;
}
