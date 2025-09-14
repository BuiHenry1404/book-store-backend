package com.henry.book_store.controllers;

import com.henry.book_store.entities.BookEntity;
import com.henry.book_store.servieces.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookEntity>> getAllBooks() {
        List<BookEntity> books = bookService.getAllBooks();

        return ResponseEntity.ok(books);
    }


    @PostMapping
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity book) {
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookEntity> updateBook(@PathVariable("id") Integer id,@RequestBody BookEntity book) {
        BookEntity bookUpdated = bookService.updateBook(id, book);
        return ResponseEntity.ok(bookUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
