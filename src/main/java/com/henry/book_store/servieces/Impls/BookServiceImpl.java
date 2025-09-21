package com.henry.book_store.servieces.Impls;

import com.henry.book_store.constants.ErrorModelConstants;
import com.henry.book_store.dtos.BookDTO;
import com.henry.book_store.entities.BookEntity;
import com.henry.book_store.exceptions.AppException;
import com.henry.book_store.mappers.BookMapper;
import com.henry.book_store.repositories.BookRepository;
import com.henry.book_store.repositories.CategoryRepository;
import com.henry.book_store.servieces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
//    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;


//    @Override
//    public BookEntity addBook(BookEntity book) {
//        return bookRepository.save(book);
//    }
//
//
    @Override
    public List<BookDTO> getAllBooks() {
        List<BookEntity> books = bookRepository.findAll();
        
        if (books.isEmpty()) {
            throw new AppException(ErrorModelConstants.BOOKS_IS_EMPTY, "Books is empty");
        }
        
        return bookMapper.toDTOList(books);
    }

    // Uncomment and implement these methods as needed
    /*
    @Override
    @Transactional
    public BookDTO createBook(BookDTO bookDTO) {
        BookEntity book = bookMapper.toEntity(bookDTO);
        BookEntity savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    @Transactional
    public BookDTO updateBook(Integer id, BookDTO bookDTO) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDTO.getTitle());
                    book.setAuthor(bookDTO.getAuthor());
                    book.setPrice(bookDTO.getPrice());
                    book.setAvailable(bookDTO.isAvailable());
                    book.setImageUrl(bookDTO.getImageUrl());
                    book.setDescription(bookDTO.getDescription());
                    // Handle categories update if needed
                    return bookMapper.toDTO(bookRepository.save(book));
                })
                .orElseThrow(() -> new AppException(ErrorModelConstants.BOOK_NOT_FOUND, "Book not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteBook(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new AppException(ErrorModelConstants.BOOK_NOT_FOUND, "Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
    */
}
