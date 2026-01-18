package com.example.librarybook_crud_api.mapper;

import com.example.librarybook_crud_api.dto.BookCreateRequestDto;
import com.example.librarybook_crud_api.dto.BookResponseDto;
import com.example.librarybook_crud_api.dto.BookUpdateRequestDto;
import com.example.librarybook_crud_api.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    //dto to entity
    public Book toEntity(BookCreateRequestDto dto)
    {
        Book book=new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setPublication(dto.publication());
        book.setPublicationYear(dto.publicationYear());
        book.setAvailableCopies(dto.availableCopies());
        return book;
    }


    //entity to dto
    public BookResponseDto toDto(Book book)
    {
        BookResponseDto responseDto=new BookResponseDto();
        responseDto.setId(book.getId());
        responseDto.setTitle(book.getTitle());
        responseDto.setAuthor(book.getAuthor());
        responseDto.setPublication(book.getPublication());
        responseDto.setPublicationYear(book.getPublicationYear());
        responseDto.setAvailableCopies(book.getAvailableCopies());
        return responseDto;
    }

    //update entity
    public void updateEntity(BookUpdateRequestDto dto,Book book)
    {
        if(book==null || dto==null){
            return;
        }

        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setPublication(dto.publication());
        book.setPublicationYear(dto.publicationYear());
        book.setAvailableCopies(dto.availableCopies());
    }
}
