package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.post.FavoriteClassDtoPost;
import com.capstone.kelompok10.model.entity.FavoriteClassEntity;

public interface FavoriteClassService {
    List<FavoriteClassEntity> findAll();
    // List<FavoriteClassEntity> findAll(Long favoriteClassId);
    FavoriteClassEntity getFavoriteClassById(Long favoriteClassId);
    void createFavoriteClassDto(FavoriteClassDtoPost favoriteClassDtoPost);
    void deleteFavoriteClass(Long favoriteClassId);
}
