package com.henry.book_store.servieces.Impls;

import com.henry.book_store.constants.ErrorModelConstants;
import com.henry.book_store.entities.BookEntity;
import com.henry.book_store.entities.CategoryEntity;
import com.henry.book_store.exceptions.AppException;
import com.henry.book_store.repositories.BookRepository;
import com.henry.book_store.repositories.CategoryRepository;
import com.henry.book_store.servieces.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;

    }


    @Override
    public BookEntity addBook(BookEntity book) {
        return bookRepository.save(book);
    }


    @Override
    public BookEntity updateBook(Integer id, BookEntity book) {

        Optional<BookEntity> bookEntity = bookRepository.findById(id);

        if(bookEntity.isPresent()) {
            BookEntity bookEntityNew = bookEntity.get();
            bookEntityNew.setTitle(book.getTitle());
            bookEntityNew.setAuthor(book.getAuthor());
            bookEntityNew.setPrice(book.getPrice());
            bookEntityNew.setAvailable(book.isAvailable());
            return bookRepository.save(bookEntityNew);
        }
        throw new RuntimeException("Book not found with id: " + id);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookEntity> getAllBooks() {

        List<BookEntity> books = bookRepository.findAll();

        if(books.isEmpty()) {
            throw new AppException(ErrorModelConstants.BOOKS_IS_EMPTY, "Books is empty");
        }

        return books;

    }
}
