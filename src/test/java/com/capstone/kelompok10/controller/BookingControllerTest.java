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

// import com.capstone.kelompok10.repository.BookingRepository;
// import com.capstone.kelompok10.service.interfaces.BookingService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class BookingControllerTest {
//     @MockBean
//     private BookingService booking;

//     @MockBean
//     private BookingRepository bookingRepository;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     ObjectMapper objectMapper;

//     @Test
//     @DisplayName("Get All Booking")
//     public void getAllBooking() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/booking/adminAccess/getAllBooking")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     } 

//     @Test
//     @DisplayName("Get Booking With id")
//     public void getBookingWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/booking/userAccess/getBookingById?bookingId=1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

// //     // @Test
// //     // @DisplayName("Post Booking Without Data")
// //     // public void postBookingNoData() throws Exception{
// //     //     RequestBuilder requestBuilder = MockMvcRequestBuilders
// //     //     .post("/miniproject/Booking")
// //     //     .contentType(MediaType.APPLICATION_JSON)
// //     //     .accept(MediaType.APPLICATION_JSON);

// //     //     MvcResult response = mockMvc        assertEquals(200, response.getResponse().getStatus());

// //     //     .perform(requestBuilder)
// //     //     .andReturn();
// //     //     assertEquals(400, response.getResponse().getStatus());
// //     // }
// }
