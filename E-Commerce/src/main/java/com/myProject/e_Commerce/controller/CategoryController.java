package com.myProject.e_Commerce.controller;


import com.myProject.e_Commerce.config.AppConstants;
import com.myProject.e_Commerce.exception.APIException;
import com.myProject.e_Commerce.model.Category;
import com.myProject.e_Commerce.payload.CategoryDTO;
import com.myProject.e_Commerce.payload.CategoryResponse;
import com.myProject.e_Commerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

   @GetMapping("/api/public/categories")
    private ResponseEntity<CategoryResponse> getAllCategories(
      @RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
      @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
      @RequestParam(name="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
      @RequestParam(name="sortOrder",defaultValue = AppConstants.SORT_ORDER,required = false) String sortOrder
   ){
       CategoryResponse categoryResponse = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
         return new ResponseEntity<>(categoryResponse,HttpStatus.OK);

    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO saved=    categoryService.createCategory(categoryDTO);
//               return ResponseEntity.ok("Category ( "+categoryDTO.getCategoryName()+ " ) has been added successfully")
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
   }
@DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable long categoryId){
    CategoryDTO status=  categoryService.deleteCategory(categoryId);
           return  ResponseEntity.ok(status);
    }
    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable long categoryId) {
        CategoryDTO savedCategory=  categoryService.updateCategory(categoryDTO,categoryId);
            return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }
}
