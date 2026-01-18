package com.example.librarybook_crud_api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDto {

        Long id;

        String title;

        String author;

        String publication;

        int publicationYear;

        int availableCopies;
}
