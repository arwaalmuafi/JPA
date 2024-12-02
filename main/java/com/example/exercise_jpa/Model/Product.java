package com.example.exercise_jpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @Column(nullable = false, length = 10)
    private String name;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be a positive number")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Category ID must not be null")
    @Column(nullable = false)
    private Integer categoryId;

    @NotNull(message = "Sales count must not be null")
    @Min(value = 0, message = "Sales count cannot be negative")
    @Column(nullable = false)
    private Integer salesCount;

    @AssertFalse(message = "Featured must be false by default")
    @Column(nullable = false)
    private boolean featured;
}
