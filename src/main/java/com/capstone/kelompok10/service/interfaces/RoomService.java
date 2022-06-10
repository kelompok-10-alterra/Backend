package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.RoomDto;
import com.capstone.kelompok10.model.entity.RoomEntity;

public interface RoomService {
    List<RoomEntity> getAllRoom();
    List<RoomDto> getAllRoomDto();
    RoomEntity getRoomById(Long room_id);
    void createRoom(RoomEntity room);
    void updateRoom(Long room_id, RoomEntity room);
    void deleteRoom(Long room_id);
}
