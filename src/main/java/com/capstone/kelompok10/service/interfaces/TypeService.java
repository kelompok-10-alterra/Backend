package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capstone.kelompok10.model.dto.get.TypeDtoGet;
import com.capstone.kelompok10.model.dto.post.TypeDtoPost;
import com.capstone.kelompok10.model.entity.TypeEntity;

public interface TypeService {
    List<TypeEntity> findAll();
    List<TypeDtoGet> findAllDto();
    Page<TypeEntity> findAllPagination(int offset, int pageSize);
    Page<TypeEntity> findAllPaginationSort(int offset, int pageSize, String field);
    TypeEntity getTypeById(Long typeId);
    void createType(TypeEntity type);
    void createTypeDto(TypeDtoPost typeDtoPost);
    void updateType(Long typeId, TypeDtoPost typeDtoPost);
    void deleteType(Long typeId);
}
