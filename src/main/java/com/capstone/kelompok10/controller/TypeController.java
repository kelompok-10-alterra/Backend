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

import com.capstone.kelompok10.model.dto.get.TypeDtoGet;
import com.capstone.kelompok10.model.dto.post.TypeDtoPost;
import com.capstone.kelompok10.model.entity.TypeEntity;
import com.capstone.kelompok10.service.interfaces.TypeService;

@RestController
@RequestMapping("/capstone/type")
public class TypeController {
    TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity<List<TypeEntity>> getAllType(){
        List<TypeEntity> types = typeService.getAllType();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<TypeDtoGet>> getAllTypeDto(){
        List<TypeDtoGet> typeDtos = typeService.getAllTypeDto();
        return new ResponseEntity<>(typeDtos, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<TypeEntity> getTypeById(@RequestParam("type_id") Long type_id){
        return new ResponseEntity<>(typeService.getTypeById(type_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TypeEntity> createType(@RequestBody TypeEntity type){
        typeService.createType(type);
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @PostMapping("/dto")
    public ResponseEntity<TypeDtoPost> createTypeDto(@RequestBody TypeDtoPost typeDtoPost){
        typeService.createTypeDto(typeDtoPost);
        return new ResponseEntity<>(typeDtoPost, HttpStatus.OK);
    }

    @PutMapping("/{type_id}")
    public ResponseEntity<TypeEntity> updateType(@PathVariable("Type_id") Long type_id, @RequestBody TypeDtoPost typeDtoPost){
        typeService.updateType(type_id, typeDtoPost);
        return new ResponseEntity<>(typeService.getTypeById(type_id), HttpStatus.OK);
    }

    @DeleteMapping("/{type_id}")
    public ResponseEntity<TypeEntity> deleteType(@PathVariable("type_id") Long type_id){
        typeService.deleteType(type_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
