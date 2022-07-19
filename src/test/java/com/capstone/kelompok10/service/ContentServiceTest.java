// package com.capstone.kelompok10.service;

// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
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

// import com.capstone.kelompok10.model.dto.get.ContentDtoGet;
// import com.capstone.kelompok10.model.dto.post.ContentDtoPost;
// import com.capstone.kelompok10.model.entity.ContentEntity;
// import com.capstone.kelompok10.repository.ContentRepository;
// import com.capstone.kelompok10.service.implementation.ContentServiceImpl;

// @ExtendWith(MockitoExtension.class)
// @ExtendWith(SpringExtension.class)
// public class ContentServiceTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();

//     @InjectMocks
//     private ContentServiceImpl service;

//     @Mock
//     private ContentRepository repository;

//     @Test
//     void findAll(){
//         List<ContentEntity> content = EASY_RANDOM.objects(ContentEntity.class, 2)
//         .collect(Collectors.toList());

//         when(repository.findAll()).thenReturn(content);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void findAllDto(){
//         List<ContentEntity> content = EASY_RANDOM.objects(ContentEntity.class, 2)
//         .collect(Collectors.toList());
//         List<ContentDtoGet> dto = new ArrayList<>();
//         content.forEach(isi ->{
//             ContentDtoGet dto2 = new ContentDtoGet();
//             dto2.setContentId(isi.getContentId());
//             dto2.setTitle(isi.getTitle());
//             dto2.setDescription(isi.getDescription());

//             dto.add(dto2);
//         });

//         when(repository.findAll()).thenReturn(content);
//         service.findAllDto();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getContentById(){
//         ContentEntity content = EASY_RANDOM.nextObject(ContentEntity.class);

//         when(repository.findById(content.getContentId())).thenReturn(Optional.of(content));
//         service.getContentById(content.getContentId());
//         verify(repository, times(2)).findById(content.getContentId());
//     }

//     @Test
//     void updateContent(){
//         ContentEntity content = EASY_RANDOM.nextObject(ContentEntity.class);
//         ContentDtoPost dto = new ContentDtoPost();
//         dto.setTitle("AAAA");

//         when(repository.findById(content.getContentId())).thenReturn(Optional.of(content));
//         service.updateContent(content.getContentId(), dto);
//         verify(repository, times(1)).save(content);
//         verify(repository, times(2)).findById(content.getContentId());
//     }

//     @Test
//     public void deleteContent(){
//         ContentEntity contentEntity = EASY_RANDOM.nextObject(ContentEntity.class);

//         when(repository.findById(contentEntity.getContentId())).thenReturn(Optional.of(contentEntity));
//         service.deleteContent(contentEntity.getContentId());
//         verify(repository, times(1)).deleteById(contentEntity.getContentId());
//         verify(repository, times(1)).findById(contentEntity.getContentId());
//     }

//     @Test
//     public void createContentDto(){
//         ContentEntity contentEntity = new ContentEntity();
//         ContentDtoPost contentDtoPost = EASY_RANDOM.nextObject(ContentDtoPost.class);
//         contentEntity.setTitle(contentDtoPost.getTitle());
//         contentEntity.setDescription(contentDtoPost.getDescription());
//         contentEntity.setImageUrl(contentDtoPost.getImageUrl());
//         contentEntity.setVideoUrl(contentDtoPost.getVideoUrl());

//         service.createContentDto(contentDtoPost);
//         verify(repository).save(contentEntity);
//     }
// }
