package com.henry.book_store.repositories;

import com.henry.book_store.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    List<BookEntity> findByTitleContainingIgnoreCase(String title);
    List<BookEntity> findByAuthorContainingIgnoreCase(String author);
    List<BookEntity> findByCategories_CategoryId(Integer id);



}
