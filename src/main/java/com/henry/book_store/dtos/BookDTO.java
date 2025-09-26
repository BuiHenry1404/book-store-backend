package com.henry.book_store.dtos;


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

    private Integer id;
    
    private String title;
    
    private String author;
    
    private double price;
    
    private boolean available = true;
    
    private String imageUrl;
    
    private String description;
    
    private Set<CategoryDTO> categories = new HashSet<>();
}
