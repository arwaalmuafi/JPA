package com.example.exercise_jpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;  // Changed to Integer or Long for proper ID mapping.

 @NotEmpty(message = "Name must not be empty")
 @Size(min = 3, message = "Name must be at least 3 characters long")
 @Column(nullable = false, length = 10)  // Ensures 'name' is not null and has a maximum length of 10
 private String name;

 @NotEmpty(message = "Description must not be empty")
 @Size(min = 3, message = "Description must be at least 3 characters long")
 @Column(nullable = false, length = 30)  // Ensures 'description' is not null and has a maximum length of 30
 private String description;

 @AssertFalse(message = "isActive must be false by default")
 @Column(nullable = false)  // Ensures 'isActive' is not null, can set default to false in DB if necessary
 private boolean isActive;  // Boolean with a default value of false (if needed, set default in DB)
}
