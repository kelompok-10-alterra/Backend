package com.capstone.kelompok10.controller;

import com.capstone.kelompok10.model.dto.get.InstructorDtoGet;
import com.capstone.kelompok10.model.dto.post.InstructorDtoPost;
import com.capstone.kelompok10.model.entity.InstructorEntity;
import com.capstone.kelompok10.service.interfaces.InstructorService;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capstone/instructor")
public class InstructorController {
    InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService){
        this.instructorService = instructorService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<InstructorEntity>> getAllInstructor(){
        List<InstructorEntity> instructors = instructorService.getAllInstructor();
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }

    @GetMapping("/user/dto")
    public ResponseEntity<List<InstructorDtoGet>> getAllInstructorDto(){
        List<InstructorDtoGet> instructorDtos = instructorService.getAllInstructorDto();
        return new ResponseEntity<>(instructorDtos, HttpStatus.OK);
    }

    @GetMapping("/user/{instructor_id}")
    public ResponseEntity<InstructorEntity> getInstructorById(@PathVariable("instructor_id") Long instructor_id){
        return new ResponseEntity<>(instructorService.getInstructorById(instructor_id), HttpStatus.OK);
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

    @PutMapping("/{instructor_id}")
    public ResponseEntity<InstructorEntity> updateInstructor(@PathVariable("instructor_id") Long instructor_id, @RequestBody InstructorEntity instructor){
        instructorService.updateInstructor(instructor_id, instructor);
        return new ResponseEntity<>(instructorService.getInstructorById(instructor_id), HttpStatus.OK);
    }

    @DeleteMapping("/{instructor_id}")
    public ResponseEntity<InstructorEntity> deleteInstructor(@PathVariable("instructor_id") Long instructor_id){
        instructorService.deleteInstructor(instructor_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

