package com.henry.book_store.servieces.Impls;

import com.henry.book_store.constants.ErrorModelConstants;
import com.henry.book_store.dtos.CategoryDTO;
import com.henry.book_store.entities.CategoryEntity;
import com.henry.book_store.exceptions.AppException;
import com.henry.book_store.mappers.CategoryMapper;
import com.henry.book_store.repositories.CategoryRepository;
import com.henry.book_store.servieces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<CategoryEntity> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            throw new AppException(ErrorModelConstants.CATEGORY_EMPTY, "No categories found");
        }

        return categoryMapper.toCategoryDTOs(categories);
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryEntityDTO) {

        if(categoryRepository.existsByNameCategory(categoryEntityDTO.getNameCategory())) {
            throw new AppException(ErrorModelConstants.CATEGORY_EXISTS, "Category already exists");
        }

        CategoryEntity category = categoryMapper.toCategoryEntity(categoryEntityDTO);
        CategoryEntity savedCategory = categoryRepository.save(category);

        return categoryMapper.toCategoryDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(Integer idCategory, CategoryDTO category) {

        CategoryEntity categoryEntity = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new AppException(
                        ErrorModelConstants.CATEGORY_NOT_FOUND,
                        "Category with id " + idCategory + " not found"
                ));

      CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(categoryEntity);

      if(categoryDTO.getNameCategory().equals(category.getNameCategory())) {
          return categoryDTO;
      }

      if(categoryRepository.existsByNameCategory(category.getNameCategory())) {
          throw new AppException(ErrorModelConstants.CATEGORY_EXISTS, "Category already exists");
      }

      categoryEntity.setNameCategory(category.getNameCategory());
      CategoryEntity savedCategory = categoryRepository.save(categoryEntity);

      return categoryMapper.toCategoryDTO(savedCategory);
    }

    @Override
    public void deleteCategory(Integer idCategory) {

        Optional<CategoryEntity> category = categoryRepository.findById(idCategory);
        if(category.isEmpty()) {
            throw new AppException(ErrorModelConstants.CATEGORY_NOT_FOUND, "Category with id " + idCategory + " not found");
        }
        categoryRepository.deleteById(idCategory);
    }
}
