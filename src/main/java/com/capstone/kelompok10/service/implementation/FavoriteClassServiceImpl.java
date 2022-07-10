package com.capstone.kelompok10.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.post.FavoriteClassDtoPost;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.entity.FavoriteClassEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.repository.FavoriteClassRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.interfaces.FavoriteClassService;
import com.capstone.kelompok10.service.interfaces.FavoriteService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class FavoriteClassServiceImpl implements FavoriteClassService {
    private FavoriteClassRepository favoriteClassRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserRepository userRepository;

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
        ClassEntity classes = new ClassEntity();
        classes.setClassId(favoriteClassDtoPost.getClassId());
        UserEntity users = new UserEntity();
        users.setUserId(favoriteClassDtoPost.getUserId());
        if(classRepository.findById(favoriteClassDtoPost.getClassId()).isPresent() && userRepository.findById(favoriteClassDtoPost.getUserId()).isPresent()){
            ClassEntity class2 = classRepository.findById(favoriteClassDtoPost.getClassId()).get();
            UserEntity user2 = userRepository.findById(favoriteClassDtoPost.getUserId()).get();
            favoriteClass.setClassIdentity(class2.getClassId());
            favoriteClass.setClassName(class2.getName());
            favoriteClass.setClassPrice(class2.getPrice());
            favoriteClass.setSchedule(class2.getSchedule());
            favoriteClass.setClasses(classes);
            favoriteClass.setUser(users);

            favoriteClassRepository.save(favoriteClass);

            Long favoriteId = user2.getFavorite().getFavoriteId();
            favoriteService.addFavoriteClassToFavorite(favoriteClass.getFavoriteClassId(), favoriteId);
        }else{
            log.info("Class with id {}, or Favorite with id {} not found", favoriteClassDtoPost.getClassId(), favoriteClassDtoPost.getUserId());
            // throw new IllegalStateException("Failed to create favorite class");
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
