package com.example.exercise_jpa.Repository;

import com.example.exercise_jpa.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
