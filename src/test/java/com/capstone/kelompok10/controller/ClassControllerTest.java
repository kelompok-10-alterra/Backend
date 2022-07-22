package com.capstone.kelompok10.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

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

import com.capstone.kelompok10.model.dto.post.ClassDtoPost;
import com.capstone.kelompok10.model.entity.ClassEntity;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.service.interfaces.ClassService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassControllerTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    
    @MockBean
    private ClassService service;

    @MockBean
    private ClassRepository classRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Get All Class")
    public void getAllClass() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/class/userAccess/getAllClass")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get Class With id Dto")
    public void getClassWithIdDto() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/class/userAccess/getClassByIdDto?classId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post Class Without Data")
    public void postClassNoData() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/class/adminAccess/createNewClass")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(400, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post Class With Data")
    public void postClassWithData() throws Exception{
        ClassDtoPost classes = EASY_RANDOM.nextObject(ClassDtoPost.class);

        JSONObject payload = new JSONObject();
        payload.put("status", classes.getStatus());
        payload.put("description", classes.getDescription());
        payload.put("capacity", classes.getCapacity());
        payload.put("schedule", "22/07/2022");
        payload.put("hour", classes.getHour());
        payload.put("price", classes.getPrice());
        payload.put("imagerUrl", classes.getImageUrl());
        payload.put("instructorId", classes.getInstructorId());
        payload.put("categoryId", classes.getCategoryId());
        payload.put("roomId", classes.getRoomId());
        payload.put("typeId", classes.getTypeId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/class/adminAccess/createNewClass")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Delete Class")
    public void deleteClassWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/capstone/class/adminAccess/deleteClass/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(204, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Update Class")
    public void updateClass() throws Exception{
        ClassDtoPost classes = EASY_RANDOM.nextObject(ClassDtoPost.class);
        ClassEntity class2 = EASY_RANDOM.nextObject(ClassEntity.class);
        class2.setClassId(1L);

        JSONObject payload = new JSONObject();
        payload.put("status", classes.getStatus());
        payload.put("description", classes.getDescription());
        payload.put("capacity", classes.getCapacity());
        payload.put("schedule", "22/07/2022");
        payload.put("hour", classes.getHour());
        payload.put("price", classes.getPrice());
        payload.put("imagerUrl", classes.getImageUrl());
        payload.put("instructorId", classes.getInstructorId());
        payload.put("categoryId", classes.getCategoryId());
        payload.put("roomId", classes.getRoomId());
        payload.put("typeId", classes.getTypeId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .put("/capstone/class/adminAccess/updateClass/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Get Class By Type")
    public void getClassByType() throws Exception{
        List<ClassEntity> classes = EASY_RANDOM.objects(ClassEntity.class, 2).collect(Collectors.toList());
        classes.get(1).setTypeName("Baru");
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/class/userAccess/getClassByType?typeName=Baru")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Get Class By Category")
    public void getClassByCategory() throws Exception{
        List<ClassEntity> classes = EASY_RANDOM.objects(ClassEntity.class, 2).collect(Collectors.toList());
        classes.get(1).setCategoryName("Baru");
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/class/userAccess/getClassByCategory?categoryName=Baru")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Get User by ClassId")
    public void getUserByClassId() throws Exception{
        List<ClassEntity> classes = EASY_RANDOM.objects(ClassEntity.class, 2).collect(Collectors.toList());
        classes.get(1).setClassId(1L);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/class/adminAccess/getUserByClassId?classId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }
}
