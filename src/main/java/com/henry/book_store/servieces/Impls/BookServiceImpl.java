package com.henry.book_store.servieces.Impls;

import com.henry.book_store.constants.ErrorModelConstants;
import com.henry.book_store.entities.BookEntity;
import com.henry.book_store.entities.CategoryEntity;
import com.henry.book_store.exceptions.AppException;
import com.henry.book_store.repositories.BookRepository;
import com.henry.book_store.repositories.CategoryRepository;
import com.henry.book_store.servieces.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;





}
