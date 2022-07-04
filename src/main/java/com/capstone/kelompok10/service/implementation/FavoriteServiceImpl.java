package com.capstone.kelompok10.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.entity.FavoriteClassEntity;
import com.capstone.kelompok10.model.entity.FavoriteEntity;
import com.capstone.kelompok10.repository.FavoriteClassRepository;
import com.capstone.kelompok10.repository.FavoriteRepository;
import com.capstone.kelompok10.service.interfaces.FavoriteService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteClassRepository favoriteClassRepository;
    
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

    @Override
	public void addFavoriteClassToFavorite(Long favoriteClassId, Long favoriteId) {
		FavoriteEntity favorite = favoriteRepository.findById(favoriteId).get();
        if(favoriteClassRepository.findById(favoriteClassId).isPresent() == true){
            FavoriteClassEntity favClass = favoriteClassRepository.findById(favoriteClassId).get();
            favorite.getFavClass().add(favClass);
            favoriteRepository.save(favorite);
        }else{
            log.info("Booking with id {} no exist !!", favoriteClassId);
        }
		
	}
    
}
