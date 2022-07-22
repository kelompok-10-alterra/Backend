package com.capstone.kelompok10.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jeasy.random.EasyRandom;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.capstone.kelompok10.model.dto.post.NewsletterDtoPost;
import com.capstone.kelompok10.model.entity.NewsletterEntity;
import com.capstone.kelompok10.repository.NewsletterRepository;
import com.capstone.kelompok10.service.interfaces.NewsletterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class NewsletterControllerTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    
    @MockBean
    private NewsletterService service;

    @MockBean
    private NewsletterRepository newsletterRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    @DisplayName("Get All Newsletter")
    public void getAllNewsletter() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/newsletter/userAccess/getAllNewsletter")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get All Newsletter With Dto")
    public void getAllNewsletterWithDto() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/newsletter/userAccess/getAllNewsletterWithDto")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get Newsletter With id")
    public void getNewsletterWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/newsletter/userAccess/getNewsletterById?newsletterId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post Newsletter Without Data")
    public void postNewsletterNoData() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/newsletter/adminAccess/createNewNewsletter")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(400, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post Newsletter With Data")
    public void postNewsletterWithData() throws Exception{
        NewsletterDtoPost newsletter = EASY_RANDOM.nextObject(NewsletterDtoPost.class);

        JSONObject payload = new JSONObject();
        payload.put("title", newsletter.getTitle());
        payload.put("description", newsletter.getDescription());
        payload.put("imageUrl", newsletter.getImageUrl());
        payload.put("videoUrl", newsletter.getVideoUrl());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/newsletter/adminAccess/createNewNewsletter")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Delete Newsletter")
    public void deleteNewsletterWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/capstone/newsletter/adminAccess/deleteNewsletter/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(204, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Update Newsletter")
    public void updateNewsletter() throws Exception{
        NewsletterDtoPost newsletter = EASY_RANDOM.nextObject(NewsletterDtoPost.class);
        NewsletterEntity newsletter2 = EASY_RANDOM.nextObject(NewsletterEntity.class);
        newsletter2.setNewsletterId(1L);

        JSONObject payload = new JSONObject();
        payload.put("title", newsletter.getTitle());
        payload.put("description", newsletter.getDescription());
        payload.put("imageUrl", newsletter.getImageUrl());
        payload.put("videoUrl", newsletter.getVideoUrl());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .put("/capstone/newsletter/adminAccess/updateNewsletter/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }
}
