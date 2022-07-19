// package com.capstone.kelompok10.service;

// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.jeasy.random.EasyRandom;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import com.capstone.kelompok10.model.entity.FavoriteClassEntity;
// import com.capstone.kelompok10.model.entity.FavoriteEntity;
// import com.capstone.kelompok10.repository.FavoriteClassRepository;
// import com.capstone.kelompok10.repository.FavoriteRepository;
// import com.capstone.kelompok10.service.implementation.FavoriteServiceImpl;
// import com.capstone.kelompok10.service.interfaces.FavoriteClassService;

// @ExtendWith(MockitoExtension.class)
// @ExtendWith(SpringExtension.class)
// public class FavoriteServiceTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();

//     @InjectMocks
//     private FavoriteServiceImpl service;

//     @Mock
//     private FavoriteRepository repository;

//     @Mock
//     private FavoriteClassRepository favoriteClassRepository;

//     @Mock
//     private FavoriteClassService favoriteClassService;

//     @Test
//     void findAll(){
//         List<FavoriteEntity> favorite = EASY_RANDOM.objects(FavoriteEntity.class, 2)
//         .collect(Collectors.toList());

//         when(repository.findAll()).thenReturn(favorite);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getFavoriteById(){
//         FavoriteEntity favorite = EASY_RANDOM.nextObject(FavoriteEntity.class);

//         when(repository.findById(favorite.getFavoriteId())).thenReturn(Optional.of(favorite));
//         service.getFavoriteById(favorite.getFavoriteId());
//         verify(repository, times(2)).findById(favorite.getFavoriteId());
//     }

//     @Test
//     void addFavoriteClassToFavorite(){
//         FavoriteClassEntity favoriteClassEntity = EASY_RANDOM.nextObject(FavoriteClassEntity.class);
//         FavoriteEntity favoriteEntity = EASY_RANDOM.nextObject(FavoriteEntity.class);

//         when(repository.findById(favoriteEntity.getFavoriteId())).thenReturn(Optional.of(favoriteEntity));
//         service.addFavoriteClassToFavorite(favoriteClassEntity.getFavoriteClassId(), favoriteEntity.getFavoriteId());
//         verify(repository, times(1)).findById(favoriteEntity.getFavoriteId());
//         // verify(repository, times(1)).save(favoriteEntity);
//     }
// }
