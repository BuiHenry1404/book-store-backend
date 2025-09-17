package com.henry.book_store.servieces;

import com.henry.book_store.entities.BookEntity;
import java.util.List;


public interface BookService {

    BookEntity addBook(BookEntity book);

    BookEntity updateBook(Integer id, BookEntity book);

    void deleteBook(Integer id);

    List<BookEntity> getAllBooks();

}
