// package com.capstone.kelompok10.controller;

// import static org.junit.jupiter.api.Assertions.assertEquals;

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

// import com.capstone.kelompok10.repository.HistoryRepository;
// import com.capstone.kelompok10.service.interfaces.HistoryService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class HistoryControllerTest {
//     @MockBean
//     private HistoryService service;

//     @MockBean
//     private HistoryRepository historyRepository;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     ObjectMapper objectMapper;

//     @Test
//     @DisplayName("Get All History")
//     public void getAllHistory() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/history/adminAccess/getAllHistory")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     } 

//     @Test
//     @DisplayName("Get History With id")
//     public void getHistoryWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/history/userAccess/getHistoryById?historyId=1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }
// }
