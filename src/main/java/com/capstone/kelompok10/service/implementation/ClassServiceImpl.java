package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.get.ClassDtoGet;
import com.capstone.kelompok10.model.dto.post.ClassDtoPost;
import com.capstone.kelompok10.model.entity.BookingEntity;
import com.capstone.kelompok10.model.entity.CategoryEntity;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.entity.InstructorEntity;
import com.capstone.kelompok10.model.entity.RoomEntity;
import com.capstone.kelompok10.model.entity.TypeEntity;
import com.capstone.kelompok10.model.payload.GetUserByClass;
import com.capstone.kelompok10.repository.BookingRepository;
import com.capstone.kelompok10.repository.CategoryRepository;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.repository.RoomRepository;
import com.capstone.kelompok10.repository.TypeRepository;
import com.capstone.kelompok10.service.interfaces.CategoryService;
import com.capstone.kelompok10.service.interfaces.ClassService;
import com.capstone.kelompok10.service.interfaces.InstructorService;
import com.capstone.kelompok10.service.interfaces.RoomService;
import com.capstone.kelompok10.service.interfaces.TypeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    ClassRepository classRepository;

    @Autowired
    public CategoryRepository categoryRepository;
    
    @Autowired
    public BookingRepository bookingRepository;

    @Autowired
    public TypeRepository typeRepository;

    @Autowired
    public RoomRepository roomRepository;

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
    public List<ClassDtoGet> findAll() {
        List<ClassEntity> classs = classRepository.findAll();
        List<ClassDtoGet> classDtos = new ArrayList<>();
        
        classs.forEach(isi ->{
            ClassDtoGet dto = new ClassDtoGet();
            dto.setClassId(isi.getClassId());
            dto.setName(isi.getName());
            dto.setDescription(isi.getDescription());
            dto.setStatus(isi.getStatus());
            dto.setCapacity(isi.getCapacity());
            dto.setBooked(isi.getBooked());
            dto.setSchedule(isi.getSchedule());
            dto.setHour(isi.getHour());
            dto.setPrice(isi.getPrice());
            dto.setImageUrl(isi.getType().getImageUrl());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setTypeId(isi.getType().getTypeId());
            dto.setTypeName(isi.getType().getName());
            dto.setInstructureId(isi.getInstructor().getInstructorId());
            dto.setInstructureName(isi.getInstructor().getName());
            dto.setContact(isi.getInstructor().getContact());
            dto.setCategoryId(isi.getCategory().getCategoryId());
            dto.setCategoryName(isi.getCategory().getName());
            dto.setRoomId(isi.getRoom().getRoomId());
            dto.setRoomName(isi.getRoom().getName());
            dto.setRating(classRating(isi.getClassId()));
            dto.setMeetUrl(isi.getMeetUrl());

            classDtos.add(dto);
        });
        return classDtos;
    }

    @Override
    public List<ClassDtoGet> findAll(String keyword) {
        List<ClassEntity> classs = classRepository.findAll(keyword);
        List<ClassDtoGet> classDtos = new ArrayList<>();
        
        classs.forEach(isi ->{
            ClassDtoGet dto = new ClassDtoGet();
            dto.setClassId(isi.getClassId());
            dto.setName(isi.getName());
            dto.setDescription(isi.getDescription());
            dto.setStatus(isi.getStatus());
            dto.setCapacity(isi.getCapacity());
            dto.setBooked(isi.getBooked());
            dto.setSchedule(isi.getSchedule());
            dto.setHour(isi.getHour());
            dto.setPrice(isi.getPrice());
            dto.setImageUrl(isi.getType().getImageUrl());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setTypeId(isi.getType().getTypeId());
            dto.setTypeName(isi.getType().getName());
            dto.setInstructureId(isi.getInstructor().getInstructorId());
            dto.setInstructureName(isi.getInstructor().getName());
            dto.setContact(isi.getInstructor().getContact());
            dto.setCategoryId(isi.getCategory().getCategoryId());
            dto.setCategoryName(isi.getCategory().getName());
            dto.setRoomId(isi.getRoom().getRoomId());
            dto.setRoomName(isi.getRoom().getName());
            dto.setRating(classRating(isi.getClassId()));
            dto.setMeetUrl(isi.getMeetUrl());

            classDtos.add(dto);
        });
        return classDtos;
    }
    
    // @Override
    // public Page<ClassEntity> findAllPagination(int offset, int pageSize) {
    //     log.info("Get all Class with Pagination");
    //     Page<ClassEntity> classes = classRepository.findAll(PageRequest.of(offset, pageSize));
    //     List<ClassDtoGet> classDto = new ArrayList<>();
    //     classes.forEach(isi ->{
    //         ClassDtoGet dto = new ClassDtoGet();
    //         dto.setClassId(isi.getClassId());
    //         dto.setName(isi.getName());
    //         dto.setDescription(isi.getDescription());
    //         dto.setStatus(isi.getStatus());
    //         dto.setCapacity(isi.getCapacity());
    //         dto.setBooked(isi.getBooked());
    //         dto.setSchedule(isi.getSchedule());
    //         dto.setHour(isi.getHour());
    //         dto.setPrice(isi.getPrice());
    //         dto.setImageUrl(isi.getType().getImageUrl());
    //         dto.setCreatedAt(isi.getCreated_at().toString());
    //         dto.setUpdatedAt(isi.getUpdated_at().toString());
    //         dto.setTypeId(isi.getType().getTypeId());
    //         dto.setTypeName(isi.getType().getName());
    //         dto.setInstructureId(isi.getInstructor().getInstructorId());
    //         dto.setInstructureName(isi.getInstructor().getName());
    //         dto.setContact(isi.getInstructor().getContact());
    //         dto.setCategoryId(isi.getCategory().getCategoryId());
    //         dto.setCategoryName(isi.getCategory().getName());
    //         dto.setRoomId(isi.getRoom().getRoomId());
    //         dto.setRoomName(isi.getRoom().getName());
    //         dto.setRating(classRating(isi.getClassId()));
    //         dto.setVideoUrl(isi.getVideoUrl());
    //         dto.setMeetUrl(isi.getMeetUrl());

    //         classDto.add(dto);
    //     });
    //     Page<ClassDtoGet> pageDto =
    //     return classDto;
    // }

    // @Override
    // public Page<ClassEntity> findAllPaginationSort(int offset, int pageSize, String field){
    //     log.info("Get all Class with Pagination and Sorting");
    //     Page<ClassEntity> classes = classRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    //     return classes;
    // }

    // @Override
    // public List<ClassDtoGet> findAllDto() {
    //     log.info("Get all Class with DTO");
    //     List<ClassEntity> classs = classRepository.findAll();
    //     List<ClassDtoGet> classDtos = new ArrayList<>();
        
    //     classs.forEach(isi ->{
    //         ClassDtoGet dto = new ClassDtoGet();
    //         dto.setClassId(isi.getClassId());
    //         dto.setName(isi.getName());
    //         dto.setDescription(isi.getDescription());
    //         dto.setCapacity(isi.getCapacity());
    //         dto.setSchedule(isi.getSchedule().toString());
    //         dto.setPrice(isi.getPrice());
    //         dto.setStatus(isi.getStatus());
    //         dto.setImageUrl(isi.getImageUrl());
    //         dto.setInstructor(isi.getInstructor().getName());
    //         dto.setCategory(isi.getCategory().getName());
    //         dto.setRoom(isi.getRoom().getName());
    //         dto.setType(isi.getType().getName());

    //         classDtos.add(dto);
    //     });
    //     return classDtos;
    // }

    @Override
    public ClassDtoGet getClassByIdDto(Long classId) {
        if(classRepository.findById(classId) == null){
            log.info("Class with id {} can't be found", classId);
            throw new IllegalStateException("Class not found");
        }else{
            log.info("Class with id {} found", classId);
            ClassEntity isi = classRepository.findById(classId).get();
            ClassDtoGet dto = new ClassDtoGet();
            dto.setClassId(isi.getClassId());
            dto.setName(isi.getName());
            dto.setDescription(isi.getDescription());
            dto.setStatus(isi.getStatus());
            dto.setCapacity(isi.getCapacity());
            dto.setBooked(isi.getBooked());
            dto.setSchedule(isi.getSchedule());
            dto.setHour(isi.getHour());
            dto.setPrice(isi.getPrice());
            dto.setImageUrl(isi.getType().getImageUrl());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setTypeId(isi.getType().getTypeId());
            dto.setTypeName(isi.getType().getName());
            dto.setInstructureId(isi.getInstructor().getInstructorId());
            dto.setInstructureName(isi.getInstructor().getName());
            dto.setContact(isi.getInstructor().getContact());
            dto.setCategoryId(isi.getCategory().getCategoryId());
            dto.setCategoryName(isi.getCategory().getName());
            dto.setRoomId(isi.getRoom().getRoomId());
            dto.setRoomName(isi.getRoom().getName());
            dto.setRating(classRating(isi.getClassId()));
            dto.setMeetUrl(isi.getMeetUrl());

            return dto;
        }
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
                    TypeEntity type2 = typeRepository.findById(classesDtoPost.getTypeId()).get();
                    CategoryEntity category2 = categoryRepository.findById(classesDtoPost.getCategoryId()).get();
                    RoomEntity room2 = roomRepository.findById(classesDtoPost.getRoomId()).get();
                    class2.setStatus(classesDtoPost.getStatus());
                    class2.setName(type2.getName() + " " + room2.getName() + " - " + category2.getName());
                    class2.setDescription(classesDtoPost.getDescription());
                    class2.setCapacity(classesDtoPost.getCapacity());
                    class2.setSchedule(classesDtoPost.getSchedule());
                    class2.setHour(classesDtoPost.getHour());
                    class2.setPrice(classesDtoPost.getPrice());
                    class2.setImageUrl(classesDtoPost.getImageUrl());
                    class2.setCategory(categoryEntity);
                    class2.setInstructor(instructorEntity);
                    class2.setRoom(roomEntity);
                    class2.setType(typeEntity);
                    class2.setTypeName(type2.getName());
                    class2.setMeetUrl(classesDtoPost.getMeetUrl());
                    
                    classRepository.save(class2);
                    log.info("Class updated");
            }else{
                log.info("Failed to Update Class");
                throw new IllegalStateException("Instructor, Category, Room or Type didn't exist");  
            }
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
                TypeEntity type2 = typeRepository.findById(classDtoPost.getTypeId()).get();
                CategoryEntity category2 = categoryRepository.findById(classDtoPost.getCategoryId()).get();
                RoomEntity room2 = roomRepository.findById(classDtoPost.getRoomId()).get();
                classEntity.setStatus(classDtoPost.getStatus());
                classEntity.setName(type2.getName() + " " + room2.getName() + " - " + category2.getName());
                classEntity.setDescription(classDtoPost.getDescription());
                classEntity.setCapacity(classDtoPost.getCapacity());
                classEntity.setBooked(0L);
                classEntity.setSchedule(classDtoPost.getSchedule());
                classEntity.setHour(classDtoPost.getHour());
                classEntity.setPrice(classDtoPost.getPrice());
                classEntity.setImageUrl(classDtoPost.getImageUrl());
                classEntity.setInstructor(instructorEntity);
                classEntity.setCategory(categoryEntity);
                classEntity.setRoom(roomEntity);
                classEntity.setType(typeEntity);
                classEntity.setTypeName(type2.getName());
                classEntity.setMeetUrl(classDtoPost.getMeetUrl());
                

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
        class2.setCapacity(class2.getCapacity() - 1);
        class2.setBooked(class2.getBooked() + 1);
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
        Long booked = class2.getBooked();
        if(class2.getBooked() > 0 ){
            class2.setCapacity(capacity + 1);
            class2.setBooked(booked - 1);
            classRepository.save(class2);
        }else{
            throw new IllegalStateException("Booked Can't Be below zero");
        }

    }

    @Override
    public Boolean classExist(Long classId) {
        if(classRepository.findById(classId).isPresent() == true){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<GetUserByClass> getBookingByClassId(Long classId) {
        List<BookingEntity> booking = bookingRepository.findByClassIdentity(classId);
        List<GetUserByClass> user = new ArrayList<>();
        booking.forEach(isi ->{
            GetUserByClass dto = new GetUserByClass();
            dto.setUserId(isi.getUser().getUserId());
            dto.setUsername(isi.getUser().getName());
            dto.setStatus(isi.getClasses().getStatus());
            dto.setJoinedAt(isi.getCreated_at().toString());

            user.add(dto);
        });
        return user;
    }

    @Override
    public List<ClassDtoGet> getClassByType(String typeName) {
        List<ClassEntity> classes = classRepository.findByTypeName(typeName);
        List<ClassDtoGet> classDtos = new ArrayList<>();
        classes.forEach(isi ->{
            ClassDtoGet dto = new ClassDtoGet();
            dto.setClassId(isi.getClassId());
            dto.setName(isi.getName());
            dto.setDescription(isi.getDescription());
            dto.setStatus(isi.getStatus());
            dto.setCapacity(isi.getCapacity());
            dto.setBooked(isi.getBooked());
            dto.setSchedule(isi.getSchedule());
            dto.setHour(isi.getHour());
            dto.setPrice(isi.getPrice());
            dto.setImageUrl(isi.getType().getImageUrl());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setTypeId(isi.getType().getTypeId());
            dto.setTypeName(isi.getType().getName());
            dto.setInstructureId(isi.getInstructor().getInstructorId());
            dto.setInstructureName(isi.getInstructor().getName());
            dto.setContact(isi.getInstructor().getContact());
            dto.setCategoryId(isi.getCategory().getCategoryId());
            dto.setCategoryName(isi.getCategory().getName());
            dto.setRoomId(isi.getRoom().getRoomId());
            dto.setRoomName(isi.getRoom().getName());
            dto.setRating(classRating(isi.getClassId()));
            dto.setMeetUrl(isi.getMeetUrl());

            classDtos.add(dto);
        });
        return classDtos;
    }

    @Override
    public List<ClassDtoGet> getClassByCategoryName(String categoryName) {
        List<ClassEntity> classes = classRepository.findByCategoryName(categoryName);
        List<ClassDtoGet> classDtos = new ArrayList<>();
        classes.forEach(isi ->{
            ClassDtoGet dto = new ClassDtoGet();
            dto.setClassId(isi.getClassId());
            dto.setName(isi.getName());
            dto.setDescription(isi.getDescription());
            dto.setStatus(isi.getStatus());
            dto.setCapacity(isi.getCapacity());
            dto.setBooked(isi.getBooked());
            dto.setSchedule(isi.getSchedule());
            dto.setHour(isi.getHour());
            dto.setPrice(isi.getPrice());
            dto.setImageUrl(isi.getType().getImageUrl());
            dto.setCreatedAt(isi.getCreated_at().toString());
            dto.setUpdatedAt(isi.getUpdated_at().toString());
            dto.setTypeId(isi.getType().getTypeId());
            dto.setTypeName(isi.getType().getName());
            dto.setInstructureId(isi.getInstructor().getInstructorId());
            dto.setInstructureName(isi.getInstructor().getName());
            dto.setContact(isi.getInstructor().getContact());
            dto.setCategoryId(isi.getCategory().getCategoryId());
            dto.setCategoryName(isi.getCategory().getName());
            dto.setRoomId(isi.getRoom().getRoomId());
            dto.setRoomName(isi.getRoom().getName());
            dto.setRating(classRating(isi.getClassId()));
            dto.setMeetUrl(isi.getMeetUrl());

            classDtos.add(dto);
        });
        return classDtos;
    }

    @Override
    public Long classRating(Long classId) {
        ClassEntity classes = classRepository.findById(classId).get();
        Long rating = 0L;
        for (int i = 0; i < classes.getRating().size(); i++) {
            rating = rating + classes.getRating().get(i).getRating();
            rating = rating / classes.getRating().size();
        }
        return rating;
    }
}
