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

import com.capstone.kelompok10.model.dto.post.RatingDtoPost;
import com.capstone.kelompok10.model.entity.RatingEntity;
import com.capstone.kelompok10.repository.RatingRepository;
import com.capstone.kelompok10.service.interfaces.RatingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    
    @MockBean
    private RatingService service;

    @MockBean
    private RatingRepository ratingRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Get All Rating")
    public void getAllRating() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/rating/adminAccess/getAllRating")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get Rating With id")
    public void getRatingWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/rating/userAccess/getRatingById?ratingId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Get All Rating By UserId")
    public void getAllRatingByUserId() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/rating/userAccess/getAllRatingByUserId?userId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get All Rating By ClassId")
    public void getAllRatingByClassId() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/rating/adminAccess/getAllRatingByClassId?classId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Post Rating Without Data")
    public void postRatingNoData() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/rating/userAccess/createNewRating")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(400, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post Rating With Data")
    public void postRatingWithData() throws Exception{
        RatingDtoPost rating = EASY_RANDOM.nextObject(RatingDtoPost.class);

        JSONObject payload = new JSONObject();
        payload.put("rating", rating.getRating());
        payload.put("userId", rating.getUserId());
        payload.put("classId", rating.getClassId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/rating/userAccess/createNewRating")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    // @Test
    // @DisplayName("Delete Rating")
    // public void deleteRatingWithId() throws Exception{

    //     RequestBuilder requestBuilder = MockMvcRequestBuilders
    //     .delete("/capstone/rating/adminAccess/deleteRating/1")
    //     .contentType(MediaType.APPLICATION_JSON)
    //     .accept(MediaType.APPLICATION_JSON);

    //     MvcResult response = mockMvc
    //     .perform(requestBuilder)
    //     .andReturn();
    //     assertEquals(204, response.getResponse().getStatus());
    // }

    @Test
    @DisplayName("Update Rating")
    public void updateRating() throws Exception{
        RatingDtoPost rating = EASY_RANDOM.nextObject(RatingDtoPost.class);
        RatingEntity rating2 = EASY_RANDOM.nextObject(RatingEntity.class);
        rating2.setRatingId(1L);

        JSONObject payload = new JSONObject();
        payload.put("rating", rating.getRating());
        payload.put("userId", rating.getUserId());
        payload.put("classId", rating.getClassId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .put("/capstone/rating/userAccess/updateRating?ratingId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }
}
