package com.project.shopapp.services;

import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.models.Category;

import java.util.List;

public interface iCategoryService {
    Category createCategory(CategoryDTO categoryDTO);
    Category getCategory(long id);

    List<Category> getAllCategory();

    Category updateCategory(long categoryId, CategoryDTO categoryDTO);
    void deleteCategory(long id);
}
