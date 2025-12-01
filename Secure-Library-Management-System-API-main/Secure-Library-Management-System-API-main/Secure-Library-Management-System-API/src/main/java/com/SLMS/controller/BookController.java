package com.SLMS.controller;

import com.SLMS.dto.request.BookCreateRequest;
import com.SLMS.dto.response.ApiResponse;
import com.SLMS.dto.response.BookCreateResponse;
import com.SLMS.dto.response.BookResponse;
import com.SLMS.dto.response.BookUpdateResponse;
import com.SLMS.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BookCreateResponse>> createBook(@RequestBody BookCreateRequest request){
        ApiResponse<BookCreateResponse> response = bookService.createNewBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ApiResponse<List<BookResponse>>> getAllBooks(){
        ApiResponse<List<BookResponse>> response = bookService.getAllBooks();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable Long id){
        ApiResponse<BookResponse> response = bookService.findBookById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BookUpdateResponse>> updateBook(@PathVariable Long id, @RequestBody BookCreateRequest request){
        ApiResponse<BookUpdateResponse> response = bookService.updateBook(id,request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBookById(id));
    }
}
