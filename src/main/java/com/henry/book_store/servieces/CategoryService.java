package com.henry.book_store.servieces;

import com.henry.book_store.dtos.CategoryDTO;
import com.henry.book_store.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();


    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Integer idCategory, CategoryDTO categoryDTO);

    void deleteCategory(Integer idCategory);
}
