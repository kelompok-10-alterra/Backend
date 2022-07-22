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

import com.capstone.kelompok10.model.dto.post.MembershipDtoPost;
import com.capstone.kelompok10.model.entity.MembershipEntity;
import com.capstone.kelompok10.repository.MembershipRepository;
import com.capstone.kelompok10.service.interfaces.MembershipService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class MembershipControllerTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    
    @MockBean
    private MembershipService service;

    @MockBean
    private MembershipRepository membershipRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    @DisplayName("Get All Membership")
    public void getAllMembership() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/membership/adminAccess/getAllMembership")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get All Membership With Dto")
    public void getAllMembershipWithDto() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/membership/adminAccess/getAllMembershipWithDto")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    } 

    @Test
    @DisplayName("Get Membership With id")
    public void getMembershipWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/capstone/membership/userAccess/getMembershipById?membershipId=1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post Membership Without Data")
    public void postMembershipNoData() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/membership/adminAccess/createNewMembership")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(400, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Post Membership With Data")
    public void postMembershipWithData() throws Exception{
        MembershipDtoPost membership = EASY_RANDOM.nextObject(MembershipDtoPost.class);

        JSONObject payload = new JSONObject();
        payload.put("userId", membership.getUserId());
        payload.put("memberId", membership.getMemberId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/capstone/membership/adminAccess/createNewMembership")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Delete Membership")
    public void deleteMembershipWithId() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .delete("/capstone/membership/adminAccess/deleteMembership/1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(204, response.getResponse().getStatus());
    }

    @Test
    @DisplayName("Update Membership")
    public void updateMembership() throws Exception{
        MembershipDtoPost membership = EASY_RANDOM.nextObject(MembershipDtoPost.class);
        MembershipEntity membership2 = EASY_RANDOM.nextObject(MembershipEntity.class);
        membership2.setMembershipId(1L);

        JSONObject payload = new JSONObject();
        payload.put("userId", membership.getUserId());
        payload.put("memberId", membership.getMemberId());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .put("/capstone/membership/adminAccess/updateMembership/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(payload.toString())
        .accept(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc
        .perform(requestBuilder)
        .andReturn();
        assertEquals(200, response.getResponse().getStatus());
    }
}
