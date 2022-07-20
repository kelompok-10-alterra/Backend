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

// import com.capstone.kelompok10.model.entity.HistoryEntity;
// import com.capstone.kelompok10.repository.HistoryRepository;
// import com.capstone.kelompok10.service.implementation.HistoryServiceImpl;

// @ExtendWith(MockitoExtension.class)
// @ExtendWith(SpringExtension.class)
// public class HistoryServiceTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();

//     @InjectMocks
//     private HistoryServiceImpl service;

//     @Mock
//     private HistoryRepository repository;

//     @Test
//     void findAll(){
//         List<HistoryEntity> history = EASY_RANDOM.objects(HistoryEntity.class, 2)
//         .collect(Collectors.toList());

//         when(repository.findAll()).thenReturn(history);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getHistoryById(){
//         HistoryEntity history = EASY_RANDOM.nextObject(HistoryEntity.class);

//         when(repository.findById(history.getHistoryId())).thenReturn(Optional.of(history));
//         service.getHistoryById(history.getHistoryId());
//         verify(repository, times(2)).findById(history.getHistoryId());
//     }
// }
