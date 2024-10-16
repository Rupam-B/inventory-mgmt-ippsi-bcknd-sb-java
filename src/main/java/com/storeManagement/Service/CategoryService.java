package com.storeManagement.Service;


import com.storeManagement.Entity.CategoryEntity;
import com.storeManagement.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    @Autowired
    CategoryRepository categoryRepository;


    public List<CategoryEntity> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Optional<CategoryEntity> getCategoriesById(Long catId){
        return categoryRepository.findById(catId);
    }

    public CategoryEntity addCategory(CategoryEntity category){
        return categoryRepository.save(category);
    }

    public String deleteCategory (Long catId){
        categoryRepository.deleteById(catId);
        return "Category Deleted SuccessFully";
    }
}
