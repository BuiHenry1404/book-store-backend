package com.henry.book_store.controllers;

import com.henry.book_store.entities.CategoryEntity;
import com.henry.book_store.servieces.CategoryService;
import com.henry.book_store.utils.UrlUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(UrlUtil.CATEGORY_URL)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        try {
            log.info("Fetching all categories with books");
            List<CategoryEntity> categories = categoryService.getAllCategories();
            List<CategoryResponseDTO> response = categories.stream()
                    .map(categoryMapper::toResponseDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching categories: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id") Integer id) {
        try {
            log.info("Fetching category with id: {}", id);
            CategoryEntity category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(categoryMapper.toResponseDTO(category));
        } catch (Exception e) {
            log.error("Error fetching category: {}", e.getMessage(), e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO categoryRequest) {
        try {
            log.info("Adding new category: {}", categoryRequest.getNameCategory());
            CategoryEntity category = new CategoryEntity();
            category.setNameCategory(categoryRequest.getNameCategory());
            CategoryEntity savedCategory = categoryService.addCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(categoryMapper.toResponseDTO(savedCategory));
        } catch (Exception e) {
            log.error("Error adding category: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable("id") Integer id,
            @RequestBody CategoryEntity newCategory) {
        try {
            log.info("Updating category with id: {}", id);
            CategoryEntity updatedCategory = categoryService.updateCategory(id, newCategory);
            return ResponseEntity.ok(categoryMapper.toResponseDTO(updatedCategory));
        } catch (Exception e) {
            log.error("Error updating category: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Integer id) {
        try {
            log.info("Deleting category with id: {}", id);
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting category: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
