package com.henry.book_store.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "name_category", nullable = false)
    private String nameCategory;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<BookEntity> books = new HashSet<>();

}
