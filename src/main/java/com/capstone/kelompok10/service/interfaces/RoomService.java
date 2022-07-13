package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.RoomDtoGet;
import com.capstone.kelompok10.model.dto.post.RoomDtoPost;
import com.capstone.kelompok10.model.entity.RoomEntity;

public interface RoomService {
    List<RoomDtoGet> findAll();
    // List<RoomDtoGet> findAllDto();
    // Page<RoomEntity> findAllPagination(int offset, int pageSize);
    // Page<RoomEntity> findAllPaginationSort(int offset, int pageSize, String field);
    RoomEntity getRoomById(Long roomId);
    void createRoomDto(RoomDtoPost roomDtoPost);
    void updateRoom(Long roomId, RoomDtoPost roomDtoPost);
    void deleteRoom(Long roomId);

    //Verify Method
    Boolean roomExist(Long roomId);
}
