package com.example.librarybook_crud_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record BookUpdateRequestDto(

        @JsonProperty("book_title")
        String title,

        String author,

        String publication,

        int publicationYear,

        int availableCopies


) { }
