package com.storeManagement.Controller;


import com.storeManagement.Entity.CategoryEntity;
import com.storeManagement.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://store-mgmt-ang-sbjava.netlify.app","http://localhost:3000"})
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(path = "/allCategories")
    public List<CategoryEntity> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping(path = "/category/{catId}")
    public Optional<CategoryEntity> getCategoryById(@PathVariable Long catId){
        return categoryService.getCategoriesById(catId);
    }

    @PostMapping(path = "/addCategory")
    public CategoryEntity saveCategory(@RequestBody CategoryEntity category){
        return categoryService.addCategory(category);
    }

    @DeleteMapping(path = "/delCategory/{catId}")
    public String deleteCategory(@PathVariable Long catId){
        return categoryService.deleteCategory(catId);
    }

}
