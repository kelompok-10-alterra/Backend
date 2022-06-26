package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.kelompok10.model.dto.get.ContentDtoGet;
import com.capstone.kelompok10.model.dto.post.ContentDtoPost;
import com.capstone.kelompok10.model.entity.ContentEntity;
import com.capstone.kelompok10.service.interfaces.ContentService;

@RestController
@RequestMapping("/capstone/content")
public class ContentController {
    ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService){
        this.contentService = contentService;
    }

    @GetMapping("/userAccess/getAllContent")
    public ResponseEntity<List<ContentEntity>> findAll(){
        List<ContentEntity> contents = contentService.findAll();
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @GetMapping("/userAccess/{offset}/{pageSize}")
    public ResponseEntity<Page<ContentEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
        Page<ContentEntity> contents = contentService.findAllPagination(offset, pageSize);
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @GetMapping("/userAccess/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<ContentEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        Page<ContentEntity> contents = contentService.findAllPaginationSort(offset, pageSize, field);
        return new ResponseEntity<>(contents, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getAllContentWithDto")
    public ResponseEntity<List<ContentDtoGet>> findAllDto(){
        List<ContentDtoGet> contentDtos = contentService.findAllDto();
        return new ResponseEntity<>(contentDtos, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getContentById")
    public ResponseEntity<ContentEntity> getContentById(@RequestParam("contentId") Long contentId){
        return new ResponseEntity<>(contentService.getContentById(contentId), HttpStatus.OK);
    }

    @PostMapping("/adminAccess/createNewContent")
    public ResponseEntity<ContentDtoPost> createContentDto(@RequestBody ContentDtoPost contentDtoPost){
        contentService.createContentDto(contentDtoPost);
        return new ResponseEntity<>(contentDtoPost, HttpStatus.OK);
    }

    @PutMapping("/adminAccess/updateContent/{contentId}")
    public ResponseEntity<ContentEntity> updateContent(@PathVariable("contentId") Long contentId, @RequestBody ContentDtoPost contentDtoPost){
        contentService.updateContent(contentId, contentDtoPost);
        return new ResponseEntity<>(contentService.getContentById(contentId), HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteContent/{contentId}")
    public ResponseEntity<ContentEntity> deleteContent(@PathVariable("contentId") Long contentId){
        contentService.deleteContent(contentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
