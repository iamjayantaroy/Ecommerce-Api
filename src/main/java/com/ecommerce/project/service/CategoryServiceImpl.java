package com.ecommerce.project.service;

import com.ecommerce.project.category.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private Long nextId = 1L;
    @Override
    public List<Category> getAllCategories() {
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
        if (category == null){
            return "Category not found";
        }
        return "Category with categoryId: "+ categoryId + " deleted successfully.";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> Optionalcategory = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if (Optionalcategory.isPresent()){
            Category existingcategory = Optionalcategory.get();
            existingcategory.setCategoryName(category.getCategoryName());
            return existingcategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found");
        }
    }
}
