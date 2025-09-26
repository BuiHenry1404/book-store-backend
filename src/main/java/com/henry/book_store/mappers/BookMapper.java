package com.henry.book_store.mappers;

import com.henry.book_store.dtos.BookDTO;
import com.henry.book_store.dtos.CategoryDTO;
import com.henry.book_store.entities.BookEntity;
import com.henry.book_store.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "categories", expression = "java(mapCategoriesToDTOs(book.getCategories()))")
    BookDTO bookToBookDTO(BookEntity book);

    @Mapping(target = "categories", expression = "java(mapCategoryDTOsToEntities(bookDTO.getCategories()))")
    BookEntity bookDTOToBook(BookDTO bookDTO);

    List<BookDTO> booksToBookDTOs(List<BookEntity> books);
    List<BookEntity> bookDTOsToBooks(List<BookDTO> bookDTOs);

    default Set<CategoryDTO> mapCategoriesToDTOs(Set<CategoryEntity> categories) {
        if (categories == null || categories.isEmpty()) {
            return new HashSet<>();
        }
        CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
        return categories.stream()
                .map(categoryMapper::toCategoryDTO)
                .collect(Collectors.toSet());
    }

    default Set<CategoryEntity> mapCategoryDTOsToEntities(Set<CategoryDTO> categoryDTOs) {
        if (categoryDTOs == null || categoryDTOs.isEmpty()) {
            return new HashSet<>();
        }
        CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
        return categoryDTOs.stream()
                .map(categoryMapper::toCategoryEntity)
                .collect(Collectors.toSet());
    }
}
