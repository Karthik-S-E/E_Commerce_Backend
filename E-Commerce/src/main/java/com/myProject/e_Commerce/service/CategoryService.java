package com.myProject.e_Commerce.service;

import com.myProject.e_Commerce.model.Category;
import com.myProject.e_Commerce.payload.CategoryDTO;
import com.myProject.e_Commerce.payload.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {
     CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder);
     CategoryDTO createCategory(CategoryDTO categoryDTO);

     CategoryDTO deleteCategory(long categoryId);

     CategoryDTO updateCategory(CategoryDTO categoryDTO, long categoryId);
}
