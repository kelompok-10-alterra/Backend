package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.InstructorDto;
import com.capstone.kelompok10.model.entity.InstructorEntity;

public interface InstructorService {
    List<InstructorEntity> getAllInstructor();
    List<InstructorDto> getAllInstructorDto();
    InstructorEntity getInstructorById(Long instructor_id);
    void createInstructor(InstructorEntity instructor);
    void updateInstructor(Long instructor_id, InstructorEntity instructor);
    void deleteInstructor(Long instructor_id);
}
