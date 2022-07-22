package com.capstone.kelompok10.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jeasy.random.EasyRandom;
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

import com.capstone.kelompok10.repository.CategoryRepository;
import com.capstone.kelompok10.service.interfaces.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    
    @MockBean
    private CategoryService service;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    @DisplayName("Get All Category")
    public void getAllCategory() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/category/userAccess/getAllCategory")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get Category With id")
    public void getCategoryWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/category/userAccess/getCategoryById?categoryId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    // @Test
    // @DisplayName("Post Category Without Data")
    // public void postCategoryNoData() throws Exception{
    //     RequestBuilder requestBuilder = MockMvcRequestBuilders
    //     .post("/capstone/category/adminAccess/createNewCategory")
    //     .contentType(MediaType.APPLICATION_JSON)
    //     .accept(MediaType.APPLICATION_JSON);

    //     MvcResult response = mockMvc
    //     .perform(requestBuilder)
    //     .andReturn();
    //     assertEquals(400, response.getResponse().getStatus());
    // }

    // @Test
    // @DisplayName("Post Category With Data")
    // public void postCategoryWithData() throws Exception{
    //     CategoryDtoPost category = EASY_RANDOM.nextObject(CategoryDtoPost.class);

    //     JSONObject payload = new JSONObject();
    //     payload.put("name", category.getName());

    //     RequestBuilder requestBuilder = MockMvcRequestBuilders
    //     .post("/capstone/category/adminAccess/createNewCategory")
    //     .contentType(MediaType.APPLICATION_JSON)
    //     .content(payload.toString())
    //     .accept(MediaType.APPLICATION_JSON);

    //     MvcResult response = mockMvc
    //     .perform(requestBuilder)
    //     .andReturn();
    //     assertEquals(200, response.getResponse().getStatus());
    // }

    // @Test
    // @DisplayName("Delete Category")
    // public void deleteCategoryWithId() throws Exception{

    //     RequestBuilder requestBuilder = MockMvcRequestBuilders
    //     .delete("/capstone/category/adminAccess/deleteCategory/1")
    //     .contentType(MediaType.APPLICATION_JSON)
    //     .accept(MediaType.APPLICATION_JSON);

    //     MvcResult response = mockMvc
    //     .perform(requestBuilder)
    //     .andReturn();
    //     assertEquals(204, response.getResponse().getStatus());
    // }

    // @Test
    // @DisplayName("Update Category")
    // public void updateCategory() throws Exception{
    //     CategoryDtoPost category = EASY_RANDOM.nextObject(CategoryDtoPost.class);
    //     CategoryEntity category2 = EASY_RANDOM.nextObject(CategoryEntity.class);
    //     category2.setCategoryId(1L);

    //     JSONObject payload = new JSONObject();
    //     payload.put("name", category.getName());

    //     RequestBuilder requestBuilder = MockMvcRequestBuilders
    //     .put("/capstone/category/adminAccess/updateCategory/1")
    //     .contentType(MediaType.APPLICATION_JSON)
    //     .content(payload.toString())
    //     .accept(MediaType.APPLICATION_JSON);

    //     MvcResult response = mockMvc
    //     .perform(requestBuilder)
    //     .andReturn();
    //     assertEquals(200, response.getResponse().getStatus());
    // }
}
