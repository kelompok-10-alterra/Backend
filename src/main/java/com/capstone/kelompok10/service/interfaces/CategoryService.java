package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.CategoryDtoGet;
import com.capstone.kelompok10.model.dto.post.CategoryDtoPost;
import com.capstone.kelompok10.model.entity.CategoryEntity;

public interface CategoryService {
    List<CategoryEntity> getAllCategory();
    List<CategoryDtoGet> getAllCategoryDto();
    CategoryEntity getCategoryById(Long category_id);
    void createCategory(CategoryEntity category);
    void createCategoryDto(CategoryDtoPost categoryDtoPost);
    void updateCategory(Long category_id, CategoryEntity category);
    void deleteCategory(Long category_id);
}
