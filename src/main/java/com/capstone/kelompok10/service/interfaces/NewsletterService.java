package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.NewsletterDtoGet;
import com.capstone.kelompok10.model.dto.post.NewsletterDtoPost;
import com.capstone.kelompok10.model.entity.NewsletterEntity;

public interface NewsletterService {
    List<NewsletterEntity> findAll();
    List<NewsletterDtoGet> findAllDto();
    // Page<NewsletterEntity> findAllPagination(int offset, int pageSize);
    // Page<NewsletterEntity> findAllPaginationSort(int offset, int pageSize, String field);
    NewsletterEntity getNewsletterById(Long newsletterId);
    void createNewsletterDto(NewsletterDtoPost newsletterDtoPost);
    void updateNewsletter(Long newsletterId, NewsletterDtoPost newsletterDtoPost);
    void deleteNewsletter(Long newsletterId);
}
