package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.kelompok10.model.dto.get.RoomDtoGet;
import com.capstone.kelompok10.model.dto.post.RoomDtoPost;
import com.capstone.kelompok10.model.entity.RoomEntity;
import com.capstone.kelompok10.service.interfaces.RoomService;

@RestController
@RequestMapping("/capstone/room")
public class RoomController {
    RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomEntity>> getAllRoom(){
        List<RoomEntity> rooms = roomService.getAllRoom();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<RoomDtoGet>> getAllRoomDto(){
        List<RoomDtoGet> roomDtos = roomService.getAllRoomDto();
        return new ResponseEntity<>(roomDtos, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<RoomEntity> getRoomById(@RequestParam("room_id") Long room_id){
        return new ResponseEntity<>(roomService.getRoomById(room_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomEntity> createRoom(@RequestBody RoomEntity room){
        roomService.createRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PostMapping("/dto")
    public ResponseEntity<RoomDtoPost> createRoomDto(@RequestBody RoomDtoPost roomDtoPost){
        roomService.createRoomDto(roomDtoPost);
        return new ResponseEntity<>(roomDtoPost, HttpStatus.OK);
    }

    @PutMapping("/{room_id}")
    public ResponseEntity<RoomEntity> updateRoom(@PathVariable("room_id") Long room_id, @RequestBody RoomEntity room){
        roomService.updateRoom(room_id, room);
        return new ResponseEntity<>(roomService.getRoomById(room_id), HttpStatus.OK);
    }

    @DeleteMapping("/{room_id}")
    public ResponseEntity<RoomEntity> deleteRoom(@PathVariable("room_id") Long room_id){
        roomService.deleteRoom(room_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
