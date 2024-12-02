package com.example.exercise_jpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    @Column(columnDefinition = "varchar (10) not null")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must be at least 6 characters long, and include letters and digits")
    @Column(columnDefinition = "varchar (10) not null")
    private String password;

    @Email(message = "Email must be valid")
    @Column(columnDefinition = "varchar (10) not null unique")
    private String email;

    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be 'Admin' or 'Customer'")
    @Column(columnDefinition = "varchar (10) not null")
    private String role;

    @NotNull(message = "Balance must not be null")
    @Positive(message = "Balance must be positive")
    @Column(columnDefinition = "double  not null")
    private Double balance;


}
