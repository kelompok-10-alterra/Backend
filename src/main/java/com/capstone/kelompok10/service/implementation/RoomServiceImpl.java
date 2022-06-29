package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.get.RoomDtoGet;
import com.capstone.kelompok10.model.dto.post.RoomDtoPost;
import com.capstone.kelompok10.model.entity.RoomEntity;
import com.capstone.kelompok10.repository.RoomRepository;
import com.capstone.kelompok10.service.interfaces.RoomService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;

    

    @Override
    public List<RoomEntity> findAll() {
        log.info("Get all Room without DTO");
        List<RoomEntity> room = new ArrayList<>();
        roomRepository.findAll().forEach(room::add);
        return room;
    }
    
    @Override
    public Page<RoomEntity> findAllPagination(int offset, int pageSize) {
        log.info("Get all Room with Pagination");
        Page<RoomEntity> room = roomRepository.findAll(PageRequest.of(offset, pageSize));
        return room;
    }

    @Override
    public Page<RoomEntity> findAllPaginationSort(int offset, int pageSize, String field){
        log.info("Get all Room with Pagination and Sorting");
        Page<RoomEntity> room = roomRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return room;
    }

    @Override
    public List<RoomDtoGet> findAllDto() {
        log.info("Get all Room with DTO");
        List<RoomEntity> rooms = roomRepository.findAll();
        List<RoomDtoGet> roomDtos = new ArrayList<>();
        
        rooms.forEach(isi ->{
            RoomDtoGet dto = new RoomDtoGet();
            dto.setRoomId(isi.getRoomId());
            dto.setName(isi.getName());

            roomDtos.add(dto);
        });
        return roomDtos;
    }

    @Override
    public RoomEntity getRoomById(Long roomId) {
        if(roomRepository.findById(roomId) != null){
            log.info("Room with id {} found", roomId);
            return roomRepository.findById(roomId).get();
        }else{
            log.info("Room with id {} not found", roomId);
            throw new IllegalStateException("Room not Found");
        }
    }

    @Override
    public void updateRoom(Long roomId, RoomDtoPost roomDtoPost) {
        if(roomRepository.findById(roomId) != null){
            RoomEntity room2 = roomRepository.findById(roomId).get();
            room2.setName(roomDtoPost.getName());

            roomRepository.save(room2);
            log.info("Room updated");
        }else{
            log.info("Room with id {} not found", roomId);
            throw new IllegalStateException("Room not Found");
        }
    }

    @Override
    public void deleteRoom(Long roomId) {
        if(roomRepository.findById(roomId) != null){
            roomRepository.deleteById(roomId);
            log.info("Room with id {} successfully deleted", roomId);
        }else{
            log.info("Room with id {} not found", roomId);
            throw new IllegalStateException("Room not Found");
        }
    }

	@Override
	public void createRoomDto(RoomDtoPost roomDtoPost) {
        if(roomRepository.findByName(roomDtoPost.getName()) == null){
            RoomEntity roomEntity = new RoomEntity();
            roomEntity.setName(roomDtoPost.getName());
            
            roomRepository.save(roomEntity);
            log.info("Room created");
        }else{
            log.info("Room with name {} already exist", roomDtoPost.getName());
            throw new IllegalStateException("Room already exist");
        }
	}

    @Override
    public Boolean roomExist(Long roomId) {
        if(roomRepository.findById(roomId) == null){
            return false;
        }else{
            return true;
        }
    }
}
