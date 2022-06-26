package com.capstone.kelompok10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.kelompok10.model.entity.NewsletterEntity;

@Repository
public interface NewsletterRepository extends JpaRepository<NewsletterEntity, Long> {
    NewsletterEntity findByTitle(String title);
}
