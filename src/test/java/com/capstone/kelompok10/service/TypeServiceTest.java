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

import com.capstone.kelompok10.model.dto.post.TypeDtoPost;
import com.capstone.kelompok10.model.entity.TypeEntity;
import com.capstone.kelompok10.repository.TypeRepository;
import com.capstone.kelompok10.service.implementation.TypeServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class TypeServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private TypeServiceImpl service;

    @Mock
    private TypeRepository repository;

    @Test
    void findAll(){
        List<TypeEntity> type = EASY_RANDOM.objects(TypeEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(type);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getTypeById(){
        TypeEntity type = EASY_RANDOM.nextObject(TypeEntity.class);

        when(repository.findById(type.getTypeId())).thenReturn(Optional.of(type));
        service.getTypeById(type.getTypeId());
        verify(repository, times(2)).findById(type.getTypeId());
    }

    @Test
    public void updateType() {
        TypeEntity type = EASY_RANDOM.nextObject(TypeEntity.class);
        TypeDtoPost newType = new TypeDtoPost();
        newType.setName("Baru");

        when(repository.findById(type.getTypeId())).thenReturn(Optional.of(type));
        service.updateType(type.getTypeId(), newType);
        verify(repository).save(type);
        verify(repository, times(2)).findById(type.getTypeId());
    }
    
    @Test
    public void createTypeDto(){
        TypeEntity typeEntity = new TypeEntity();
        TypeDtoPost typeDtoPost = EASY_RANDOM.nextObject(TypeDtoPost.class);
        typeEntity.setName(typeDtoPost.getName());
        typeEntity.setImageUrl(typeDtoPost.getImageUrl());

        service.createTypeDto(typeDtoPost);
        verify(repository).save(typeEntity);
    }

    @Test
    public void TypeExist(){
        TypeEntity type = EASY_RANDOM.nextObject(TypeEntity.class);

        when(repository.findById(type.getTypeId())).thenReturn(Optional.of(type));
        service.typeExist(type.getTypeId());
        assertEquals(true, service.typeExist(type.getTypeId()));
    }

    @Test
    public void deleteType(){
        TypeEntity typeEntity = EASY_RANDOM.nextObject(TypeEntity.class);

        when(repository.findById(typeEntity.getTypeId())).thenReturn(Optional.of(typeEntity));
        service.deleteType(typeEntity.getTypeId());
        verify(repository, times(1)).deleteById(typeEntity.getTypeId());
        verify(repository, times(1)).findById(typeEntity.getTypeId());
    }
}
