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

// import com.capstone.kelompok10.model.dto.get.NewsletterDtoGet;
// import com.capstone.kelompok10.model.dto.post.NewsletterDtoPost;
// import com.capstone.kelompok10.model.entity.NewsletterEntity;
// import com.capstone.kelompok10.repository.NewsletterRepository;
// import com.capstone.kelompok10.service.implementation.NewsletterServiceImpl;

// @ExtendWith(MockitoExtension.class)
// @ExtendWith(SpringExtension.class)
// public class NewsletterServiceTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();

//     @InjectMocks
//     private NewsletterServiceImpl service;

//     @Mock
//     private NewsletterRepository repository;
    
//     @Test
//     void findAll(){
//         List<NewsletterEntity> Newsletter = EASY_RANDOM.objects(NewsletterEntity.class, 2)
//         .collect(Collectors.toList());

//         when(repository.findAll()).thenReturn(Newsletter);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void findAllDto(){
//         List<NewsletterEntity> newsletter = EASY_RANDOM.objects(NewsletterEntity.class, 2)
//         .collect(Collectors.toList());
//         List<NewsletterDtoGet> dtos = new ArrayList<>();
//         newsletter.forEach(isi ->{
//             NewsletterDtoGet dto = new NewsletterDtoGet();
//             dto.setNewsletterId(isi.getNewsletterId());
//             dto.setTitle(isi.getTitle());
//             dto.setDescription(isi.getDescription());

//             dtos.add(dto);
//         });
        
//         when(repository.findAll()).thenReturn(newsletter);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getNewsletterById(){
//         NewsletterEntity newsletter = EASY_RANDOM.nextObject(NewsletterEntity.class);

//         when(repository.findById(newsletter.getNewsletterId())).thenReturn(Optional.of(newsletter));
//         service.getNewsletterById(newsletter.getNewsletterId());
//         verify(repository, times(2)).findById(newsletter.getNewsletterId());
//     }

//     @Test
//     public void updateNewsletter() {
//         NewsletterEntity newsletter = EASY_RANDOM.nextObject(NewsletterEntity.class);
//         NewsletterDtoPost newNewsletter = new NewsletterDtoPost();
//         newNewsletter.setTitle("Baru");

//         when(repository.findById(newsletter.getNewsletterId())).thenReturn(Optional.of(newsletter));
//         service.updateNewsletter(newsletter.getNewsletterId(), newNewsletter);
//         verify(repository).save(newsletter);
//         verify(repository, times(2)).findById(newsletter.getNewsletterId());
//     }

//     @Test
//     public void createNewsletterDto(){
//         NewsletterEntity newsletterEntity = new NewsletterEntity();
//         NewsletterDtoPost newsletterDtoPost = EASY_RANDOM.nextObject(NewsletterDtoPost.class);
//         newsletterEntity.setTitle(newsletterDtoPost.getTitle());
//         newsletterEntity.setDescription(newsletterDtoPost.getDescription());
//         newsletterEntity.setImageUrl(newsletterDtoPost.getImageUrl());
//         newsletterEntity.setVideoUrl(newsletterDtoPost.getVideoUrl());

//         service.createNewsletterDto(newsletterDtoPost);
//         verify(repository).save(newsletterEntity);
//     }

//     @Test
//     public void deleteNewsletter(){
//         NewsletterEntity newsletterEntity = EASY_RANDOM.nextObject(NewsletterEntity.class);

//         when(repository.findById(newsletterEntity.getNewsletterId())).thenReturn(Optional.of(newsletterEntity));
//         service.deleteNewsletter(newsletterEntity.getNewsletterId());
//         verify(repository, times(1)).deleteById(newsletterEntity.getNewsletterId());
//         verify(repository, times(1)).findById(newsletterEntity.getNewsletterId());
//     }
// }
