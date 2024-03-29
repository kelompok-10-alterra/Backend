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

    @GetMapping("/userAccess/getAllType")
    public ResponseEntity<List<TypeDtoGet>> findAll(){
        List<TypeDtoGet> types = typeService.findAll();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    // @GetMapping("/userAccess/{offset}/{pageSize}")
    // public ResponseEntity<Page<TypeEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
    //     Page<TypeEntity> types = typeService.findAllPagination(offset, pageSize);
    //     return new ResponseEntity<>(types, HttpStatus.OK);
    // }

    // @GetMapping("/userAccess/{offset}/{pageSize}/{field}")
    // public ResponseEntity<Page<TypeEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
    //     Page<TypeEntity> types = typeService.findAllPaginationSort(offset, pageSize, field);
    //     return new ResponseEntity<>(types, HttpStatus.OK);
    // }

    // @GetMapping("/userAccess/getAllTypeWithDto")
    // public ResponseEntity<List<TypeDtoGet>> findAllDto(){
    //     List<TypeDtoGet> typeDtos = typeService.findAllDto();
    //     return new ResponseEntity<>(typeDtos, HttpStatus.OK);
    // }

    @GetMapping("/userAccess/getTypeById")
    public ResponseEntity<TypeEntity> getTypeById(@RequestParam("typeId") Long typeId){
        return new ResponseEntity<>(typeService.getTypeById(typeId), HttpStatus.OK);
    }

    @PostMapping("/adminAccess/createNewType")
    public ResponseEntity<TypeDtoPost> createTypeDto(@RequestBody TypeDtoPost typeDtoPost){
        typeService.createTypeDto(typeDtoPost);
        return new ResponseEntity<>(typeDtoPost, HttpStatus.OK);
    }

    @PutMapping("/adminAccess/updateType/{typeId}")
    public ResponseEntity<TypeEntity> updateType(@PathVariable("typeId") Long typeId, @RequestBody TypeDtoPost typeDtoPost){
        typeService.updateType(typeId, typeDtoPost);
        return new ResponseEntity<>(typeService.getTypeById(typeId), HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteType/{typeId}")
    public ResponseEntity<TypeEntity> deleteType(@PathVariable("typeId") Long typeId){
        typeService.deleteType(typeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
