// package com.capstone.kelompok10.controller;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// // import org.jeasy.random.EasyRandom;
// // import org.json.JSONObject;
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

// // import com.capstone.kelompok10.model.dto.post.MemberDtoPost;
// // import com.capstone.kelompok10.model.entity.MemberEntity;
// import com.capstone.kelompok10.repository.MemberRepository;
// import com.capstone.kelompok10.service.interfaces.MemberService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class MemberControllerTest {
//     // private final EasyRandom EASY_RANDOM = new EasyRandom();
    
//     @MockBean
//     private MemberService service;

//     @MockBean
//     private MemberRepository memberRepository;

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     ObjectMapper objectMapper;
    
//     @Test
//     @DisplayName("Get All Member")
//     public void getAllMember() throws Exception{
//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/member/userAccess/getAllMember")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     } 

//     @Test
//     @DisplayName("Get Member With id")
//     public void getMemberWithId() throws Exception{

//         RequestBuilder requestBuilder = MockMvcRequestBuilders
//         .get("/capstone/member/userAccess/getMemberById?memberId=1")
//         .contentType(MediaType.APPLICATION_JSON)
//         .accept(MediaType.APPLICATION_JSON);

//         MvcResult response = mockMvc
//         .perform(requestBuilder)
//         .andReturn();
//         assertEquals(200, response.getResponse().getStatus());
//     }

//     // @Test
//     // @DisplayName("Post Member Without Data")
//     // public void postMemberNoData() throws Exception{
//     //     RequestBuilder requestBuilder = MockMvcRequestBuilders
//     //     .post("/capstone/member/adminAccess/createNewMember")
//     //     .contentType(MediaType.APPLICATION_JSON)
//     //     .accept(MediaType.APPLICATION_JSON);

//     //     MvcResult response = mockMvc
//     //     .perform(requestBuilder)
//     //     .andReturn();
//     //     assertEquals(400, response.getResponse().getStatus());
//     // }

//     // @Test
//     // @DisplayName("Post Member With Data")
//     // public void postMemberWithData() throws Exception{
//     //     MemberDtoPost member = EASY_RANDOM.nextObject(MemberDtoPost.class);

//     //     JSONObject payload = new JSONObject();
//     //     payload.put("name", member.getName());
//     //     payload.put("period", member.getPeriod());
//     //     payload.put("price", member.getPrice());

//     //     RequestBuilder requestBuilder = MockMvcRequestBuilders
//     //     .post("/capstone/member/adminAccess/createNewMember")
//     //     .contentType(MediaType.APPLICATION_JSON)
//     //     .content(payload.toString())
//     //     .accept(MediaType.APPLICATION_JSON);

//     //     MvcResult response = mockMvc
//     //     .perform(requestBuilder)
//     //     .andReturn();
//     //     assertEquals(200, response.getResponse().getStatus());
//     // }

//     // @Test
//     // @DisplayName("Delete Member")
//     // public void deleteMemberWithId() throws Exception{

//     //     RequestBuilder requestBuilder = MockMvcRequestBuilders
//     //     .delete("/capstone/member/adminAccess/deleteMember/1")
//     //     .contentType(MediaType.APPLICATION_JSON)
//     //     .accept(MediaType.APPLICATION_JSON);

//     //     MvcResult response = mockMvc
//     //     .perform(requestBuilder)
//     //     .andReturn();
//     //     assertEquals(204, response.getResponse().getStatus());
//     // }

//     // @Test
//     // @DisplayName("Update Member")
//     // public void updateMember() throws Exception{
//     //     MemberDtoPost member = EASY_RANDOM.nextObject(MemberDtoPost.class);
//     //     MemberEntity member2 = EASY_RANDOM.nextObject(MemberEntity.class);
//     //     member2.setMemberId(1L);

//     //     JSONObject payload = new JSONObject();
//     //     payload.put("name", member.getName());
//     //     payload.put("period", member.getPeriod());
//     //     payload.put("price", member.getPrice());

//     //     RequestBuilder requestBuilder = MockMvcRequestBuilders
//     //     .put("/capstone/member/adminAccess/updateMember/1")
//     //     .contentType(MediaType.APPLICATION_JSON)
//     //     .content(payload.toString())
//     //     .accept(MediaType.APPLICATION_JSON);

//     //     MvcResult response = mockMvc
//     //     .perform(requestBuilder)
//     //     .andReturn();
//     //     assertEquals(200, response.getResponse().getStatus());
//     // }
// }
