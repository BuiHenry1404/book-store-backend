package com.henry.book_store.mappers;

import com.henry.book_store.dtos.CategoryDTO;
import com.henry.book_store.entities.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")

public interface CategoryMapper {

    CategoryDTO toCategoryDTO(CategoryEntity category);
    CategoryEntity toCategoryEntity(CategoryDTO category);
    List<CategoryDTO> toCategoryDTOs(List<CategoryEntity> categories);


}
