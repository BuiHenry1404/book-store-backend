package com.henry.book_store.mappers;

import com.henry.book_store.dtos.BookDTO;
import com.henry.book_store.entities.BookEntity;
import com.henry.book_store.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "categories", expression = "java(mapCategoriesToNames(book.getCategories()))")
    BookDTO bookToBookDTO(BookEntity book);

    @Mapping(target = "categories", ignore = true)
    BookEntity bookDTOToBook(BookDTO bookDTO);

    List<BookDTO> booksToBookDTOs(List<BookEntity> books);
    List<BookEntity> bookDTOsToBooks(List<BookDTO> bookDTOs);

    default Set<String> mapCategoriesToNames(Set<CategoryEntity> categories) {
        if (categories == null || categories.isEmpty()) {
            return new HashSet<>();
        }
        return categories.stream()
                .map(CategoryEntity::getNameCategory)
                .collect(Collectors.toSet());
    }
}
