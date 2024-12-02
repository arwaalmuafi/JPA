package com.example.exercise_jpa.Service;

import com.example.exercise_jpa.Model.Category;
import com.example.exercise_jpa.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public Boolean updateCategory(int id, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory == null) {
            return false;
        }

        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());

        categoryRepository.save(existingCategory);
        return true;
    }

    public Boolean deleteCategory(int id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return false;
        }

        categoryRepository.delete(category);
        return true;
    }


    public ArrayList<Category> getActiveCategories() {
        List<Category> categories = categoryRepository.findAll();
        ArrayList<Category> activeCategories = new ArrayList<>();
        for (Category category : categories) {
            if (category.isActive()) {
                activeCategories.add(category);
            }
        }
        return activeCategories;
    }

    public boolean markAsActive(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            category.setActive(true);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }
}
