// package com.capstone.kelompok10.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.jeasy.random.EasyRandom;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import com.capstone.kelompok10.model.dto.post.InstructorDtoPost;
// import com.capstone.kelompok10.model.entity.InstructorEntity;
// import com.capstone.kelompok10.repository.InstructorRepository;
// import com.capstone.kelompok10.service.implementation.InstructorServiceImpl;

// @ExtendWith(MockitoExtension.class)
// @ExtendWith(SpringExtension.class)
// public class InstructorServiceTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();

//     @InjectMocks
//     private InstructorServiceImpl service;

//     @Mock
//     private InstructorRepository repository;
    
//     @Test
//     void findAll(){
//         List<InstructorEntity> instructor = EASY_RANDOM.objects(InstructorEntity.class, 2)
//         .collect(Collectors.toList());

//         when(repository.findAll()).thenReturn(instructor);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getInstructorById(){
//         InstructorEntity instructor = EASY_RANDOM.nextObject(InstructorEntity.class);

//         when(repository.findById(instructor.getInstructorId())).thenReturn(Optional.of(instructor));
//         service.getInstructorById(instructor.getInstructorId());
//         verify(repository, times(2)).findById(instructor.getInstructorId());
//     }

//     @Test
//     public void updateInstructor() {
//         InstructorEntity instructor = EASY_RANDOM.nextObject(InstructorEntity.class);
//         InstructorDtoPost newInstructor = new InstructorDtoPost();
//         newInstructor.setName("Baru");

//         when(repository.findById(instructor.getInstructorId())).thenReturn(Optional.of(instructor));
//         service.updateInstructor(instructor.getInstructorId(), newInstructor);
//         verify(repository).save(instructor);
//         verify(repository, times(2)).findById(instructor.getInstructorId());
//     }

//     @Test
//     public void createInstructorDto(){
//         InstructorEntity instructorEntity = new InstructorEntity();
//         InstructorDtoPost instructorDtoPost = EASY_RANDOM.nextObject(InstructorDtoPost.class);
//         instructorEntity.setName(instructorDtoPost.getName());
//         instructorEntity.setContact(instructorDtoPost.getContact());
//         instructorEntity.setAddress(instructorDtoPost.getAddress());
//         instructorEntity.setImageUrl(instructorDtoPost.getImageUrl());

//         service.createInstructorDto(instructorDtoPost);
//         verify(repository).save(instructorEntity);
//     }

//     @Test
//     public void deleteInstructor(){
//         InstructorEntity instructorEntity = EASY_RANDOM.nextObject(InstructorEntity.class);

//         when(repository.findById(instructorEntity.getInstructorId())).thenReturn(Optional.of(instructorEntity));
//         service.deleteInstructor(instructorEntity.getInstructorId());
//         verify(repository, times(1)).deleteById(instructorEntity.getInstructorId());
//         verify(repository, times(1)).findById(instructorEntity.getInstructorId());
//     }

//     @Test
//     public void instructorExist(){
//         InstructorEntity instructure = EASY_RANDOM.nextObject(InstructorEntity.class);

//         when(repository.findById(instructure.getInstructorId())).thenReturn(Optional.of(instructure));
//         service.instructorExist(instructure.getInstructorId());
//         assertEquals(true, service.instructorExist(instructure.getInstructorId()));
//     }
// }
