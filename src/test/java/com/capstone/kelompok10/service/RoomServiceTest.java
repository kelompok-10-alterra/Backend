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

// import com.capstone.kelompok10.model.dto.post.RoomDtoPost;
// import com.capstone.kelompok10.model.entity.RoomEntity;
// import com.capstone.kelompok10.repository.RoomRepository;
// import com.capstone.kelompok10.service.implementation.RoomServiceImpl;

// @ExtendWith(MockitoExtension.class)
// @ExtendWith(SpringExtension.class)
// public class RoomServiceTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();

//     @InjectMocks
//     private RoomServiceImpl service;

//     @Mock
//     private RoomRepository repository;

//     @Test
//     void findAll(){
//         List<RoomEntity> room = EASY_RANDOM.objects(RoomEntity.class, 2)
//         .collect(Collectors.toList());

//         when(repository.findAll()).thenReturn(room);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getRoomById(){
//         RoomEntity room = EASY_RANDOM.nextObject(RoomEntity.class);

//         when(repository.findById(room.getRoomId())).thenReturn(Optional.of(room));
//         service.getRoomById(room.getRoomId());
//         verify(repository, times(2)).findById(room.getRoomId());
//     }

//     @Test
//     public void updateRoom() {
//         RoomEntity room = EASY_RANDOM.nextObject(RoomEntity.class);
//         RoomDtoPost newRoom = new RoomDtoPost();
//         newRoom.setName("Baru");

//         when(repository.findById(room.getRoomId())).thenReturn(Optional.of(room));
//         service.updateRoom(room.getRoomId(), newRoom);
//         verify(repository).save(room);
//         verify(repository, times(2)).findById(room.getRoomId());
//     }
    
//     @Test
//     public void createRoomDto(){
//         RoomEntity roomEntity = new RoomEntity();
//         RoomDtoPost roomDtoPost = EASY_RANDOM.nextObject(RoomDtoPost.class);
//         roomEntity.setName(roomDtoPost.getName());

//         service.createRoomDto(roomDtoPost);
//         verify(repository).save(roomEntity);
//     }

//     @Test
//     public void RoomExist(){
//         RoomEntity room = EASY_RANDOM.nextObject(RoomEntity.class);

//         when(repository.findById(room.getRoomId())).thenReturn(Optional.of(room));
//         service.roomExist(room.getRoomId());
//         assertEquals(true, service.roomExist(room.getRoomId()));
//     }

//     @Test
//     public void deleteRoom(){
//         RoomEntity roomEntity = EASY_RANDOM.nextObject(RoomEntity.class);

//         when(repository.findById(roomEntity.getRoomId())).thenReturn(Optional.of(roomEntity));
//         service.deleteRoom(roomEntity.getRoomId());
//         verify(repository, times(1)).deleteById(roomEntity.getRoomId());
//         verify(repository, times(1)).findById(roomEntity.getRoomId());
//     }
// }
