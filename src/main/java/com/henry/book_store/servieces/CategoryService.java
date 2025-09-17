package com.henry.book_store.servieces;

import com.henry.book_store.entities.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> getAllCategories();

    CategoryEntity addCategory(CategoryEntity category);

    CategoryEntity updateCategory(Integer idCategory, CategoryEntity category);

    void deleteCategory(Integer idCategory);

}
