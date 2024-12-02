package com.example.exercise_jpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @NotEmpty(message = "name must be not empty")
    @Size(min = 3, message = "have to be more than 3 length long")
    @Column(columnDefinition = "varchar (10) not null")
    private String name;

    @NotNull(message = "connote be null")
    @Column(columnDefinition ="int not null")
    private int rating;



}