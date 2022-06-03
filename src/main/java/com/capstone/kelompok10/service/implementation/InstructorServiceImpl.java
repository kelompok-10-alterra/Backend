package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.InstructorDto;
import com.capstone.kelompok10.model.entity.InstructorEntity;
import com.capstone.kelompok10.repository.InstructorRepository;
import com.capstone.kelompok10.service.interfaces.InstructorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {
    InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<InstructorEntity> getAllInstructor() {
        List<InstructorEntity> instructors = new ArrayList<>();
        instructorRepository.findAll().forEach(instructors::add);
        return instructors;
    }

    @Override
    public List<InstructorDto> getAllInstructorDto() {
        List<InstructorEntity> instructors = instructorRepository.findAll();
        List<InstructorDto> instructorDtos = new ArrayList<>();

        instructors.forEach(isi ->{
            InstructorDto dto = new InstructorDto();
            dto.setInstructor_id(isi.getInstructor_id());
            dto.setName(isi.getName());
            dto.setPhone(isi.getPhone());

            instructorDtos.add(dto);
        });
        return instructorDtos;
    }

    @Override
    public InstructorEntity getInstructorById(Long instructor_id) {
        return instructorRepository.findById(instructor_id).get();
    }

    @Override
    public void createInstructor(InstructorEntity instructor) {
        instructorRepository.save(instructor);
    }

    @Override
    public void updateInstructor(Long instructor_id, InstructorEntity instructor) {
        InstructorEntity instructor2 = instructorRepository.findById(instructor_id).get();
        System.out.println(instructor2.toString());
        instructor2.setName(instructor.getName());
        instructor2.setPhone(instructor.getPhone());
        instructorRepository.save(instructor2);
    }

    @Override
    public void deleteInstructor(Long instructor_id) {
        instructorRepository.deleteById(instructor_id);
    }
}

