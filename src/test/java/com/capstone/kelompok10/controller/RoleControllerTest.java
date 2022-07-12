// package com.capstone.kelompok10.controller;
// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.jeasy.random.EasyRandom;
// import org.json.JSONObject;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.RequestBuilder;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import com.capstone.kelompok10.model.dto.post.RoleDtoPost;
// import com.capstone.kelompok10.model.entity.RoleEntity;
// import com.capstone.kelompok10.repository.RoleRepository;
// import com.capstone.kelompok10.service.interfaces.RoleService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class RoleControllerTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();
    
//     @MockBean
//     private RoleService service;

//     @MockBean
//     private RoleRepository roleRepository;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     ObjectMapper objectMapper;
    
//     @Test
//     @DisplayName("Get All Role")
//     public void getAllRole() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/role/managerOnly/getAllRole")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     } 

//     @Test
//     @DisplayName("Get All Role With Dto")
//     public void getAllRoleWithDto() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/role/managerOnly/getAllRoleWithDto")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     } 

//     @Test
//     @DisplayName("Get Role With id")
//     public void getRoleWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/role/managerOnly/getRoleById?roleId=1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Post Role Without Data")
//     public void postRoleNoData() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .post("/capstone/role/managerOnly/createNewRole")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(400, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Post Role With Data")
//     public void postRoleWithData() throws Exception{
//         RoleDtoPost role = EASY_RANDOM.nextObject(RoleDtoPost.class);
//         RoleEntity role2 = EASY_RANDOM.nextObject(RoleEntity.class);

//         JSONObject payload = new JSONObject();
//         payload.put("name", role.getName());

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .post("/capstone/role/managerOnly/createNewRole")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(payload.toString())
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }
// }
