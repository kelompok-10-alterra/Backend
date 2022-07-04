package com.capstone.kelompok10.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.post.FavoriteClassDtoPost;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.entity.FavoriteClassEntity;
import com.capstone.kelompok10.model.entity.FavoriteEntity;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.repository.FavoriteClassRepository;
import com.capstone.kelompok10.repository.FavoriteRepository;
import com.capstone.kelompok10.service.interfaces.FavoriteClassService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class FavoriteClassServiceImpl implements FavoriteClassService {
    private FavoriteClassRepository favoriteClassRepository;
    private ClassRepository classRepository;
    private FavoriteRepository favoriteRepository;

    @Override
    public List<FavoriteClassEntity> findAll() {
        log.info("Get all FavoriteClass");
        List<FavoriteClassEntity> favoriteClasss = favoriteClassRepository.findAll();
        return favoriteClasss;
    }

    // @Override
    // public List<FavoriteClassEntity> findAll(Long favoriteClassId) {
    //     if(favoriteClassRepository.findById(favoriteClassId) != null){
    //         log.info("FavoriteClass with id {} found", favoriteClassId);
    //         return favoriteClassRepository.findById(favoriteClassId).get();
    //     }else{
    //         log.info("FavoriteClass with id {} not found", favoriteClassId);
    //         throw new IllegalStateException("FavoriteClass not Found");
    //     }
    // }

    @Override
    public FavoriteClassEntity getFavoriteClassById(Long favoriteClassId) {
        if(favoriteClassRepository.findById(favoriteClassId) != null){
            log.info("FavoriteClass with id {} found", favoriteClassId);
            return favoriteClassRepository.findById(favoriteClassId).get();
        }else{
            log.info("FavoriteClass with id {} not found", favoriteClassId);
            throw new IllegalStateException("FavoriteClass not Found");
        }
    }

    @Override
    public void createFavoriteClassDto(FavoriteClassDtoPost favoriteClassDtoPost) {
        FavoriteClassEntity favoriteClass = new FavoriteClassEntity();
        FavoriteEntity favorite = new FavoriteEntity();
        favorite.setFavoriteId(favoriteClassDtoPost.getFavoriteId());
        ClassEntity classes = new ClassEntity();
        classes.setClassId(favoriteClassDtoPost.getClassId());
        if(favoriteRepository.findById(favoriteClassDtoPost.getFavoriteId()).isPresent() && classRepository.findById(favoriteClassDtoPost.getClassId()).isPresent()){
            ClassEntity class2 = classRepository.findById(favoriteClassDtoPost.getClassId()).get();
            favoriteClass.setClassIdentity(class2.getClassId());
            favoriteClass.setClassName(class2.getName());
            favoriteClass.setClassPrice(class2.getPrice());
            favoriteClass.setSchedule(class2.getSchedule());
            favoriteClass.setClasses(classes);
            favoriteClass.setFavorite(favorite);

            favoriteClassRepository.save(favoriteClass);
        }else{
            log.info("Class with id {}, or Favorite with id {} not found", favoriteClassDtoPost.getFavoriteId(), favoriteClassDtoPost.getFavoriteId());
            throw new IllegalStateException("Failed to create favorite class");
        }


        
    }

    @Override
    public void deleteFavoriteClass(Long favoriteClassId) {
        if(favoriteClassRepository.findById(favoriteClassId).isPresent()){
            log.info("Successfuly deleted favorite class");
            favoriteClassRepository.deleteById(favoriteClassId);
        }else{
            log.info("Favorite Class with id {} not found", favoriteClassId);
            throw new IllegalStateException("Favorite Class you search not found");
        }
        
    }
}
