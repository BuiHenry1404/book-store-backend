package com.henry.book_store.services.Impls;

import com.henry.book_store.constants.ErrorModelConstants;
import com.henry.book_store.dtos.BookDTO;
import com.henry.book_store.entities.BookEntity;
import com.henry.book_store.exceptions.AppException;
import com.henry.book_store.mappers.BookMapper;
import com.henry.book_store.repositories.BookRepository;
import com.henry.book_store.services.BookService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @Override
    public BookDTO getBookById(Integer id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if(book.isPresent()){
            return bookMapper.bookToBookDTO(book.get());
        }
        throw new AppException(ErrorModelConstants.BOOKS_IS_EMPTY, "Not found a book" +
                                                                        "with id: " + book.get().getId());
    }

    @Override
    public List<BookDTO> getBookByTitle(String title) {
        List<BookEntity> books = bookRepository.findByTitleContainingIgnoreCase(title);
        if (books == null || books.isEmpty()) {
            throw new AppException(ErrorModelConstants.BOOKS_IS_EMPTY, "List of books is empty.");
        }
        return bookMapper.booksToBookDTOs(books);
    }

    @Override
    public List<BookDTO> getBookByAuthor(String author) {
        List<BookEntity> books = bookRepository.findByAuthorContainingIgnoreCase(author);

        if(books == null || books.isEmpty()) {
            throw new AppException(ErrorModelConstants.BOOKS_IS_EMPTY,
                    "List of books is empty.");
        }

        return bookMapper.booksToBookDTOs(books);
    }
    @Override
    public List<BookDTO> getBookByCategory(Integer categoryId) {
        List<BookEntity> books = bookRepository.findByCategories_Id(categoryId);
        if (books == null || books.isEmpty()) {
            throw new AppException(ErrorModelConstants.BOOKS_IS_EMPTY, "List of books is empty.");
        }
        return bookMapper.booksToBookDTOs(books);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        BookEntity book = bookMapper.bookDTOToBook(bookDTO);


        return bookMapper.bookToBookDTO(bookRepository.save(book));
    }

    @Override
    public BookDTO updateBook(Integer id, BookDTO book) {

        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorModelConstants.BOOKS_IS_EMPTY,
                        "Not found a book with id: " + id));

        BookDTO bookDTO = bookMapper.bookToBookDTO(bookEntity);

        // Check if another book with the same title already exists (excluding current book)
        if (bookRepository.existsByTitleAndIdNot(book.getTitle(), id)) {
            throw new AppException(ErrorModelConstants.BOOK_EXISTS,
                    "Book with title: " + book.getTitle() + " already exists");
        }

        // Update the bookDTO with new values from the request
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setAvailable(book.isAvailable());
        bookDTO.setCategories(book.getCategories());

        BookEntity updatedBookEntity = bookMapper.bookDTOToBook(bookDTO);
        updatedBookEntity.setId(id); //
        return bookMapper.bookToBookDTO(bookRepository.save(updatedBookEntity));
    }


}
