package com.SLMS.services;

import com.SLMS.dto.request.BookCreateRequest;
import com.SLMS.dto.response.ApiResponse;
import com.SLMS.dto.response.BookCreateResponse;
import com.SLMS.dto.response.BookResponse;
import com.SLMS.dto.response.BookUpdateResponse;
import com.SLMS.entity.Book;
import com.SLMS.exception.BookNotFoundException;
import com.SLMS.repository.BookRepository;
import com.SLMS.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.SLMS.constant.ApplicationConstant.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;


    public ApiResponse<BookCreateResponse> createNewBook(BookCreateRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userRepository.findByUsername(username).isEmpty()) {
            log.warn(USER_NOT_FOUND);
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setAvailableCopies(request.getAvailableCopies());
        book.setCreated(LocalDateTime.now());
        book.setUser(userRepository.findByUsername(username).get());
        log.info("Book created: {}", book);
        Book createdBook = bookRepository.save(book);

        BookCreateResponse bookCreateResponse = new BookCreateResponse(createdBook.getId(), createdBook.getTitle(), createdBook.getAuthor(), createdBook.getAvailableCopies(),createdBook.getCreated());
        ApiResponse<BookCreateResponse> response = new ApiResponse<>(
                HttpStatus.CREATED.toString(),
                201,
                BOOK_CREATED_SUCCESSFUL,
                false,
                bookCreateResponse
        );
        return response;
    }

    public ApiResponse<List<BookResponse>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()) {
            log.warn(BOOK_NOT_FOUND);
            throw new BookNotFoundException(BOOK_NOT_FOUND);
        }

        List<BookResponse> bookResponseList = new ArrayList<>();
        for(Book book : books) {
            BookResponse  bookResponse= new BookResponse(book.getId(),book.getTitle(),book.getAuthor(),book.getAvailableCopies(),book.getUser(),book.getCreated(),book.getUpdated());
            bookResponseList.add(bookResponse);
        }

        ApiResponse<List<BookResponse>> response = new ApiResponse<>(
                HttpStatus.OK.toString(),
                200,
                "All Books",
                false,
                bookResponseList
        );
        return response;
    }

    public ApiResponse<BookResponse> findBookById(Long id) {

        Book book = bookRepository.findById(id).orElseThrow(
                () -> new BookNotFoundException(BOOK_NOT_FOUND)

        );

        BookResponse bookResponse =  new BookResponse(book.getId(),book.getTitle(),book.getAuthor(),book.getAvailableCopies(),book.getUser(),book.getCreated(),book.getUpdated());
        ApiResponse<BookResponse> response = new ApiResponse<>(
                HttpStatus.OK.toString(),
                200,
                "Get book by id",
                false,
                bookResponse
        );
        return response;


    }

    public ApiResponse<BookUpdateResponse> updateBook(Long id, BookCreateRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(BOOK_NOT_FOUND));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setAvailableCopies(request.getAvailableCopies());
        book.setUpdated(LocalDateTime.now());
        Book updatedBook = bookRepository.save(book);

        BookUpdateResponse response = new BookUpdateResponse(book.getId(), book.getTitle(),  book.getAuthor(), book.getAvailableCopies(), book.getUser(), book.getUpdated());
        ApiResponse<BookUpdateResponse> apiResponse = new ApiResponse<>(
                HttpStatus.OK.toString(),
                200,
                BOOK_UPDATED_SUCCESSFUL,
                false,
                response
        );
        return apiResponse;
    }

    public  ApiResponse<Void> deleteBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(BOOK_NOT_FOUND));
        bookRepository.delete(book);
        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.OK.toString(),
                200,
                BOOK_DELETED_SUCCESSFUL,
                false,
                null
        );
        return response;
    }
}
