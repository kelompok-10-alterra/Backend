package com.capstone.kelompok10.service.implementation;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.kelompok10.model.dto.get.ContentDtoGet;
import com.capstone.kelompok10.model.dto.post.ContentDtoPost;
import com.capstone.kelompok10.model.entity.ContentEntity;
import com.capstone.kelompok10.repository.ContentRepository;
import com.capstone.kelompok10.service.interfaces.ContentService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {
    ContentRepository contentRepository;

    @Override
    public List<ContentEntity> findAll() {
        log.info("Get all Content without DTO");
        List<ContentEntity> content = new ArrayList<>();
        contentRepository.findAll().forEach(content::add);
        return content;
    }
    
    // @Override
    // public Page<ContentEntity> findAllPagination(int offset, int pageSize) {
    //     log.info("Get all Content with Pagination");
    //     Page<ContentEntity> content = contentRepository.findAll(PageRequest.of(offset, pageSize));
    //     return content;
    // }

    // @Override
    // public Page<ContentEntity> findAllPaginationSort(int offset, int pageSize, String field){
    //     log.info("Get all Content with Pagination and Sorting");
    //     Page<ContentEntity> content = contentRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    //     return content;
    // }

    @Override
    public List<ContentDtoGet> findAllDto() {
        log.info("Get all Content with DTO");
        List<ContentEntity> contents = contentRepository.findAll();
        List<ContentDtoGet> contentDtos = new ArrayList<>();
        
        contents.forEach(isi ->{
            ContentDtoGet dto = new ContentDtoGet();
            dto.setContentId(isi.getContentId());
            dto.setTitle(isi.getTitle());
            dto.setDescription(isi.getDescription());

            contentDtos.add(dto);
        });
        return contentDtos;
    }

    @Override
    public ContentEntity getContentById(Long contentId) {
        if(contentRepository.findById(contentId) != null){
            log.info("Content with id {} found", contentId);
            return contentRepository.findById(contentId).get();
        }else{
            log.info("Content with id {} not found", contentId);
            throw new IllegalStateException("Content not Found");
        }
    }

    @Override
    public void updateContent(Long contentId, ContentDtoPost contentDtoPost) {
        if(contentRepository.findById(contentId) != null){
            ContentEntity content2 = contentRepository.findById(contentId).get();
            content2.setTitle(contentDtoPost.getTitle());
            content2.setDescription(contentDtoPost.getDescription());
            content2.setImageUrl(contentDtoPost.getImageUrl());
            content2.setVideoUrl(contentDtoPost.getVideoUrl());

            contentRepository.save(content2);
            log.info("Content updated");
        }else{
            log.info("Content with id {} not found", contentId);
            throw new IllegalStateException("Content not Found");
        }
    }

    @Override
    public void deleteContent(Long contentId) {
        if(contentRepository.findById(contentId) != null){
            contentRepository.deleteById(contentId);
            log.info("Content with id {} successfully deleted", contentId);
        }else{
            log.info("Content with id {} not found", contentId);
            throw new IllegalStateException("Content not Found");
        }
    }

    @Override
    public void createContentDto(ContentDtoPost contentDtoPost) {
        if(contentRepository.findByTitle(contentDtoPost.getTitle()) == null){
            ContentEntity contentEntity = new ContentEntity();
            contentEntity.setTitle(contentDtoPost.getTitle());
            contentEntity.setDescription(contentDtoPost.getDescription());
            contentEntity.setImageUrl(contentDtoPost.getImageUrl());
            contentEntity.setVideoUrl(contentDtoPost.getVideoUrl());

            contentRepository.save(contentEntity);
            log.info("Content created");
        }else{
            log.info("Content with Title {} already exist", contentDtoPost.getTitle());
            throw new IllegalStateException("Content already Exist");
        }
    }
}
