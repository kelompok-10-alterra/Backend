package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.get.ClassDtoGet;
import com.capstone.kelompok10.model.dto.post.ClassDtoPost;
import com.capstone.kelompok10.model.entity.CategoryEntity;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.entity.InstructorEntity;
import com.capstone.kelompok10.model.entity.RoomEntity;
import com.capstone.kelompok10.model.entity.TypeEntity;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.service.interfaces.CategoryService;
import com.capstone.kelompok10.service.interfaces.ClassService;
import com.capstone.kelompok10.service.interfaces.InstructorService;
import com.capstone.kelompok10.service.interfaces.RoomService;
import com.capstone.kelompok10.service.interfaces.TypeService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ClassServiceImpl implements ClassService {
    ClassRepository classRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository){
        this.classRepository = classRepository;
    }

    @Override
    public List<ClassEntity> findAll() {
        log.info("Get all Class without DTO");
        List<ClassEntity> classes = new ArrayList<>();
        classRepository.findAll().forEach(classes::add);
        return classes;
    }
    
    @Override
    public Page<ClassEntity> findAllPagination(int offset, int pageSize) {
        log.info("Get all Class with Pagination");
        Page<ClassEntity> classes = classRepository.findAll(PageRequest.of(offset, pageSize));
        return classes;
    }

    @Override
    public Page<ClassEntity> findAllPaginationSort(int offset, int pageSize, String field){
        log.info("Get all Class with Pagination and Sorting");
        Page<ClassEntity> classes = classRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return classes;
    }

    @Override
    public List<ClassDtoGet> findAllDto() {
        log.info("Get all Class with DTO");
        List<ClassEntity> classs = classRepository.findAll();
        List<ClassDtoGet> classDtos = new ArrayList<>();
        
        classs.forEach(isi ->{
            ClassDtoGet dto = new ClassDtoGet();
            dto.setClassId(isi.getClassId());
            dto.setCapacity(isi.getCapacity());
            dto.setSchedule(isi.getSchedule().toString());
            dto.setPrice(isi.getPrice());
            dto.setStatus(isi.getStatus());
            dto.setImageUrl(isi.getImageUrl());
            dto.setInstructor(isi.getInstructor().getName());
            dto.setCategory(isi.getCategory().getName());
            dto.setRoom(isi.getRoom().getName());
            dto.setType(isi.getType().getName());

            classDtos.add(dto);
        });
        return classDtos;
    }

    @Override
    public ClassEntity getClassById(Long classId) {
        if(classRepository.findById(classId) == null){
            log.info("Class with id {} can't be found", classId);
            throw new IllegalStateException("Class not found");
        }else{
            log.info("Class with id {} found", classId);
            return classRepository.findById(classId).get();
        }
    }

    @Override
    public void updateClass(Long classId, ClassDtoPost classesDtoPost) {
        if(classRepository.findById(classId) == null){
            log.info("Class with id {} can't be found", classId);
            throw new IllegalStateException("Class not found");
        }else{
            ClassEntity class2 = classRepository.findById(classId).get();

            InstructorEntity instructorEntity = new InstructorEntity();
            instructorEntity.setInstructorId(classesDtoPost.getInstructorId());

            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setCategoryId(classesDtoPost.getCategoryId());

            RoomEntity roomEntity = new RoomEntity();
            roomEntity.setRoomId(classesDtoPost.getRoomId());

            TypeEntity typeEntity = new TypeEntity();
            typeEntity.setTypeId(classesDtoPost.getTypeId());

            if(instructorService.instructorExist(classesDtoPost.getInstructorId()) == true && categoryService.categoryExist(classesDtoPost.getCategoryId()) == true &&
                roomService.roomExist(classesDtoPost.getRoomId()) == true && typeService.typeExist(classesDtoPost.getTypeId()) == true){
                    class2.setStatus(classesDtoPost.getStatus());
                    class2.setCapacity(classesDtoPost.getCapacity());
                    class2.setSchedule(classesDtoPost.getSchedule());
                    class2.setPrice(classesDtoPost.getPrice());
                    class2.setImageUrl(classesDtoPost.getImageUrl());
                    class2.setCategory(categoryEntity);
                    class2.setInstructor(instructorEntity);
                    class2.setRoom(roomEntity);
                    class2.setType(typeEntity);
                    
                    classRepository.save(class2);
                    log.info("Class updated");
                }
            log.info("Failed to Update Class");
            throw new IllegalStateException("Instructor, Category, Room or Type didn't exist");  
        }
    }

    @Override
    public void deleteClass(Long classId) {
        if(classRepository.findById(classId) == null){
            log.info("Class with id {} can't be found", classId);
            throw new IllegalStateException("Class not found");
        }else{
            log.info("Class successfully deleted");
            classRepository.deleteById(classId);
        }
    }

	@Override
	public void createClassDto(ClassDtoPost classDtoPost) {
		ClassEntity classEntity = new ClassEntity();

        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setInstructorId(classDtoPost.getInstructorId());

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(classDtoPost.getCategoryId());

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomId(classDtoPost.getRoomId());

        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setTypeId(classDtoPost.getTypeId());
        if(instructorService.instructorExist(classDtoPost.getInstructorId()) == true && categoryService.categoryExist(classDtoPost.getCategoryId()) == true &&
            roomService.roomExist(classDtoPost.getRoomId()) == true && typeService.typeExist(classDtoPost.getTypeId()) == true){
                classEntity.setStatus(classDtoPost.getStatus());
                classEntity.setCapacity(classDtoPost.getCapacity());
                classEntity.setSchedule(classDtoPost.getSchedule());
                classEntity.setPrice(classDtoPost.getPrice());
                classEntity.setImageUrl(classDtoPost.getImageUrl());
                classEntity.setInstructor(instructorEntity);
                classEntity.setCategory(categoryEntity);
                classEntity.setRoom(roomEntity);
                classEntity.setType(typeEntity);
                classRepository.save(classEntity);
                log.info("Class created");
            }else{
                log.info("Failed to create Class");
                throw new IllegalStateException("Instructor, Category, Room or Type didn't exist");
            }
    }

    @Override
    public void classBooked(Long classId) {
        ClassEntity class2 = classRepository.findById(classId).get();
        Long capacity = class2.getCapacity();
        class2.setCapacity(capacity - 1);
        classRepository.save(class2);
    }

    @Override
    public Boolean classFull(Long classId) {
        ClassEntity class2 = classRepository.findById(classId).get();
        Long capacity = class2.getCapacity();
        if(capacity > 0){
            return false;
        }else{
            return true;
        }
    }

	@Override
	public Long classPrice(Long classId) {
		ClassEntity class2 = classRepository.findById(classId).get();
        Long price = class2.getPrice();
		return price;
	}

    @Override
    public void unBookClass(Long classId) {
        ClassEntity class2 = classRepository.findById(classId).get();
        Long capacity = class2.getCapacity();
        class2.setCapacity(capacity + 1);
        classRepository.save(class2);
    }
}
