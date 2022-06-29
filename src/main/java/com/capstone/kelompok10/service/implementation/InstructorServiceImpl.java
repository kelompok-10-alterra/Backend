package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.get.InstructorDtoGet;
import com.capstone.kelompok10.model.dto.post.InstructorDtoPost;
import com.capstone.kelompok10.model.entity.InstructorEntity;
import com.capstone.kelompok10.repository.InstructorRepository;
import com.capstone.kelompok10.service.interfaces.InstructorService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class InstructorServiceImpl implements InstructorService {
    InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }
    
    @Override
    public List<InstructorDtoGet> findAll() {
        List<InstructorEntity> instructors = instructorRepository.findAll();
        List<InstructorDtoGet> instructorDtos = new ArrayList<>();

        instructors.forEach(isi ->{
            InstructorDtoGet dto = new InstructorDtoGet();
            dto.setInstructorId(isi.getInstructorId());
            dto.setName(isi.getName());
            dto.setContact(isi.getContact());
            dto.setImageUrl(isi.getImageUrl());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());

            instructorDtos.add(dto);
        });
        return instructorDtos;
    }
    
    @Override
    public Page<InstructorEntity> findAllPagination(int offset, int pageSize) {
        log.info("Get all Instructor with Pagination");
        Page<InstructorEntity> instructor = instructorRepository.findAll(PageRequest.of(offset, pageSize));
        return instructor;
    }

    @Override
    public Page<InstructorEntity> findAllPaginationSort(int offset, int pageSize, String field){
        log.info("Get all Instructor with Pagination and Sorting");
        Page<InstructorEntity> instructor = instructorRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return instructor;
    }

    // @Override
    // public List<InstructorDtoGet> findAllDto() {
    //     log.info("Get all Instructor with DTO");
    //     List<InstructorEntity> instructors = instructorRepository.findAll();
    //     List<InstructorDtoGet> instructorDtos = new ArrayList<>();

    //     instructors.forEach(isi ->{
    //         InstructorDtoGet dto = new InstructorDtoGet();
    //         dto.setInstructorId(isi.getInstructorId());
    //         dto.setName(isi.getName());
    //         dto.setContact(isi.getContact());
    //         dto.setImageUrl(isi.getImageUrl());

    //         instructorDtos.add(dto);
    //     });
    //     return instructorDtos;
    // }

    @Override
    public InstructorEntity getInstructorById(Long instructorId) {
        if(instructorRepository.findById(instructorId) != null){
            log.info("Instructor with id {} found", instructorId);
            return instructorRepository.findById(instructorId).get();
        }else{
            log.info("Instructor with id {} not found", instructorId);
            throw new IllegalStateException("Instructor not found");
        }
    }

    @Override
    public void updateInstructor(Long instructorId, InstructorDtoPost instructorDtoPost) {
        if(instructorRepository.findById(instructorId) != null){
            InstructorEntity instructor2 = instructorRepository.findById(instructorId).get();
            instructor2.setName(instructorDtoPost.getName());
            instructor2.setContact(instructorDtoPost.getContact());
            instructor2.setImageUrl(instructorDtoPost.getImageUrl());
            instructorRepository.save(instructor2);
            log.info("Instructor updated");
        }else{
            log.info("Instructor with id {} not found", instructorId);
            throw new IllegalStateException("Instructor not found");
        }
    }

    @Override
    public void deleteInstructor(Long instructorId) {
        if(instructorRepository.findById(instructorId).isPresent() == true){
            log.info("Instructor with id {} successfully deleted", instructorId);
            instructorRepository.deleteById(instructorId);
        }else{
            log.info("Instructor with id {} not found", instructorId);
            throw new IllegalStateException("Instructor not found");
        }
    }

	@Override
	public void createInstructorDto(InstructorDtoPost instructorDtoPost) {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setName(instructorDtoPost.getName());
        instructorEntity.setContact(instructorDtoPost.getContact());
        instructorEntity.setImageUrl(instructorDtoPost.getImageUrl());
            
        instructorRepository.save(instructorEntity);
        log.info("Instructor created");
    }

    @Override
    public Boolean instructorExist(Long instructorId) {
        if(instructorRepository.findById(instructorId).isPresent() == true){
            return true;
        }else{
            return false;
        }
    }
}

