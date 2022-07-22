package com.capstone.kelompok10.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.capstone.kelompok10.repository.FavoriteRepository;
import com.capstone.kelompok10.service.interfaces.FavoriteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class FavoriteControllerTest {    
    @MockBean
    private FavoriteService service;

    @MockBean
    private FavoriteRepository favoriteRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Get All Favorite")
    public void getAllFavorite() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/favorite/adminAccess/getAllFavorite")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get Favorite With id")
    public void getFavoriteWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/favorite/userAccess/getFavoriteById?favoriteId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }
}
