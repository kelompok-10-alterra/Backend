package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.TypeDtoGet;
import com.capstone.kelompok10.model.dto.post.TypeDtoPost;
import com.capstone.kelompok10.model.entity.TypeEntity;

public interface TypeService {
    List<TypeEntity> getAllType();
    List<TypeDtoGet> getAllTypeDto();
    TypeEntity getTypeById(Long type_id);
    void createType(TypeEntity type);
    void createTypeDto(TypeDtoPost typeDtoPost);
    void updateType(Long type_id, TypeDtoPost typeDtoPost);
    void deleteType(Long type_id);
}
