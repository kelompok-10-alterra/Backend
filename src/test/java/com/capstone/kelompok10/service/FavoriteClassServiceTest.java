package com.capstone.kelompok10.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
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

// import com.capstone.kelompok10.model.dto.post.FavoriteClassDtoPost;
// import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.model.entity.FavoriteClassEntity;
// import com.capstone.kelompok10.model.entity.FavoriteEntity;
// import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.repository.FavoriteClassRepository;
import com.capstone.kelompok10.repository.FavoriteRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.implementation.FavoriteClassServiceImpl;
import com.capstone.kelompok10.service.interfaces.ClassService;
import com.capstone.kelompok10.service.interfaces.FavoriteService;
import com.capstone.kelompok10.service.interfaces.UserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FavoriteClassServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private FavoriteClassServiceImpl service;

    @Mock
    private FavoriteClassRepository repository;

    @Mock
    private FavoriteService favoriteService;

    @Mock
    private ClassService classService;

    @Mock
    private UserService userService;

    @Mock
    private ClassRepository classRepository;

    @Mock
    private FavoriteRepository favoriteRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void findAll(){
        List<FavoriteClassEntity> favoriteClass = EASY_RANDOM.objects(FavoriteClassEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(favoriteClass);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getFavoriteClassById(){
        FavoriteClassEntity favoriteClass = EASY_RANDOM.nextObject(FavoriteClassEntity.class);

        when(repository.findById(favoriteClass.getFavoriteClassId())).thenReturn(Optional.of(favoriteClass));
        service.getFavoriteClassById(favoriteClass.getFavoriteClassId());
        verify(repository, times(2)).findById(favoriteClass.getFavoriteClassId());
    }

    @Test
    public void deleteFavoriteClass(){
        FavoriteClassEntity favoriteClassEntity = EASY_RANDOM.nextObject(FavoriteClassEntity.class);

        when(repository.findById(favoriteClassEntity.getFavoriteClassId())).thenReturn(Optional.of(favoriteClassEntity));
        service.deleteFavoriteClass(favoriteClassEntity.getFavoriteClassId());
        verify(repository, times(1)).deleteById(favoriteClassEntity.getFavoriteClassId());
        verify(repository, times(1)).findById(favoriteClassEntity.getFavoriteClassId());
    }

    // @Test
    // public void createFavoriteClassDto(){
    //     FavoriteClassEntity favoriteClassEntity = EASY_RANDOM.nextObject(FavoriteClassEntity.class);
    //     FavoriteClassDtoPost favoriteClassDtoPost = EASY_RANDOM.nextObject(FavoriteClassDtoPost.class);
    //     UserEntity user = new UserEntity();
    //     user.setUserId(favoriteClassDtoPost.getUserId());
    //     ClassEntity classes = new ClassEntity();
    //     classes.setClassId(favoriteClassDtoPost.getClassId());
    //     favoriteClassEntity.setUser(user);
    //     favoriteClassEntity.setClasses(classes);
    //     favoriteClassEntity.setClassName("bebas");

    //     when(repository.save(favoriteClassEntity)).thenReturn(favoriteClassEntity);
    //     service.createFavoriteClassDto(favoriteClassDtoPost);
    //     verify(repository).save(favoriteClassEntity);
    // }
}
