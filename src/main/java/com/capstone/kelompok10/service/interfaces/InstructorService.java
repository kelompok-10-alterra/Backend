package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capstone.kelompok10.model.dto.get.InstructorDtoGet;
import com.capstone.kelompok10.model.dto.post.InstructorDtoPost;
import com.capstone.kelompok10.model.entity.InstructorEntity;

public interface InstructorService {
    List<InstructorEntity> findAll();
    List<InstructorDtoGet> findAllDto();
    Page<InstructorEntity> findAllPagination(int offset, int pageSize);
    Page<InstructorEntity> findAllPaginationSort(int offset, int pageSize, String field);
    InstructorEntity getInstructorById(Long instructorId);
    void createInstructor(InstructorEntity instructor);
    void createInstructorDto(InstructorDtoPost instructorDtoPost);
    void updateInstructor(Long instructorId, InstructorDtoPost instructorDtoPost);
    void deleteInstructor(Long instructorId);
}
