package com.capstone.kelompok10.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.entity.FavoriteEntity;
import com.capstone.kelompok10.repository.FavoriteRepository;
import com.capstone.kelompok10.service.interfaces.FavoriteService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteRepository favoriteRepository;
    
    @Override
    public List<FavoriteEntity> findAll() {
        log.info("Get all Favorite");
        List<FavoriteEntity> favorite = favoriteRepository.findAll();
        return favorite;
    }

    @Override
    public FavoriteEntity getFavoriteById(Long favoriteId) {
        if(favoriteRepository.findById(favoriteId) != null){
            log.info("Favorite with id {} found", favoriteId);
            return favoriteRepository.findById(favoriteId).get();
        }else{
            log.info("Favorite with id {} not found", favoriteId);
            throw new IllegalStateException("Favorite not Found");
        }
    }
    
}
