package com.henry.book_store.services;

import com.henry.book_store.dtos.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
}
