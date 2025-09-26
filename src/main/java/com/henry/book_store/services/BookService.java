package com.henry.book_store.services;

import com.henry.book_store.dtos.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    BookDTO getBookById(Integer id);

    List<BookDTO> getBookByTitle(String title);

    List<BookDTO> getBookByAuthor(String author);

    List<BookDTO> getBookByCategory(Integer categoryId);

    BookDTO createBook(BookDTO bookDTO);

    BookDTO updateBook(Integer id, BookDTO bookDTO);
}
