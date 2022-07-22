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

import com.capstone.kelompok10.model.dto.post.InstructorDtoPost;
import com.capstone.kelompok10.model.entity.InstructorEntity;
import com.capstone.kelompok10.repository.InstructorRepository;
import com.capstone.kelompok10.service.interfaces.InstructorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class InstructorControllerTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    
    @MockBean
    private InstructorService service;

    @MockBean
    private InstructorRepository instructorRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    @DisplayName("Get All Instructor")
    public void getAllInstructor() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/instructor/userAccess/getAllInstructure")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get Instructor With id")
    public void getInstructorWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/instructor/userAccess/getInstructureWithId?instructorId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post Instructor Without Data")
    public void postInstructorNoData() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/instructor/adminAccess/createNewInstructure")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(400, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post Instructor With Data")
    public void postInstructorWithData() throws Exception{
        InstructorDtoPost instructor = EASY_RANDOM.nextObject(InstructorDtoPost.class);

        JSONObject payload = new JSONObject();
        payload.put("name", instructor.getName());
        payload.put("contact", instructor.getContact());
        payload.put("address", instructor.getAddress());
        payload.put("imageUrl", instructor.getImageUrl());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/instructor/adminAccess/createNewInstructure")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Delete Instructor")
    public void deleteInstructorWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/capstone/instructor/adminAccess/deleteInstructure/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(204, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Update Instructor")
    public void updateInstructor() throws Exception{
        InstructorDtoPost instructor = EASY_RANDOM.nextObject(InstructorDtoPost.class);
        InstructorEntity instructor2 = EASY_RANDOM.nextObject(InstructorEntity.class);
        instructor2.setInstructorId(1L);

        JSONObject payload = new JSONObject();
        payload.put("name", instructor.getName());
        payload.put("contact", instructor.getContact());
        payload.put("address", instructor.getAddress());
        payload.put("imageUrl", instructor.getImageUrl());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .put("/capstone/instructor/adminAccess/updateInstructure/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }
}
