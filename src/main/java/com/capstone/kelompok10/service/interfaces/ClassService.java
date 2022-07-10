package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.ClassDtoGet;
import com.capstone.kelompok10.model.dto.post.ClassDtoPost;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.payload.GetUserByClass;

public interface ClassService {
    List<ClassDtoGet> findAll();
    List<ClassDtoGet> findAll(String keyword);
    // Page<ClassEntity> findAllPagination(int offset, int pageSize);
    // Page<ClassEntity> findAllPaginationSort(int offset, int pageSize, String field);
    ClassDtoGet getClassByIdDto(Long classId);
    ClassEntity getClassById(Long classId);
    void createClassDto(ClassDtoPost classDtoPost);
    void updateClass(Long classId, ClassDtoPost classesDtoPost);
    void deleteClass(Long classId);
    List<GetUserByClass> getBookingByClassId(Long classId);
    List<ClassDtoGet> getClassByType(String typeName);
    List<ClassDtoGet> getClassByCategoryName(String categoryName);

    // Verify Method
    void classBooked(Long classId);
    void unBookClass(Long classId);
    Boolean classFull(Long classId);
    Long classPrice(Long classId);
    Boolean classExist(Long classId);
    Long classRating(Long classId);
}
