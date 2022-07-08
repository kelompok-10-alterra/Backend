package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.post.RatingDtoPost;
import com.capstone.kelompok10.model.entity.RatingEntity;

public interface RatingService {
    List<RatingEntity> findAll();
    RatingEntity getRatingById(Long ratingId);
    void createRatingDto(RatingDtoPost ratingDtoPost);
    void updateRating(Long ratingId, RatingDtoPost ratingDtoPost);
    void deleteRating(Long ratingId);
    Boolean ratingValue(Long rating);
    List<RatingEntity> getRatingByUser(Long userIdentity);
    List<RatingEntity> getRatingByClass(Long classIdentity);
}
