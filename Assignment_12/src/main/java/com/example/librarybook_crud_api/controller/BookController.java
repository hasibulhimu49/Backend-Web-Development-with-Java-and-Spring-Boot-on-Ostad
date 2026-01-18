package com.example.librarybook_crud_api.controller;


import com.example.librarybook_crud_api.dto.BookCreateRequestDto;
import com.example.librarybook_crud_api.dto.BookResponseDto;
import com.example.librarybook_crud_api.dto.BookUpdateRequestDto;
import com.example.librarybook_crud_api.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookCreateRequestDto dto)
    {
         BookResponseDto response=service.createBook(dto);
         return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks()
    {
        return ResponseEntity.ok(service.getAllBook());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookUpdateRequestDto dto){
      BookResponseDto responseDto=  service.updateBook(id, dto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id)
    {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();

    }



    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookResponseDto>> getBookByAuthor(@PathVariable String author)
    {
        return ResponseEntity.ok(service.getBookByAuthor(author));
    }

    @GetMapping("/publication")
    public ResponseEntity<List<BookResponseDto>> getBookByPublication(@RequestParam String publication)
    {
        return ResponseEntity.ok(service.getBookByPublication(publication));
    }



}
