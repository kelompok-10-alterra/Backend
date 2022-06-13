package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.get.CategoryDtoGet;
import com.capstone.kelompok10.model.dto.post.CategoryDtoPost;
import com.capstone.kelompok10.model.entity.CategoryEntity;
import com.capstone.kelompok10.repository.CategoryRepository;
import com.capstone.kelompok10.service.interfaces.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryEntity> getAllCategory() {
        List<CategoryEntity> categorys = new ArrayList<>();
        categoryRepository.findAll().forEach(categorys::add);
        return categorys;
    }

    @Override
    public List<CategoryDtoGet> getAllCategoryDto() {
        List<CategoryEntity> categorys = categoryRepository.findAll();
        List<CategoryDtoGet> CategoryDtos = new ArrayList<>();
        
        categorys.forEach(isi ->{
            CategoryDtoGet dto = new CategoryDtoGet();
            dto.setCategory_id(isi.getCategory_id());
            dto.setName(isi.getName());

            CategoryDtos.add(dto);
        });
        return CategoryDtos;
    }

    @Override
    public CategoryEntity getCategoryById(Long category_id) {
        return categoryRepository.findById(category_id).get();
    }

    @Override
    public void createCategory(CategoryEntity category) {
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long category_id, CategoryDtoPost categoryDtoPost) {
        CategoryEntity category2 = categoryRepository.findById(category_id).get();
        category2.setName(categoryDtoPost.getName());

        categoryRepository.save(category2);
    }

    @Override
    public void deleteCategory(Long category_id) {
        categoryRepository.deleteById(category_id);
    }

	@Override
	public void createCategoryDto(CategoryDtoPost categoryDtoPost) {
		CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDtoPost.getName());
		
        categoryRepository.save(categoryEntity);
	}
}
