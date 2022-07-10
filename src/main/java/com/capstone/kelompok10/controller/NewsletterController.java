package com.capstone.kelompok10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.capstone.kelompok10.model.dto.get.NewsletterDtoGet;
import com.capstone.kelompok10.model.dto.post.NewsletterDtoPost;
import com.capstone.kelompok10.model.entity.NewsletterEntity;
import com.capstone.kelompok10.service.interfaces.NewsletterService;

@RestController
@RequestMapping("/capstone/newsletter")
public class NewsletterController {
    NewsletterService newsletterService;

    @Autowired
    public NewsletterController(NewsletterService newsletterService){
        this.newsletterService = newsletterService;
    }

    @GetMapping("/userAccess/getAllNewsletter")
    public ResponseEntity<List<NewsletterEntity>> findAll(){
        List<NewsletterEntity> newsletters = newsletterService.findAll();
        return new ResponseEntity<>(newsletters, HttpStatus.OK);
    }

    // @GetMapping("/userAccess/{offset}/{pageSize}")
    // public ResponseEntity<Page<NewsletterEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
    //     Page<NewsletterEntity> newsletters = newsletterService.findAllPagination(offset, pageSize);
    //     return new ResponseEntity<>(newsletters, HttpStatus.OK);
    // }

    // @GetMapping("/userAccess/{offset}/{pageSize}/{field}")
    // public ResponseEntity<Page<NewsletterEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
    //     Page<NewsletterEntity> newsletters = newsletterService.findAllPaginationSort(offset, pageSize, field);
    //     return new ResponseEntity<>(newsletters, HttpStatus.OK);
    // }

    @GetMapping("/userAccess/getAllNewsletterWithDto")
    public ResponseEntity<List<NewsletterDtoGet>> findAllDto(){
        List<NewsletterDtoGet> newsletterDtos = newsletterService.findAllDto();
        return new ResponseEntity<>(newsletterDtos, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getNewsletterById")
    public ResponseEntity<NewsletterEntity> getNewsletterById(@RequestParam("newsletterId") Long newsletterId){
        return new ResponseEntity<>(newsletterService.getNewsletterById(newsletterId), HttpStatus.OK);
    }

    @PostMapping("/adminAccess/createNewNewsletter")
    public ResponseEntity<NewsletterDtoPost> createNewsletterDto(@RequestBody NewsletterDtoPost newsletterDtoPost){
        newsletterService.createNewsletterDto(newsletterDtoPost);
        return new ResponseEntity<>(newsletterDtoPost, HttpStatus.OK);
    }

    @PutMapping("/adminAccess/updateNewsletter/{newsletterId}")
    public ResponseEntity<NewsletterEntity> updateNewsletter(@PathVariable("newsletterId") Long newsletterId, @RequestBody NewsletterDtoPost newsletterDtoPost){
        newsletterService.updateNewsletter(newsletterId, newsletterDtoPost);
        return new ResponseEntity<>(newsletterService.getNewsletterById(newsletterId), HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteNewsletter/{newsletterId}")
    public ResponseEntity<NewsletterEntity> deleteNewsletter(@PathVariable("newsletterId") Long newsletterId){
        newsletterService.deleteNewsletter(newsletterId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
