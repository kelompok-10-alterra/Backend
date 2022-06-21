package com.capstone.kelompok10.controller;

import com.capstone.kelompok10.model.dto.get.InstructorDtoGet;
import com.capstone.kelompok10.model.dto.post.InstructorDtoPost;
import com.capstone.kelompok10.model.entity.InstructorEntity;
import com.capstone.kelompok10.service.interfaces.InstructorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/capstone/instructor")
public class InstructorController {
    InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService){
        this.instructorService = instructorService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<InstructorEntity>> findAll(){
        List<InstructorEntity> instructors = instructorService.findAll();
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }

    @GetMapping("/user/{offset}/{pageSize}")
    public ResponseEntity<Page<InstructorEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
        Page<InstructorEntity> instructors = instructorService.findAllPagination(offset, pageSize);
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }

    @GetMapping("/user/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<InstructorEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        Page<InstructorEntity> instructors = instructorService.findAllPaginationSort(offset, pageSize, field);
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }

    @GetMapping("/user/dto")
    public ResponseEntity<List<InstructorDtoGet>> findAllDto(){
        List<InstructorDtoGet> instructorDtos = instructorService.findAllDto();
        return new ResponseEntity<>(instructorDtos, HttpStatus.OK);
    }

    @GetMapping("/user/{instructorId}")
    public ResponseEntity<InstructorEntity> getInstructorById(@PathVariable("instructorId") Long instructorId){
        return new ResponseEntity<>(instructorService.getInstructorById(instructorId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InstructorEntity> createInstructor(@RequestBody InstructorEntity instructor){
        instructorService.createInstructor(instructor);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @PostMapping("/dto")
    public ResponseEntity<InstructorDtoPost> createInstructorDto(@RequestBody InstructorDtoPost instructorDtoPost){
        instructorService.createInstructorDto(instructorDtoPost);
        return new ResponseEntity<>(instructorDtoPost, HttpStatus.OK);
    }

    @PutMapping("/{instructorId}")
    public ResponseEntity<InstructorEntity> updateInstructor(@PathVariable("instructorId") Long instructorId, @RequestBody InstructorDtoPost instructorDtoPost){
        instructorService.updateInstructor(instructorId, instructorDtoPost);
        return new ResponseEntity<>(instructorService.getInstructorById(instructorId), HttpStatus.OK);
    }

    @DeleteMapping("/{instructorId}")
    public ResponseEntity<InstructorEntity> deleteInstructor(@PathVariable("instructorId") Long instructorId){
        instructorService.deleteInstructor(instructorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

