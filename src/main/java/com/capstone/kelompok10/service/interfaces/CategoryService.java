package com.capstone.kelompok10.service.interfaces;

import java.util.List;

// import com.capstone.kelompok10.model.dto.get.CategoryDtoGet;
import com.capstone.kelompok10.model.dto.post.CategoryDtoPost;
import com.capstone.kelompok10.model.entity.CategoryEntity;

public interface CategoryService {
    //CRUD Method
    List<CategoryEntity> findAll();
    // List<CategoryDtoGet> findAllDto();
    // Page<CategoryEntity> findAllPagination(int offset, int pageSize);
    // Page<CategoryEntity> findAllPaginationSort(int offset, int pageSize, String field);
    CategoryEntity getCategoryById(Long categoryId);
    void createCategoryDto(CategoryDtoPost categoryDtoPost);
    void updateCategory(Long categoryId, CategoryDtoPost categoryDtoPost);
    void deleteCategory(Long categoryId);
    void createCategory(CategoryEntity categoryEntity);

    //Verify Method
    Boolean categoryExist(Long categoryId);
}
