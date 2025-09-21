package com.henry.book_store.servieces.Impls;

import com.henry.book_store.constants.ErrorModelConstants;
import com.henry.book_store.entities.CategoryEntity;
import com.henry.book_store.exceptions.AppException;
import com.henry.book_store.repositories.CategoryRepository;
import com.henry.book_store.servieces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    public final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        if(categoryRepository.findAll().isEmpty()){
            throw new AppException(ErrorModelConstants.CATEGORY_EMPTY, "Category is empty");
        }
        return categoryRepository.findAll();
    }
    
    @Override
    public CategoryEntity getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorModelConstants.CATEGORY_NOT_FOUND, "Category not found with id: " + id));
    }

    @Override
    public CategoryEntity addCategory(CategoryEntity category) {

      if(categoryRepository.existsByNameCategory(category.getNameCategory())) {
            throw new AppException(ErrorModelConstants.CATEGORY_EXISTS, "Category already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity updateCategory(Integer idCategory, CategoryEntity category) {
        return categoryRepository.findById(idCategory)
                .map(exitstingCategory -> {
                    if(categoryRepository.existsByIdNotAndNameCategory(idCategory, category.getNameCategory())){
                        throw new AppException(ErrorModelConstants.CATEGORY_EXISTS, "Category already exists");
                    }
                    exitstingCategory.setNameCategory(category.getNameCategory());
                    if(exitstingCategory.getNameCategory() == null){
                        throw new AppException(ErrorModelConstants.CATEGORY_NAME_EMPTY, "Category name not empty");
                    }
                    return categoryRepository.save(exitstingCategory);
                }).orElseThrow(() -> {
                    throw new AppException(ErrorModelConstants.CATEGORY_NOT_FOUND, "Category not found");
                });

    }


    @Override
    public void deleteCategory(Integer idCategory) {

        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(idCategory);

        if(categoryEntity.isPresent()) {
            categoryRepository.deleteById(idCategory);
        }
    }


}
