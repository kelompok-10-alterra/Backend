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

// import com.capstone.kelompok10.model.dto.post.UserDtoPost;
// import com.capstone.kelompok10.model.entity.UserEntity;
// import com.capstone.kelompok10.repository.UserRepository;
// import com.capstone.kelompok10.service.interfaces.UserService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class UserControllerTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();
    
//     @MockBean
//     private UserService service;

//     @MockBean
//     private UserRepository userRepository;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     ObjectMapper objectMapper;
    
//     @Test
//     @DisplayName("Get All User")
//     public void getAllUser() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/user/adminAccess/getAllUser")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     } 

//     @Test
//     @DisplayName("Get All User With Dto")
//     public void getAllUserWithDto() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/user/adminAccess/getAllUserWithDto")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Get All User With Dto")
//     public void findAllRoleUser() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/user/userAccess/findAllRoleUser")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Get All User With Dto")
//     public void findAllRoleAdmin() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/user/adminAccess/findAllRoleAdmin")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Get All User With Dto")
//     public void findAllRoleSuperAdmin() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/user/adminAccess/findAllRoleSuperAdmin")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Get User With id")
//     public void getUserWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/user/userAccess/getUserById?UserId=1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Post User Without Data")
//     public void postUserNoData() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .post("/capstone/User/adminAccess/createNewUser")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(400, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Post User With Data")
//     public void postUserWithData() throws Exception{
//         UserDtoPost User = EASY_RANDOM.nextObject(UserDtoPost.class);

//         JSONObject payload = new JSONObject();
//         payload.put("name", User.getName());

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .post("/capstone/User/adminAccess/createNewUser")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(payload.toString())
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Delete User")
//     public void deleteUserWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .delete("/capstone/User/adminAccess/deleteUser/1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(204, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Update User")
//     public void updateUser() throws Exception{
//         UserDtoPost User = EASY_RANDOM.nextObject(UserDtoPost.class);
//         UserEntity User2 = EASY_RANDOM.nextObject(UserEntity.class);
//         User2.setUserId(1L);

//         JSONObject payload = new JSONObject();
//         payload.put("name", User.getName());

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .put("/capstone/User/adminAccess/updateUser/1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(payload.toString())
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }
// }
