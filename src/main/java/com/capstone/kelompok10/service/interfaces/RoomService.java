package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.RoomDtoGet;
import com.capstone.kelompok10.model.dto.post.RoomDtoPost;
import com.capstone.kelompok10.model.entity.RoomEntity;

public interface RoomService {
    List<RoomEntity> getAllRoom();
    List<RoomDtoGet> getAllRoomDto();
    RoomEntity getRoomById(Long room_id);
    void createRoom(RoomEntity room);
    void createRoomDto(RoomDtoPost roomDtoPost);
    void updateRoom(Long room_id, RoomEntity room);
    void deleteRoom(Long room_id);
}
