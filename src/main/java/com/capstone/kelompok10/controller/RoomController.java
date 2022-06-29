package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/userAccess/getAllRoom")
    public ResponseEntity<List<RoomDtoGet>> findAll(){
        List<RoomDtoGet> rooms = roomService.findAll();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/userAccess/{offset}/{pageSize}")
    public ResponseEntity<Page<RoomEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
        Page<RoomEntity> rooms = roomService.findAllPagination(offset, pageSize);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/userAccess/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<RoomEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        Page<RoomEntity> rooms = roomService.findAllPaginationSort(offset, pageSize, field);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // @GetMapping("/userAccess/getAllRoomWithDto")
    // public ResponseEntity<List<RoomDtoGet>> findAllDto(){
    //     List<RoomDtoGet> roomDtos = roomService.findAllDto();
    //     return new ResponseEntity<>(roomDtos, HttpStatus.OK);
    // }

    @GetMapping("/userAccess/getRoomById")
    public ResponseEntity<RoomEntity> getRoomById(@RequestParam("roomId") Long roomId){
        return new ResponseEntity<>(roomService.getRoomById(roomId), HttpStatus.OK);
    }

    @PostMapping("/adminAccess/createNewRoom")
    public ResponseEntity<RoomDtoPost> createRoomDto(@RequestBody RoomDtoPost roomDtoPost){
        roomService.createRoomDto(roomDtoPost);
        return new ResponseEntity<>(roomDtoPost, HttpStatus.OK);
    }

    @PutMapping("/adminAccess/updateRoom/{roomId}")
    public ResponseEntity<RoomEntity> updateRoom(@PathVariable("roomId") Long roomId, @RequestBody RoomDtoPost roomDtoPost){
        roomService.updateRoom(roomId, roomDtoPost);
        return new ResponseEntity<>(roomService.getRoomById(roomId), HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteRoom/{roomId}")
    public ResponseEntity<RoomEntity> deleteRoom(@PathVariable("roomId") Long roomId){
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
