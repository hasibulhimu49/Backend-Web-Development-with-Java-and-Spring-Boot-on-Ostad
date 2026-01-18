package com.example.librarybook_crud_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="book_table")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String title;

    @Column
    String author;

    @Column
    String publication;

    @Column
    int publicationYear;

    @Column
    int availableCopies;
}
