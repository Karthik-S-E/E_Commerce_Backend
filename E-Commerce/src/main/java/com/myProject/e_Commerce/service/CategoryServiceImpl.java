package com.myProject.e_Commerce.service;

import com.myProject.e_Commerce.config.AppConstants;
import com.myProject.e_Commerce.exception.APIException;
import com.myProject.e_Commerce.exception.ResourceNotFoundException;
import com.myProject.e_Commerce.model.Category;
import com.myProject.e_Commerce.payload.CategoryDTO;
import com.myProject.e_Commerce.payload.CategoryResponse;
import com.myProject.e_Commerce.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryServiceImpl implements CategoryService {

@Autowired
    private CategoryRepository categoryRepository;

@Autowired
private ModelMapper modelMapper;
    @Override
    public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder) {
        //Sorting
        Sort sortCategories=sortOrder.equalsIgnoreCase(AppConstants.SORT_ORDER)
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        //Pagination is done  by next immediate 2 lines
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sortCategories);
        Page<Category> page=categoryRepository.findAll(pageable);

           List<Category> categories = page.getContent();
           if (categories.isEmpty())
               throw new APIException("No Category created till now...");

           //mapping category to category DTO
           List<CategoryDTO> categoryDTOs = categories.stream()//converted all list<Categories>to Stream<Categories>
                   .map(category -> modelMapper.map(category, CategoryDTO.class))// actual syntax for maodelmapper to map entity to DTO class
                   .collect(toList());

           //setting content for Category response bcs it should return CategoryResponse
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setContent(categoryDTOs);
            //Setting pagination details
            categoryResponse.setPageNumber(page.getNumber());
            categoryResponse.setPageSize(page.getSize());
            categoryResponse.setTotalElement(page.getTotalElements());
            categoryResponse.setTotalPage(page.getTotalPages());
            categoryResponse.setLastPage(page.isLast());
        return categoryResponse;
    }

@Override
public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category= modelMapper.map(categoryDTO,Category.class);
    Category savedcategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
    if(savedcategory != null){
        throw new APIException("Category with the name "+categoryDTO.getCategoryName()+" already exists");
    }
   Category categorySaved= categoryRepository.save(category);
    return modelMapper.map(categorySaved,CategoryDTO.class);
}
    @Override
    public CategoryDTO deleteCategory(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->
                new ResourceNotFoundException("Category"," CategoryId ",categoryId));
        category.setCategoryId(categoryId);
        categoryRepository.delete(category);
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, long categoryId) {
        Category category= modelMapper.map(categoryDTO,Category.class);
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category"," CategoryId ",categoryId));
        category.setCategoryId(categoryId);
        savedCategory=  categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }
}
