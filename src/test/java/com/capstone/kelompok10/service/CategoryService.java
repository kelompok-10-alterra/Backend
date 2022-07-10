package com.capstone.kelompok10.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capstone.kelompok10.model.dto.post.CategoryDtoPost;
import com.capstone.kelompok10.model.entity.CategoryEntity;
import com.capstone.kelompok10.repository.CategoryRepository;
import com.capstone.kelompok10.service.implementation.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CategoryService {
    private final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private CategoryServiceImpl service;

    @Mock
    private CategoryRepository repository;

    @Test
    void findAll(){
        List<CategoryEntity> category = EASY_RANDOM.objects(CategoryEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(category);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getCategoryById(){
        CategoryEntity category = EASY_RANDOM.nextObject(CategoryEntity.class);

        when(repository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        service.getCategoryById(category.getCategoryId());
        verify(repository, times(2)).findById(category.getCategoryId());
    }

    @Test
    public void updateCategory() {
        CategoryEntity category = EASY_RANDOM.nextObject(CategoryEntity.class);
        CategoryDtoPost newCategory = new CategoryDtoPost();
        newCategory.setName("Baru");

        when(repository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        service.updateCategory(category.getCategoryId(), newCategory);
        verify(repository).save(category);
        verify(repository, times(2)).findById(category.getCategoryId());
    }
    
    @Test
    public void createCategoryDto(){
        CategoryEntity categoryEntity = new CategoryEntity();
        CategoryDtoPost categoryDtoPost = EASY_RANDOM.nextObject(CategoryDtoPost.class);
        categoryEntity.setName(categoryDtoPost.getName());

        service.createCategoryDto(categoryDtoPost);
        verify(repository).save(categoryEntity);
    }

    @Test
    public void categoryExist(){
        CategoryEntity category = EASY_RANDOM.nextObject(CategoryEntity.class);

        when(repository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        service.categoryExist(category.getCategoryId());
        assertEquals(true, service.categoryExist(category.getCategoryId()));
    }
}
