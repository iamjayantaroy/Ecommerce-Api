package com.ecommerce.project.service;

import com.ecommerce.project.category.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

//    private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();

        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
        if (category == null){
            return "Category not found";
        }
        categoryRepository.delete(category);
        return "Category with categoryId: "+ categoryId + " deleted successfully.";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        List<Category> categories = categoryRepository.findAll();
        Optional<Category> Optionalcategory = categories.stream().filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if (Optionalcategory.isPresent()){
            Category existingcategory = Optionalcategory.get();
            existingcategory.setCategoryName(category.getCategoryName());
            Category savedCategory = categoryRepository.save(existingcategory);
            return existingcategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found");
        }
    }
}
