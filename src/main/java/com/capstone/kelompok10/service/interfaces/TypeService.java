package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.TypeDto;
import com.capstone.kelompok10.model.entity.TypeEntity;

public interface TypeService {
    List<TypeEntity> getAllType();
    List<TypeDto> getAllTypeDto();
    TypeEntity getTypeById(Long type_id);
    void createType(TypeEntity type);
    void updateType(Long type_id, TypeEntity type);
    void deleteType(Long type_id);
}
