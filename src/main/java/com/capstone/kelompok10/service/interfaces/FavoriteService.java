package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.entity.FavoriteEntity;

public interface FavoriteService {
    List<FavoriteEntity> findAll();
    FavoriteEntity getFavoriteById(Long FavoriteId);
    void addFavoriteClassToFavorite(Long favoriteClassId, Long favoriteId);
}
