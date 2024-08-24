package com.emazon.stock.emazon.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@RequiredArgsConstructor
@Setter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String name;
    private final String description;
}
