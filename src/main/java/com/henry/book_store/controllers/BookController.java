package com.henry.book_store.controllers;

import com.henry.book_store.dtos.BookDTO;
import com.henry.book_store.services.BookService;
import com.henry.book_store.utils.UrlUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UrlUtil.BOOK_URL)
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }


    @GetMapping("/{id_book}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id_book){
        return ResponseEntity.ok(bookService.getBookById(id_book));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookDTO>> getBookByTitle(@PathVariable String title){
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDTO>> getBookByAuthor(@PathVariable String author){

        return ResponseEntity.ok(bookService.getBookByAuthor(author));
    }




}
