package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.ClassDto;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.service.interfaces.ClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {
    ClassRepository classRepository;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository){
        this.classRepository = classRepository;
    }

    @Override
    public List<ClassEntity> getAllClass() {
        List<ClassEntity> classs = new ArrayList<>();
        classRepository.findAll().forEach(classs::add);
        return classs;
    }

    @Override
    public List<ClassDto> getAllClassDto() {
        List<ClassEntity> classs = classRepository.findAll();
        List<ClassDto> classDtos = new ArrayList<>();
        
        classs.forEach(isi ->{
            ClassDto dto = new ClassDto();
            dto.setClass_id(isi.getClass_id());
            dto.setCapacity(isi.getCapacity());
            dto.setSchedule(isi.getSchedule().toString());
            dto.setPrice(isi.getPrice());
            dto.setStatus(isi.getStatus().toString());
            dto.setType(isi.getType().toString());
            dto.setInstructor(isi.getInstructor().getName());
            dto.setCategory(isi.getCategory().getName());
            dto.setRoom(isi.getRoom().getName());

            classDtos.add(dto);
        });
        return classDtos;
    }

    @Override
    public ClassEntity getClassById(Long class_id) {
        return classRepository.findById(class_id).get();
    }

    @Override
    public void createClass(ClassEntity classes) {
        classRepository.save(classes);
    }

    @Override
    public void updateClass(Long class_id, ClassEntity classes) {
        ClassEntity class2 = classRepository.findById(class_id).get();
        System.out.println(class2.toString());
        class2.setType(classes.getType());
        class2.setStatus(classes.getStatus());
        class2.setCapacity(classes.getCapacity());
        class2.setSchedule(classes.getSchedule());
        class2.setPrice(classes.getPrice());
        class2.setCategory(classes.getCategory());
        class2.setInstructor(classes.getInstructor());
        class2.setRoom(classes.getRoom());
        classRepository.save(class2);
    }

    @Override
    public void deleteClass(Long class_id) {
        classRepository.deleteById(class_id);
        
    }
}
