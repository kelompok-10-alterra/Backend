package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.kelompok10.model.dto.CategoryDto;
import com.capstone.kelompok10.model.entity.CategoryEntity;
import com.capstone.kelompok10.service.interfaces.CategoryService;

@RestController
@RequestMapping("/capstone/category")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getAllCategory(){
        List<CategoryEntity> categorys = categoryService.getAllCategory();
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<CategoryDto>> getAllCategoryDto(){
        List<CategoryDto> categoryDtos = categoryService.getAllCategoryDto();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<CategoryEntity> getCategoryById(@RequestParam("category_id") Long category_id){
        return new ResponseEntity<>(categoryService.getCategoryById(category_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable("category_id") Long category_id, @RequestBody CategoryEntity category){
        categoryService.updateCategory(category_id, category);
        return new ResponseEntity<>(categoryService.getCategoryById(category_id), HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<CategoryEntity> deleteCategory(@PathVariable("category_id") Long category_id){
        categoryService.deleteCategory(category_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
