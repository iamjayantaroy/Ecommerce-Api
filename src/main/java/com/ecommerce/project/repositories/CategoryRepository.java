package com.ecommerce.project.repositories;

import com.ecommerce.project.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
