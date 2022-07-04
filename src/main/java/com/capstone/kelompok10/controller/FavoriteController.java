package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.kelompok10.model.entity.FavoriteEntity;
import com.capstone.kelompok10.service.interfaces.FavoriteService;

@RestController
@RequestMapping("/capstone/favorite")
public class FavoriteController {
    FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

    @GetMapping("/adminAccess/getAllFavorite")
    public ResponseEntity<List<FavoriteEntity>> findAll(){
        List<FavoriteEntity> favorites = favoriteService.findAll();
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getFavoriteById")
    public ResponseEntity<FavoriteEntity> getRoleById(@RequestParam("favoriteId") Long favoriteId){
        favoriteService.getFavoriteById(favoriteId);
        return new ResponseEntity<>(favoriteService.getFavoriteById(favoriteId), HttpStatus.OK);
    }
}
