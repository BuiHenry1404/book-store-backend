package com.henry.book_store.dtos;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDTO {

    public interface CreateValidation {}
    public interface UpdateValidation {}

    private Integer id;

    @NotBlank(message = "Title is required", groups = CreateValidation.class)
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    private String title;

    @NotBlank(message = "Author is required", groups = CreateValidation.class)
    @Size(min = 1, max = 100, message = "Author must be between 1 and 100 characters")
    private String author;

    @NotNull(message = "Price is required", groups = CreateValidation.class)
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have at most 2 decimal places")
    private Double price;

    private boolean available = true;

    @Size(max = 500, message = "Image URL must not exceed 500 characters")
    private String imageUrl;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    private Set<CategoryDTO> categories = new HashSet<>();
}
