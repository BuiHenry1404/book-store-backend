package com.henry.book_store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Integer id;
    private String title;
    private String author;
    private double price;
    private boolean available;
    private String imageUrl;
    private String description;
    private Set<String> categoryNames = new HashSet<>();
}
