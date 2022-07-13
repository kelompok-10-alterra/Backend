package com.capstone.kelompok10.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capstone.kelompok10.model.entity.RatingEntity;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.repository.RatingRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.implementation.RatingServiceImpl;
import com.capstone.kelompok10.service.interfaces.ClassService;
import com.capstone.kelompok10.service.interfaces.UserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RatingServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private RatingServiceImpl service;

    @Mock
    private RatingRepository repository;

    @Mock
    private ClassService classService;

    @Mock
    private UserService userService;

    @Mock
    private ClassRepository classRepository;

    @Mock
    private UserRepository userRepository;
    
    @Test
    void findAll(){
        List<RatingEntity> rating = EASY_RANDOM.objects(RatingEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(rating);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getRatingById(){
        RatingEntity rating = EASY_RANDOM.nextObject(RatingEntity.class);

        when(repository.findById(rating.getRatingId())).thenReturn(Optional.of(rating));
        service.getRatingById(rating.getRatingId());
        verify(repository, times(2)).findById(rating.getRatingId());
    }

    // @Test
    // public void updateRating() {
    //     RatingEntity rating = EASY_RANDOM.nextObject(RatingEntity.class);
    //     RatingDtoPost newRating = new RatingDtoPost();
    //     newRating.setRating(4L);

    //     when(repository.findById(rating.getRatingId())).thenReturn(Optional.of(rating));
    //     service.updateRating(rating.getRatingId(), newRating);
    //     verify(repository).save(rating);
    //     verify(repository, times(2)).findById(rating.getRatingId());
    // }

    // @Test
    // public void createRatingDto(){
    //     RatingEntity RatingEntity = new RatingEntity();
    //     RatingDtoPost RatingDtoPost = EASY_RANDOM.nextObject(RatingDtoPost.class);
    //     RatingEntity.setName(RatingDtoPost.getName());
    //     RatingEntity.setContact(RatingDtoPost.getContact());
    //     RatingEntity.setAddress(RatingDtoPost.getAddress());
    //     RatingEntity.setImageUrl(RatingDtoPost.getImageUrl());

    //     service.createRatingDto(RatingDtoPost);
    //     verify(repository).save(RatingEntity);
    // }

    @Test
    public void deleteRating(){
        RatingEntity ratingEntity = EASY_RANDOM.nextObject(RatingEntity.class);

        when(repository.findById(ratingEntity.getRatingId())).thenReturn(Optional.of(ratingEntity));
        service.deleteRating(ratingEntity.getRatingId());
        verify(repository, times(1)).deleteById(ratingEntity.getRatingId());
        verify(repository, times(1)).findById(ratingEntity.getRatingId());
    }

    @Test
    public void ratingValue(){
        RatingEntity rating = EASY_RANDOM.nextObject(RatingEntity.class);
        rating.setRating(4L);

        service.ratingValue(rating.getRating());
        assertEquals(true, service.ratingValue(rating.getRating()));
    }

    @Test
    public void ratingValueNotCorrect(){
        RatingEntity rating = EASY_RANDOM.nextObject(RatingEntity.class);
        rating.setRating(10L);

        service.ratingValue(rating.getRating());
        assertEquals(false, service.ratingValue(rating.getRating()));
    }

    @Test
    public void getRatingByUser(){
        List<RatingEntity> rating = EASY_RANDOM.objects(RatingEntity.class, 2)
        .collect(Collectors.toList());
        Long userid = rating.get(1).getUserIdentity();

        service.getRatingByUser(userid);
        verify(repository, times(1)).getRatingByUserIdentity(userid);
    }

    @Test
    public void getRatingByClass(){
        List<RatingEntity> rating = EASY_RANDOM.objects(RatingEntity.class, 2)
        .collect(Collectors.toList());
        Long classid = rating.get(1).getClassIdentity();

        service.getRatingByUser(classid);
        verify(repository, times(1)).getRatingByUserIdentity(classid);
    }
}
