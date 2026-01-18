package com.example.librarybook_crud_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record BookCreateRequestDto (


        @JsonProperty("book_title")
        String title,

        @NotNull(message = "author cant be null")
        String author,

        String publication,

        int publicationYear,

        int availableCopies
){}
