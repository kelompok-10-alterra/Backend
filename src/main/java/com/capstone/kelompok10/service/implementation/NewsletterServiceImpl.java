package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.get.NewsletterDtoGet;
import com.capstone.kelompok10.model.dto.post.NewsletterDtoPost;
import com.capstone.kelompok10.model.entity.NewsletterEntity;
import com.capstone.kelompok10.repository.NewsletterRepository;
import com.capstone.kelompok10.service.interfaces.NewsletterService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class NewsletterServiceImpl implements NewsletterService {
    NewsletterRepository newsletterRepository;

    @Override
    public List<NewsletterEntity> findAll() {
        log.info("Get all Newsletter without DTO");
        List<NewsletterEntity> newsletter = new ArrayList<>();
        newsletterRepository.findAll().forEach(newsletter::add);
        return newsletter;
    }
    
    // @Override
    // public Page<NewsletterEntity> findAllPagination(int offset, int pageSize) {
    //     log.info("Get all Newsletter with Pagination");
    //     Page<NewsletterEntity> newsletter = newsletterRepository.findAll(PageRequest.of(offset, pageSize));
    //     return newsletter;
    // }

    // @Override
    // public Page<NewsletterEntity> findAllPaginationSort(int offset, int pageSize, String field){
    //     log.info("Get all Newsletter with Pagination and Sorting");
    //     Page<NewsletterEntity> newsletter = newsletterRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    //     return newsletter;
    // }

    @Override
    public List<NewsletterDtoGet> findAllDto() {
        log.info("Get all Newsletter with DTO");
        List<NewsletterEntity> newsletters = newsletterRepository.findAll();
        List<NewsletterDtoGet> newsletterDtos = new ArrayList<>();
        
        newsletters.forEach(isi ->{
            NewsletterDtoGet dto = new NewsletterDtoGet();
            dto.setNewsletterId(isi.getNewsletterId());
            dto.setTitle(isi.getTitle());
            dto.setDescription(isi.getDescription());

            newsletterDtos.add(dto);
        });
        return newsletterDtos;
    }

    @Override
    public NewsletterEntity getNewsletterById(Long newsletterId) {
        if(newsletterRepository.findById(newsletterId) != null){
            log.info("Newsletter with id {} found", newsletterId);
            return newsletterRepository.findById(newsletterId).get();
        }else{
            log.info("Newsletter with id {} not found", newsletterId);
            throw new IllegalStateException("Newsletter not Found");
        }
    }

    @Override
    public void updateNewsletter(Long newsletterId, NewsletterDtoPost newsletterDtoPost) {
        if(newsletterRepository.findById(newsletterId) != null){
            NewsletterEntity newsletter2 = newsletterRepository.findById(newsletterId).get();
            newsletter2.setTitle(newsletterDtoPost.getTitle());
            newsletter2.setDescription(newsletterDtoPost.getDescription());
            newsletter2.setImageUrl(newsletterDtoPost.getImageUrl());
            newsletter2.setVideoUrl(newsletterDtoPost.getVideoUrl());

            newsletterRepository.save(newsletter2);
            log.info("Newsletter updated");
        }else{
            log.info("Newsletter with id {} not found", newsletterId);
            throw new IllegalStateException("Newsletter not Found");
        }
    }

    @Override
    public void deleteNewsletter(Long newsletterId) {
        if(newsletterRepository.findById(newsletterId) != null){
            newsletterRepository.deleteById(newsletterId);
            log.info("Newsletter with id {} successfully deleted", newsletterId);
        }else{
            log.info("Newsletter with id {} not found", newsletterId);
            throw new IllegalStateException("Newsletter not Found");
        }
    }

    @Override
    public void createNewsletterDto(NewsletterDtoPost newsletterDtoPost) {
        if(newsletterRepository.findByTitle(newsletterDtoPost.getTitle()) == null){
            NewsletterEntity newsletterEntity = new NewsletterEntity();
            newsletterEntity.setTitle(newsletterDtoPost.getTitle());
            newsletterEntity.setDescription(newsletterDtoPost.getDescription());
            newsletterEntity.setImageUrl(newsletterDtoPost.getImageUrl());
            newsletterEntity.setVideoUrl(newsletterDtoPost.getVideoUrl());

            newsletterRepository.save(newsletterEntity);
            log.info("Newsletter created");
        }else{
            log.info("Newsletter with Title {} already exist", newsletterDtoPost.getTitle());
            throw new IllegalStateException("Newsletter already Exist");
        }
    }
}
