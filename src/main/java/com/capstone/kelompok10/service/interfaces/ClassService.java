package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.ClassDtoGet;
import com.capstone.kelompok10.model.dto.post.ClassDtoPost;
import com.capstone.kelompok10.model.entity.ClassEntity;

public interface ClassService {
    List<ClassEntity> getAllClass();
    List<ClassDtoGet> getAllClassDto();
    ClassEntity getClassById(Long class_id);
    void createClass(ClassEntity classes);
    void createClassDto(ClassDtoPost classDtoPost);
    void updateClass(Long class_id, ClassEntity classes);
    void deleteClass(Long class_id);
}
