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

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDtoGet> findAll() {
        List<CategoryEntity> categorys = categoryRepository.findAll();
        List<CategoryDtoGet> CategoryDtos = new ArrayList<>();
        
        categorys.forEach(isi ->{
            CategoryDtoGet dto = new CategoryDtoGet();
            dto.setCategoryId(isi.getCategoryId());
            dto.setName(isi.getName());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());

            CategoryDtos.add(dto);
        });
        return CategoryDtos;
    }
    
    // @Override
    // public Page<CategoryEntity> findAllPagination(int offset, int pageSize) {
    //     log.info("Get all Category with Pagination");
    //     Page<CategoryEntity> category = categoryRepository.findAll(PageRequest.of(offset, pageSize));
    //     return category;
    // }

    // @Override
    // public Page<CategoryEntity> findAllPaginationSort(int offset, int pageSize, String field){
    //     log.info("Get all Category with Pagination and Sorting");
    //     Page<CategoryEntity> category = categoryRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    //     return category;
    // }

    // @Override
    // public List<CategoryDtoGet> findAllDto() {
    //     log.info("Get all Category with DTO");
    //     List<CategoryEntity> categorys = categoryRepository.findAll();
    //     List<CategoryDtoGet> CategoryDtos = new ArrayList<>();
        
    //     categorys.forEach(isi ->{
    //         CategoryDtoGet dto = new CategoryDtoGet();
    //         dto.setCategoryId(isi.getCategoryId());
    //         dto.setName(isi.getName());

    //         CategoryDtos.add(dto);
    //     });
    //     return CategoryDtos;
    // }

    @Override
    public CategoryEntity getCategoryById(Long categoryId) {
        if(categoryRepository.findById(categoryId) == null){
            log.info("Can't find category with id {}", categoryId);
            throw new IllegalStateException("Cant find category you're trying to find");
        }else{
            log.info("Category with id {} found", categoryId);
            return categoryRepository.findById(categoryId).get();
        }
    }

    @Override
    public void updateCategory(Long categoryId, CategoryDtoPost categoryDtoPost) {
        if(categoryRepository.findById(categoryId) == null){
            log.info("Can't find category with id {}", categoryId);
            throw new IllegalStateException("Cant find category you're trying to find");
        }else{
            CategoryEntity category2 = categoryRepository.findById(categoryId).get();
            category2.setName(categoryDtoPost.getName());

            categoryRepository.save(category2);
            log.info("Category updated");
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if(categoryRepository.findById(categoryId) == null){
            log.info("Can't find category with id {}", categoryId);
            throw new IllegalStateException("Cant find category you're trying to find");
        }else{
            categoryRepository.deleteById(categoryId);
            log.info("Caegory with id {} successfully deleted", categoryId);
        }
    }

	@Override
	public void createCategoryDto(CategoryDtoPost categoryDtoPost) {
        if(categoryRepository.findByName(categoryDtoPost.getName()) == null){
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(categoryDtoPost.getName());
		
            categoryRepository.save(categoryEntity);
            log.info("Category created");
        }else{
            log.info("Category with name {} already exist", categoryDtoPost.getName());
            throw new IllegalStateException("Name you're trying to input already exist");
        }
	}

    @Override
    public Boolean categoryExist(Long categoryId) {
        if(categoryRepository.findById(categoryId).isPresent() == true){
            return true;
        }else{
            return false;
        }
    }
}
