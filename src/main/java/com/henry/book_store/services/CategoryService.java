package com.henry.book_store.services;

import com.henry.book_store.dtos.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();


    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Integer idCategory, CategoryDTO categoryDTO);

    void deleteCategory(Integer idCategory);
}
