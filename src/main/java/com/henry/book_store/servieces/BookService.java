package com.henry.book_store.servieces;

import com.henry.book_store.dtos.BookDTO;
import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    
    // Uncomment and update these methods when implementing them
    // BookDTO getBookById(Integer id);
    // BookDTO createBook(BookDTO bookDTO);
    // BookDTO updateBook(Integer id, BookDTO bookDTO);
    // void deleteBook(Integer id);
}
