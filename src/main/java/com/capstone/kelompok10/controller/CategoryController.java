package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.capstone.kelompok10.model.dto.get.CategoryDtoGet;
import com.capstone.kelompok10.model.dto.post.CategoryDtoPost;
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

    @GetMapping("/userAccess/getAllCategory")
    public ResponseEntity<List<CategoryEntity>> findAll(){
        List<CategoryEntity> categorys = categoryService.findAll();
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    @GetMapping("/userAccess/{offset}/{pageSize}")
    public ResponseEntity<Page<CategoryEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
        Page<CategoryEntity> categorys = categoryService.findAllPagination(offset, pageSize);
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    @GetMapping("/userAccess/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<CategoryEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        Page<CategoryEntity> categorys = categoryService.findAllPaginationSort(offset, pageSize, field);
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    @GetMapping("userAccess/getAllCategoryWithDto")
    public ResponseEntity<List<CategoryDtoGet>> findAllDto(){
        List<CategoryDtoGet> categoryDtos = categoryService.findAllDto();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getCategoryById")
    public ResponseEntity<CategoryEntity> getCategoryById(@RequestParam("categoryId") Long categoryId){
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @PostMapping("/adminAccess/createNewCategory")
    public ResponseEntity<CategoryDtoPost> createCategoryDto(@RequestBody CategoryDtoPost categoryDtoPost){
        categoryService.createCategoryDto(categoryDtoPost);
        return new ResponseEntity<>(categoryDtoPost, HttpStatus.OK);
    }

    @PutMapping("/adminAccess/updateCategory/{categoryId}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryDtoPost categoryDtoPost){
        categoryService.updateCategory(categoryId, categoryDtoPost);
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteCategory/{categoryId}")
    public ResponseEntity<CategoryEntity> deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
