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

// import com.capstone.kelompok10.model.dto.post.GraphDtoPost;
// import com.capstone.kelompok10.model.entity.GraphEntity;
// import com.capstone.kelompok10.repository.GraphRepository;
// import com.capstone.kelompok10.service.interfaces.GraphService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class GraphControllerTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();
    
//     @MockBean
//     private GraphService service;

//     @MockBean
//     private GraphRepository graphRepository;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     ObjectMapper objectMapper;
    
//     @Test
//     @DisplayName("Get All Graph")
//     public void getAllGraph() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/graph/adminAccess/getAllGraph")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     } 

//     @Test
//     @DisplayName("Get Graph With id")
//     public void getGraphWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/graph/adminAccess/getGraphById?graphId=1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Post Graph Without Data")
//     public void postGraphNoData() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .post("/capstone/graph/adminAccess/createNewGraph")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(400, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Post Graph With Data")
//     public void postGraphWithData() throws Exception{
//         GraphDtoPost graph = EASY_RANDOM.nextObject(GraphDtoPost.class);

//         JSONObject payload = new JSONObject();
//         payload.put("month", graph.getMonth());
//         payload.put("year", graph.getYear());
//         payload.put("totalUser", graph.getTotalUser());
//         payload.put("totalMember", graph.getTotalMember());

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .post("/capstone/graph/adminAccess/createNewGraph")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(payload.toString())
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Delete Graph")
//     public void deleteGraphWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .delete("/capstone/graph/adminAccess/deleteGraph/1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(204, response.getResponse().getStatus());
//     }

//     @Test
//     @DisplayName("Update Graph")
//     public void updateGraph() throws Exception{
//         GraphDtoPost graph = EASY_RANDOM.nextObject(GraphDtoPost.class);
//         GraphEntity graph2 = EASY_RANDOM.nextObject(GraphEntity.class);
//         graph2.setGraphId(1L);

//         JSONObject payload = new JSONObject();
//         payload.put("month", graph.getMonth());
//         payload.put("year", graph.getYear());
//         payload.put("totalUser", graph.getTotalUser());
//         payload.put("totalMember", graph.getTotalMember());

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .put("/capstone/graph/adminAccess/updateGraph/1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .content(payload.toString())
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }
// }
