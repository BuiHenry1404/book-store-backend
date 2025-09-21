package com.henry.book_store.servieces.Impls;

import com.henry.book_store.constants.ErrorModelConstants;
import com.henry.book_store.dtos.BookDTO;
import com.henry.book_store.entities.BookEntity;
import com.henry.book_store.exceptions.AppException;
import com.henry.book_store.mappers.BookMapper;
import com.henry.book_store.repositories.BookRepository;
import com.henry.book_store.servieces.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    @Override
    public List<BookDTO> getAllBooks() {

        List<BookEntity> books = bookRepository.findAll();

        if(books.isEmpty()) {
            throw new AppException(ErrorModelConstants.BOOKS_IS_EMPTY, "List of books is empty");
        }

        return bookMapper.booksToBookDTOs(books);
    }
}
