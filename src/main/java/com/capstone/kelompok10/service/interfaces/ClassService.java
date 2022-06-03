package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.ClassDto;
import com.capstone.kelompok10.model.entity.ClassEntity;

public interface ClassService {
    List<ClassEntity> getAllClass();
    List<ClassDto> getAllClassDto();
    ClassEntity getClassById(Long class_id);
    void createClass(ClassEntity classes);
    void updateClass(Long class_id, ClassEntity classes);
    void deleteClass(Long class_id);
}
