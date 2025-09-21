package com.henry.book_store.mappers;

import com.henry.book_store.dtos.BookDTO;
import com.henry.book_store.entities.BookEntity;
import com.henry.book_store.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "categoryNames", source = "categories", qualifiedByName = "mapCategoriesToNames")
    BookDTO toDTO(BookEntity book);

    List<BookDTO> toDTOList(List<BookEntity> books);

    BookEntity toEntity(BookDTO bookDTo);



    @Named("mapCategoriesToNames")
    default Set<String> mapCategoriesToNames(Set<CategoryEntity> categories) {
        if (categories == null) {
            return Set.of();
        }
        return categories.stream()
                .map(CategoryEntity::getNameCategory)
                .collect(Collectors.toSet());
    }
}
