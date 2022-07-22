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

// import com.capstone.kelompok10.model.dto.post.RoomDtoPost;
// import com.capstone.kelompok10.model.entity.RoomEntity;
// import com.capstone.kelompok10.repository.RoomRepository;
// import com.capstone.kelompok10.service.interfaces.RoomService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class RoomControllerTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();
    
//     @MockBean
//     private RoomService service;

//     @MockBean
//     private RoomRepository roomRepository;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     ObjectMapper objectMapper;
    
//     @Test
//     @DisplayName("Get All Room")
//     public void getAllRoom() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/room/userAccess/getAllRoom")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     } 

//     @Test
//     @DisplayName("Get Room With id")
//     public void getRoomWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/room/userAccess/getRoomById?roomId=1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Post Room Without Data")
//     public void postRoomNoData() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .post("/capstone/room/adminAccess/createNewRoom")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(400, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Post Room With Data")
//     public void postRoomWithData() throws Exception{
//         RoomDtoPost room = EASY_RANDOM.nextObject(RoomDtoPost.class);

//         JSONObject payload = new JSONObject();
//         payload.put("name", room.getName());

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .post("/capstone/room/adminAccess/createNewRoom")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(payload.toString())
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Delete Room")
//     public void deleteRoomWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .delete("/capstone/room/adminAccess/deleteRoom/1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(204, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Update Room")
//     public void updateRoom() throws Exception{
//         RoomDtoPost room = EASY_RANDOM.nextObject(RoomDtoPost.class);
//         RoomEntity room2 = EASY_RANDOM.nextObject(RoomEntity.class);
//         room2.setRoomId(1L);

//         JSONObject payload = new JSONObject();
//         payload.put("name", room.getName());

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .put("/capstone/room/adminAccess/updateRoom/1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(payload.toString())
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }
// }
