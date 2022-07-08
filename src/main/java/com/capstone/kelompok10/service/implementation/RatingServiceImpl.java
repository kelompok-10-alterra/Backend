package com.capstone.kelompok10.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.post.RatingDtoPost;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.entity.RatingEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.repository.RatingRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.interfaces.RatingService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private RatingRepository ratingRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<RatingEntity> findAll() {
        log.info("Get all Rating");
        List<RatingEntity> ratings = ratingRepository.findAll();
        return ratings;
    }

    @Override
    public RatingEntity getRatingById(Long ratingId) {
        if(ratingRepository.findById(ratingId) != null){
            log.info("Rating with id {} found", ratingId);
            return ratingRepository.findById(ratingId).get();
        }else{
            log.info("Rating with id {} not found", ratingId);
            throw new IllegalStateException("Rating not Found");
        }
    }

    @Override
    public void createRatingDto(RatingDtoPost ratingDtoPost) {
        RatingEntity rating = new RatingEntity();
        ClassEntity classes = new ClassEntity();
        classes.setClassId(ratingDtoPost.getClassId());
        UserEntity users = new UserEntity();
        users.setUserId(ratingDtoPost.getUserId());
        if(classRepository.findById(ratingDtoPost.getClassId()).isPresent() && userRepository.findById(ratingDtoPost.getUserId()).isPresent()){
            UserEntity user2 = userRepository.findById(ratingDtoPost.getUserId()).get();
            ClassEntity class2 = classRepository.findById(ratingDtoPost.getClassId()).get();
            rating.setRating(ratingDtoPost.getRating());
            if(ratingValue(ratingDtoPost.getRating()) == true){
                rating.setClasses(classes);
                rating.setUser(users);
                rating.setUsername(user2.getUsername());
                rating.setUserIdentity(user2.getUserId());
                rating.setClassIdentity(class2.getClassId());

                ratingRepository.save(rating);
            }else{
                log.info("Rating must be 1 - 5 !!");
                throw new IllegalStateException("Rating must be 1 - 5 !!");
            }
        }else{
            log.info("Class with id {}, or Favorite with id {} not found", ratingDtoPost.getClassId(), ratingDtoPost.getUserId());
            throw new IllegalStateException("Failed to create favorite class");
        }


        
    }

    @Override
    public void deleteRating(Long ratingId) {
        if(ratingRepository.findById(ratingId).isPresent()){
            log.info("Successfuly deleted favorite class");
            ratingRepository.deleteById(ratingId);
        }else{
            log.info("Favorite Class with id {} not found", ratingId);
            throw new IllegalStateException("Favorite Class you search not found");
        }
        
    }

    @Override
    public void updateRating(Long ratingId, RatingDtoPost ratingDtoPost) {
        if (ratingRepository.findById(ratingId).isPresent()){
            RatingEntity rating = ratingRepository.findById(ratingId).get();
            ClassEntity classes = new ClassEntity();
            classes.setClassId(ratingDtoPost.getClassId());
            UserEntity user = new UserEntity();
            user.setUserId(ratingDtoPost.getUserId());

            ClassEntity class2 = classRepository.findById(ratingDtoPost.getClassId()).get();
            UserEntity user2 = userRepository.findById(ratingDtoPost.getUserId()).get();
            rating.setRating(ratingDtoPost.getRating());
            rating.setClasses(classes);
            rating.setUser(user);
            rating.setUsername(user2.getUsername());
            rating.setUserIdentity(user2.getUserId());
            rating.setClassIdentity(class2.getClassId());

            ratingRepository.save(rating);
            log.info("Rating Updated");
        }else{
            log.info("Rating with id {} not found", ratingId);
            throw new IllegalStateException("Rating not found");
        }
        
    }

    @Override
    public Boolean ratingValue(Long rating) {
        if(rating == 1 || rating == 2 || rating == 3 || rating == 4 || rating == 5){
            return true;
        }else{
            return false;
        }
    }

	@Override
	public List<RatingEntity> getRatingByUser(Long userIdentity) {
        log.info("Get All rating with userId {}", userIdentity);
		List<RatingEntity> rating = ratingRepository.getRatingByUserIdentity(userIdentity);
		return rating;
	}

	@Override
	public List<RatingEntity> getRatingByClass(Long classIdentity) {
		log.info("Get All rating with classId {}", classIdentity);
		List<RatingEntity> rating = ratingRepository.getRatingByUserIdentity(classIdentity);
		return rating;
	}
}
