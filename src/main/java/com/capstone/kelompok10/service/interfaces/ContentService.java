package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capstone.kelompok10.model.dto.get.ContentDtoGet;
import com.capstone.kelompok10.model.dto.post.ContentDtoPost;
import com.capstone.kelompok10.model.entity.ContentEntity;

public interface ContentService {
    List<ContentEntity> findAll();
    List<ContentDtoGet> findAllDto();
    Page<ContentEntity> findAllPagination(int offset, int pageSize);
    Page<ContentEntity> findAllPaginationSort(int offset, int pageSize, String field);
    ContentEntity getContentById(Long contentId);
    void createContentDto(ContentDtoPost contentDtoPost);
    void updateContent(Long contentId, ContentDtoPost contentDtoPost);
    void deleteContent(Long contentId);
}
