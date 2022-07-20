// package com.capstone.kelompok10.service;

// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.jeasy.random.EasyRandom;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import com.capstone.kelompok10.model.dto.get.ClassDtoGet;
// import com.capstone.kelompok10.model.entity.ClassEntity;
// import com.capstone.kelompok10.repository.CategoryRepository;
// import com.capstone.kelompok10.repository.ClassRepository;
// import com.capstone.kelompok10.repository.InstructorRepository;
// import com.capstone.kelompok10.repository.RoomRepository;
// import com.capstone.kelompok10.repository.TypeRepository;
// import com.capstone.kelompok10.service.implementation.CategoryServiceImpl;
// import com.capstone.kelompok10.service.implementation.ClassServiceImpl;
// import com.capstone.kelompok10.service.implementation.InstructorServiceImpl;
// import com.capstone.kelompok10.service.implementation.RoomServiceImpl;
// import com.capstone.kelompok10.service.implementation.TypeServiceImpl;
// import com.capstone.kelompok10.service.interfaces.InstructorService;
// import com.capstone.kelompok10.service.interfaces.RoomService;
// import com.capstone.kelompok10.service.interfaces.TypeService;

// @ExtendWith(MockitoExtension.class)
// @ExtendWith(SpringExtension.class)
// public class ClassServiceTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();

//     @InjectMocks
//     private ClassServiceImpl service;

//     @Mock
//     private ClassRepository repository;

//     @Mock
//     private InstructorRepository instructorRepository;

//     @Mock
//     private CategoryRepository categoryRepository;

//     @Mock
//     private TypeRepository typeRepository;

//     @Mock
//     private RoomRepository roomRepository;

//     @Mock
//     InstructorServiceImpl instructorServiceImpl;

//     @Mock
//     RoomServiceImpl roomServiceImpl;

//     @Mock
//     TypeServiceImpl typeServiceImpl;

//     @Mock
//     CategoryServiceImpl categoryServiceImpl;

//     @Mock
//     private InstructorService instructorService;

//     @Mock
//     private RoomService roomService;

//     @Mock
//     private TypeService typeService;

//     @Mock
//     private CategoryServiceTest categoryService;

//     @Test
//     void findAllClass(){
//         List<ClassEntity> classs = EASY_RANDOM.objects(ClassEntity.class, 2).collect(Collectors.toList());
//         List<ClassDtoGet> class2 = new ArrayList<>();
//         classs.forEach(isi ->{
//             ClassDtoGet dto = new ClassDtoGet();
//             dto.setClassId(isi.getClassId());
//             dto.setName(isi.getName());
//             dto.setDescription(isi.getDescription());
//             dto.setStatus(isi.getStatus());
//             dto.setCapacity(isi.getCapacity());
//             dto.setBooked(isi.getBooked());
//             dto.setSchedule(isi.getSchedule());
//             dto.setHour(isi.getHour());
//             dto.setPrice(isi.getPrice());
//             dto.setImageUrl(isi.getImageUrl());
//             dto.setCreatedAt(isi.getCreated_at().toString());
//             dto.setUpdatedAt(isi.getUpdated_at().toString());
//             dto.setTypeId(isi.getType().getTypeId());
//             dto.setTypeName(isi.getType().getName());
//             dto.setInstructureId(isi.getInstructor().getInstructorId());
//             dto.setInstructureName(isi.getInstructor().getName());
//             dto.setContact(isi.getInstructor().getContact());
//             dto.setCategoryId(isi.getCategory().getCategoryId());
//             dto.setCategoryName(isi.getCategory().getName());
//             dto.setRoomId(isi.getRoom().getRoomId());
//             dto.setRoomName(isi.getRoom().getName());
//             dto.setMeetUrl(isi.getMeetUrl());

//             class2.add(dto);
//         });

//         // when(repository.findAll()).thenReturn(classs);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getClassById(){
//         ClassEntity isi = EASY_RANDOM.nextObject(ClassEntity.class);
//         ClassDtoGet dto = new ClassDtoGet();
//         dto.setClassId(isi.getClassId());
//             dto.setName(isi.getName());
//             dto.setDescription(isi.getDescription());
//             dto.setStatus(isi.getStatus());
//             dto.setCapacity(isi.getCapacity());
//             dto.setBooked(isi.getBooked());
//             dto.setSchedule(isi.getSchedule());
//             dto.setHour(isi.getHour());
//             dto.setPrice(isi.getPrice());
//             dto.setImageUrl(isi.getImageUrl());
//             dto.setCreatedAt(isi.getCreated_at().toString());
//             dto.setUpdatedAt(isi.getUpdated_at().toString());
//             dto.setTypeId(isi.getType().getTypeId());
//             dto.setTypeName(isi.getType().getName());
//             dto.setInstructureId(isi.getInstructor().getInstructorId());
//             dto.setInstructureName(isi.getInstructor().getName());
//             dto.setContact(isi.getInstructor().getContact());
//             dto.setCategoryId(isi.getCategory().getCategoryId());
//             dto.setCategoryName(isi.getCategory().getName());
//             dto.setRoomId(isi.getRoom().getRoomId());
//             dto.setRoomName(isi.getRoom().getName());
//             dto.setMeetUrl(isi.getMeetUrl());
//             // dto.setRating(classRating(isi.getClassId()));

//         when(repository.findById(isi.getClassId())).thenReturn(Optional.of(isi));
//         service.getClassById(isi.getClassId());
//         verify(repository, times(2)).findById(isi.getClassId());
//     }

//     @Test
//     void getClassByIdDto(){
//         ClassEntity isi = EASY_RANDOM.nextObject(ClassEntity.class);
//         ClassDtoGet dto = new ClassDtoGet();
//         dto.setClassId(isi.getClassId());
//             dto.setName(isi.getName());
//             dto.setDescription(isi.getDescription());
//             dto.setStatus(isi.getStatus());
//             dto.setCapacity(isi.getCapacity());
//             dto.setBooked(isi.getBooked());
//             dto.setSchedule(isi.getSchedule());
//             dto.setHour(isi.getHour());
//             dto.setPrice(isi.getPrice());
//             dto.setImageUrl(isi.getImageUrl());
//             dto.setCreatedAt(isi.getCreated_at().toString());
//             dto.setUpdatedAt(isi.getUpdated_at().toString());
//             dto.setTypeId(isi.getType().getTypeId());
//             dto.setTypeName(isi.getType().getName());
//             dto.setInstructureId(isi.getInstructor().getInstructorId());
//             dto.setInstructureName(isi.getInstructor().getName());
//             dto.setContact(isi.getInstructor().getContact());
//             dto.setCategoryId(isi.getCategory().getCategoryId());
//             dto.setCategoryName(isi.getCategory().getName());
//             dto.setRoomId(isi.getRoom().getRoomId());
//             dto.setRoomName(isi.getRoom().getName());
//             dto.setMeetUrl(isi.getMeetUrl());
//             // dto.setRating(classRating(isi.getClassId()));

//         when(repository.findById(isi.getClassId())).thenReturn(Optional.of(isi));
//         service.getClassByIdDto(isi.getClassId());
//         verify(repository, times(3)).findById(isi.getClassId());
//     }

//     @Test
//     public void deleteClass(){
//         ClassEntity class2 = EASY_RANDOM.nextObject(ClassEntity.class);

//         when(repository.findById(class2.getClassId())).thenReturn(Optional.of(class2));
//         service.deleteClass(class2.getClassId());
//         verify(repository).deleteById(class2.getClassId());
//     }

//     // @Test
//     // public void createClassDto(){
//     //     ClassDtoPost dto = EASY_RANDOM.nextObject(ClassDtoPost.class);
//     //     InstructorEntity instructorEntity = new InstructorEntity();
//     //     instructorEntity.setInstructorId(dto.getInstructorId());
//     //     RoomEntity roomEntity = new RoomEntity();
//     //     roomEntity.setRoomId(dto.getRoomId());
//     //     TypeEntity typeEntity = new TypeEntity();
//     //     typeEntity.setTypeId(dto.getTypeId());
//     //     CategoryEntity categoryEntity = new CategoryEntity();
//     //     categoryEntity.setCategoryId(dto.getCategoryId());
//     //     ClassEntity class2 = new ClassEntity();

//     //     class2.setStatus(dto.getStatus());
//     //     class2.setName(dto.getName());
//     //     class2.setDescription(dto.getDescription());
//     //     class2.setCapacity(dto.getCapacity());
//     //     class2.setSchedule(dto.getSchedule());
//     //     class2.setHour(dto.getHour());
//     //     class2.setPrice(dto.getPrice());
//     //     class2.setImageUrl(dto.getImageUrl());
//     //     class2.setInstructor(instructorEntity);
//     //     class2.setRoom(roomEntity);
//     //     class2.setCategory(categoryEntity);
//     //     class2.setType(typeEntity);

//     //     service.createClassDto(dto);
//     //     verify(repository, times(1)).save(class2);
//     // }

//     @Test
//     public void classBooked(){
//         ClassEntity class2 = EASY_RANDOM.nextObject(ClassEntity.class);
//         Long capacity = class2.getCapacity() - 1;
//         Long booked = class2.getBooked() + 1;

//         when(repository.findById(class2.getClassId())).thenReturn(Optional.of(class2));
//         service.classBooked(class2.getClassId());
//         verify(repository, times(1)).save(class2);
//         verify(repository, times(1)).findById(class2.getClassId());
//         assertEquals(capacity, class2.getCapacity());
//         assertEquals(booked, class2.getBooked());
//     }
    
//     @Test
//     public void classFull(){
//         ClassEntity class2 = EASY_RANDOM.nextObject(ClassEntity.class);
//         Long capacity = class2.getCapacity();
//         class2.setCapacity(class2.getCapacity() - capacity);
//         repository.save(class2);

//         when(repository.findById(class2.getClassId())).thenReturn(Optional.of(class2));
//         service.classFull(class2.getClassId());
//         assertEquals(true, service.classFull(class2.getClassId()));
//     }

//     @Test
//     public void classNotFull(){
//         ClassEntity class2 = EASY_RANDOM.nextObject(ClassEntity.class);
//         class2.setCapacity(100L - 1L);
//         repository.save(class2);

//         when(repository.findById(class2.getClassId())).thenReturn(Optional.of(class2));
//         service.classFull(class2.getClassId());
//         assertEquals(false, service.classFull(class2.getClassId()));
//     }

//     @Test
//     public void classPrice(){
//         ClassEntity class2 = EASY_RANDOM.nextObject(ClassEntity.class);
//         Long price = class2.getPrice();
        
//         when(repository.findById(class2.getClassId())).thenReturn(Optional.of(class2));
//         service.classPrice(class2.getClassId());
//         verify(repository, times(1)).findById(class2.getClassId());
//         assertEquals(price, class2.getPrice());
//     }

//     @Test
//     public void unBookClass(){
//         ClassEntity class2 = EASY_RANDOM.nextObject(ClassEntity.class);
//         Long capacity = class2.getCapacity() + 1;
//         Long booked = class2.getBooked() - 1;

//         when(repository.findById(class2.getClassId())).thenReturn(Optional.of(class2));
//         service.unBookClass(class2.getClassId());
//         verify(repository, times(1)).save(class2);
//         verify(repository, times(1)).findById(class2.getClassId());
//         assertEquals(capacity, class2.getCapacity());
//         assertEquals(booked, class2.getBooked());
//     }

//     @Test
//     public void classExist(){
//         ClassEntity class2 = EASY_RANDOM.nextObject(ClassEntity.class);

//         when(repository.findById(class2.getClassId())).thenReturn(Optional.of(class2));
//         service.classFull(class2.getClassId());
//         assertEquals(true, service.classFull(class2.getClassId()));
//     }

// }
