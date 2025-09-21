package com.henry.book_store.controllers;

import com.henry.book_store.dtos.CategoryDTO;
import com.henry.book_store.servieces.Impls.CategoryServiceImpl;
import com.henry.book_store.utils.UrlUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlUtil.CATEGORY_URL)
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(CategoryDTO categoryEntityDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryEntityDTO));
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer idCategory,@RequestBody CategoryDTO category) {
        return ResponseEntity.ok(categoryService.updateCategory(idCategory, category));
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer idCategory) {
        categoryService.deleteCategory(idCategory);
        return ResponseEntity.noContent().build();
    }


}
