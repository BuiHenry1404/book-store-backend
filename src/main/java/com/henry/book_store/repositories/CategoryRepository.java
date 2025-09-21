package com.henry.book_store.repositories;

import com.henry.book_store.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    boolean existsByNameCategory(String categoryName);
    boolean existsByIdNotAndNameCategory(Integer id, String categoryName);

    Set<CategoryEntity> findByNameCategoryIn(Set<String> categoryNames);
}
