package com.SLMS.dto.request;

import lombok.Data;

@Data
public class BookCreateRequest {
    private String title;
    private String author;
    private Integer availableCopies;
}
