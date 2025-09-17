package com.henry.book_store.repositories;

import com.henry.book_store.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    boolean existsByNameCategory(String categoryName);
    boolean existsByIdNotAndNameCategory(Integer id, String nameCategory);

}
