package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.get.RoomDtoGet;
import com.capstone.kelompok10.model.dto.post.RoomDtoPost;
import com.capstone.kelompok10.model.entity.RoomEntity;
import com.capstone.kelompok10.repository.RoomRepository;
import com.capstone.kelompok10.service.interfaces.RoomService;

@Service
public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomEntity> getAllRoom() {
        List<RoomEntity> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
        return rooms;
    }

    @Override
    public List<RoomDtoGet> getAllRoomDto() {
        List<RoomEntity> rooms = roomRepository.findAll();
        List<RoomDtoGet> roomDtos = new ArrayList<>();
        
        rooms.forEach(isi ->{
            RoomDtoGet dto = new RoomDtoGet();
            dto.setRoom_id(isi.getRoom_id());
            dto.setName(isi.getName());

            roomDtos.add(dto);
        });
        return roomDtos;
    }

    @Override
    public RoomEntity getRoomById(Long room_id) {
        return roomRepository.findById(room_id).get();
    }

    @Override
    public void createRoom(RoomEntity room) {
        roomRepository.save(room);
    }

    @Override
    public void updateRoom(Long room_id, RoomEntity room) {
        RoomEntity room2 = roomRepository.findById(room_id).get();
        System.out.println(room2.toString());
        room2.setName(room.getName());

        roomRepository.save(room2);
    }

    @Override
    public void deleteRoom(Long room_id) {
        roomRepository.deleteById(room_id);
    }

	@Override
	public void createRoomDto(RoomDtoPost roomDtoPost) {
		RoomEntity roomEntity = new RoomEntity();
        roomEntity.setName(roomDtoPost.getName());
		
        roomRepository.save(roomEntity);
	}
}
