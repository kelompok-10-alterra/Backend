package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.InstructorDtoGet;
import com.capstone.kelompok10.model.dto.post.InstructorDtoPost;
import com.capstone.kelompok10.model.entity.InstructorEntity;

public interface InstructorService {
    List<InstructorEntity> getAllInstructor();
    List<InstructorDtoGet> getAllInstructorDto();
    InstructorEntity getInstructorById(Long instructor_id);
    void createInstructor(InstructorEntity instructor);
    void createInstructorDto(InstructorDtoPost instructorDtoPost);
    void updateInstructor(Long instructor_id, InstructorDtoPost instructorDtoPost);
    void deleteInstructor(Long instructor_id);
}
