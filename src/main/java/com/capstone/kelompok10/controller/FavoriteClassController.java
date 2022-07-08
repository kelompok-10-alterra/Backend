package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.kelompok10.model.dto.post.FavoriteClassDtoPost;
import com.capstone.kelompok10.model.entity.FavoriteClassEntity;
import com.capstone.kelompok10.service.interfaces.FavoriteClassService;

@RestController
@RequestMapping("/capstone/favoriteClass")
public class FavoriteClassController {
    FavoriteClassService favoriteClassService;

    @Autowired
    public FavoriteClassController(FavoriteClassService favoriteClassService){
        this.favoriteClassService = favoriteClassService;
    }

    @GetMapping("/adminAccess/getAllFavoriteClass")
    public ResponseEntity<List<FavoriteClassEntity>> findAll(){
        List<FavoriteClassEntity> favoriteClass = favoriteClassService.findAll();
        return new ResponseEntity<>(favoriteClass, HttpStatus.OK);
    }

    @GetMapping("/userAcess/getFavoriteClassById")
    public ResponseEntity<FavoriteClassEntity> getFavoriteClassById(@RequestParam("favoriteClassId") Long favoriteClassId){
        return new ResponseEntity<>(favoriteClassService.getFavoriteClassById(favoriteClassId), HttpStatus.OK);
    }

    @PostMapping("/userAcess/createNewFavoriteClass")
    public ResponseEntity<FavoriteClassDtoPost> createFavoriteClassDto(@RequestBody FavoriteClassDtoPost favoriteClassDtoPost){
        favoriteClassService.createFavoriteClassDto(favoriteClassDtoPost);
        return new ResponseEntity<>(favoriteClassDtoPost, HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteFavoriteClass/{favoriteClassId}")
    public ResponseEntity<FavoriteClassEntity> deleteFavoriteClass(@PathVariable("favoriteClassId") Long favoriteClassId){
        favoriteClassService.deleteFavoriteClass(favoriteClassId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
