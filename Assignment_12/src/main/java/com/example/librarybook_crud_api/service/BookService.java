package com.example.librarybook_crud_api.service;

import com.example.librarybook_crud_api.dto.BookCreateRequestDto;
import com.example.librarybook_crud_api.dto.BookResponseDto;
import com.example.librarybook_crud_api.dto.BookUpdateRequestDto;
import com.example.librarybook_crud_api.entity.Book;
import com.example.librarybook_crud_api.mapper.BookMapper;
import com.example.librarybook_crud_api.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    BookRepository repository;
    BookMapper mapper;

    public BookResponseDto createBook(BookCreateRequestDto dto){
        Book book=mapper.toEntity(dto);
        repository.save(book);
        return mapper.toDto(book);
    }


    public List<BookResponseDto> getAllBook(){
       List<Book> books =repository.findAll();
       return books.stream().map(b->mapper.toDto(b)).toList();
    }


    public BookResponseDto getBookById(Long id)
    {
        Book book= repository.findById(id).orElseThrow(()->new EntityNotFoundException("Not found"));
        return mapper.toDto(book);
    }


    public BookResponseDto updateBook(Long id, BookUpdateRequestDto dto)
    {
        Book book=repository.findById(id).orElseThrow(()->new EntityNotFoundException("Not exist"));
        mapper.updateEntity(dto,book);
        repository.save(book);
        return mapper.toDto(book);
    }

    public void deleteBook(Long id)
    {
        repository.deleteById(id);
    }

    public List<BookResponseDto> getBookByAuthor(String author) {

        List<Book> books = repository.findAll();

        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .map(book -> mapper.toDto(book)).toList();
    }

    public List<BookResponseDto> getBookByPublication(String publication)
    {
        List<Book> books=repository.findAll();

        return books.stream()
                .filter(book -> book.getPublication().equalsIgnoreCase(publication))
                .map(book -> mapper.toDto(book)).toList();
    }


}
