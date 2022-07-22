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

import com.capstone.kelompok10.model.dto.post.FavoriteClassDtoPost;
import com.capstone.kelompok10.repository.FavoriteClassRepository;
import com.capstone.kelompok10.service.interfaces.FavoriteClassService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class FavoriteClassControllerTest{
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    
    @MockBean
    private FavoriteClassService service;

    @MockBean
    private FavoriteClassRepository favoriteClassRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Get All FavoriteClass")
    public void getAllFavoriteClass() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/favoriteClass/adminAccess/getAllFavoriteClass")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get FavoriteClass With id")
    public void getFavoriteClassWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/favoriteClass/userAccess/getFavoriteClassById?favoriteClassId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post FavoriteClass Without Data")
    public void postFavoriteClassNoData() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/favoriteClass/userAccess/createNewFavoriteClass")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(400, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post FavoriteClass With Data")
    public void postFavoriteClassWithData() throws Exception{
        FavoriteClassDtoPost favoriteClasses = EASY_RANDOM.nextObject(FavoriteClassDtoPost.class);

        JSONObject payload = new JSONObject();
        payload.put("classId", favoriteClasses.getClassId());
        payload.put("userId", favoriteClasses.getUserId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/favoriteClass/userAccess/createNewFavoriteClass")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Delete FavoriteClass")
    public void deleteFavoriteClassWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/capstone/favoriteClass/userAccess/deleteFavoriteClass/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(204, response.getResponse().getStatus());
    }
}
