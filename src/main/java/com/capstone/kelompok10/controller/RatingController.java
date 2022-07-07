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

import com.capstone.kelompok10.model.dto.post.RatingDtoPost;
import com.capstone.kelompok10.model.entity.RatingEntity;
import com.capstone.kelompok10.service.interfaces.RatingService;

@RestController
@RequestMapping("/capstone/rating")
public class RatingController {
    RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }

    @GetMapping("/adminAccess/getAllRating")
    public ResponseEntity<List<RatingEntity>> findAll(){
        List<RatingEntity> rating = ratingService.findAll();
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getAllRatingByUserId")
    public ResponseEntity<List<RatingEntity>> findRatingByUserId(@RequestParam Long userId){
        List<RatingEntity> rating = ratingService.getRatingByUser(userId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping("/adminAccess/getAllRatingByClassId")
    public ResponseEntity<List<RatingEntity>> findRatingByClassId(@RequestParam Long classId){
        List<RatingEntity> rating = ratingService.getRatingByClass(classId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping("/userAcess/getRatingById")
    public ResponseEntity<RatingEntity> getRatingById(@RequestParam("ratingId") Long ratingId){
        return new ResponseEntity<>(ratingService.getRatingById(ratingId), HttpStatus.OK);
    }

    @PostMapping("/userAcess/createNewRating")
    public ResponseEntity<RatingDtoPost> createRatingDto(@RequestBody RatingDtoPost ratingDtoPost){
        ratingService.createRatingDto(ratingDtoPost);
        return new ResponseEntity<>(ratingDtoPost, HttpStatus.OK);
    }

    @PutMapping("/userAccess/updateRating")
    public ResponseEntity<RatingEntity> updateRating(@RequestParam("ratingId") Long ratingId, @RequestBody RatingDtoPost ratingDtoPost){
        ratingService.updateRating(ratingId, ratingDtoPost);
        return new ResponseEntity<>(ratingService.getRatingById(ratingId), HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteRating/{ratingId}")
    public ResponseEntity<RatingEntity> deleteRating(@PathVariable("RatingId") Long ratingId){
        ratingService.deleteRating(ratingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
