// package com.capstone.kelompok10.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.jeasy.random.EasyRandom;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import com.capstone.kelompok10.model.dto.get.MembershipDtoGet;
// import com.capstone.kelompok10.model.entity.MembershipEntity;
// import com.capstone.kelompok10.repository.MembershipRepository;
// import com.capstone.kelompok10.service.implementation.MembershipServiceImpl;
// import com.capstone.kelompok10.service.interfaces.MemberService;
// import com.capstone.kelompok10.service.interfaces.UserService;

// @ExtendWith(MockitoExtension.class)
// @ExtendWith(SpringExtension.class)
// public class MembershipServiceTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();

//     @InjectMocks
//     private MembershipServiceImpl service;

//     @Mock
//     private MembershipRepository repository;

//     @Mock
//     private UserService userService;

//     @Mock
//     private MemberService memberService;
    
//     @Test
//     void findAll(){
//         List<MembershipEntity> membership = EASY_RANDOM.objects(MembershipEntity.class, 2)
//         .collect(Collectors.toList());

//         when(repository.findAll()).thenReturn(membership);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getMembershipById(){
//         MembershipEntity membership = EASY_RANDOM.nextObject(MembershipEntity.class);

//         when(repository.findById(membership.getMembershipId())).thenReturn(Optional.of(membership));
//         service.getMembershipById(membership.getMembershipId());
//         verify(repository, times(2)).findById(membership.getMembershipId());
//     }

//     // @Test
//     // public void updateMembership() {
//     //     MembershipEntity membership = EASY_RANDOM.nextObject(MembershipEntity.class);
//     //     MembershipDtoPost newMembership = new MembershipDtoPost();
//     //     UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);
//     //     newMembership.setUserId(user.getUserId());

//     //     when(repository.findById(membership.getMembershipId())).thenReturn(Optional.of(membership));
//     //     assertEquals("user or member did'ny exist",         service.updateMembership(membership.getMembershipId(), newMembership));
//     // }

//     // @Test
//     // public void createMembershipDto(){
//     //     MembershipEntity membershipEntity = new MembershipEntity();
//     //     MembershipDtoPost membershipDtoPost = EASY_RANDOM.nextObject(MembershipDtoPost.class);
//     //     membershipEntity.setUser(membershipDtoPost.getUserId());
//     //     membershipEntity.setContact(membershipDtoPost.getContact());
//     //     membershipEntity.setAddress(membershipDtoPost.getAddress());
//     //     membershipEntity.setImageUrl(membershipDtoPost.getImageUrl());

//     //     service.createMembershipDto(MembershipDtoPost);
//     //     verify(repository).save(MembershipEntity);
//     // }

//     @Test
//     public void deleteMembership(){
//         MembershipEntity membershipEntity = EASY_RANDOM.nextObject(MembershipEntity.class);

//         when(repository.findById(membershipEntity.getMembershipId())).thenReturn(Optional.of(membershipEntity));
//         service.deleteMembership(membershipEntity.getMembershipId());
//         verify(repository, times(1)).deleteById(membershipEntity.getMembershipId());
//         verify(repository, times(1)).findById(membershipEntity.getMembershipId());
//     }

//     @Test
//     public void totalMembership(){
//         List<MembershipEntity> membership = EASY_RANDOM.objects(MembershipEntity.class, 2)
//         .collect(Collectors.toList());

//         when(repository.findAll()).thenReturn(membership);
//         service.totalMembership();
//         assertEquals(2, service.totalMembership());
//     }

//     @Test
//     public void membershipNotExpired(){
//         MembershipEntity membership = EASY_RANDOM.nextObject(MembershipEntity.class);
//         membership.setCreatedAt(LocalDateTime.now());
//         membership.setExpiredAt(LocalDateTime.now().plusMonths(2));

//         when(repository.findById(membership.getMembershipId())).thenReturn(Optional.of(membership));
//         service.membershipExpired(membership.getMembershipId());
//         assertEquals(false, service.membershipExpired(membership.getMembershipId()));
//     }

//     @Test
//     public void membershipExpired(){
//         MembershipEntity membership = EASY_RANDOM.nextObject(MembershipEntity.class);
//         membership.setCreatedAt(LocalDateTime.now());
//         membership.setExpiredAt(LocalDateTime.now().minusMinutes(2));

//         when(repository.findById(membership.getMembershipId())).thenReturn(Optional.of(membership));
//         service.membershipExpired(membership.getMembershipId());
//         assertEquals(true, service.membershipExpired(membership.getMembershipId()));
//     }

//     @Test
//     public void findAllDto(){
//         List<MembershipEntity> membership = EASY_RANDOM.objects(MembershipEntity.class, 2)
//         .collect(Collectors.toList());
//         List<MembershipDtoGet> membershipDtos = new ArrayList<>();

//         membership.forEach(isi ->{
//             MembershipDtoGet dto = new MembershipDtoGet();
//             dto.setMembershipId(isi.getMembershipId());
//             dto.setStatus(isi.getStatus());
//             dto.setCreatedAt(isi.getCreatedAt().toString());
//             dto.setUpdatedAt(isi.getUpdated_at().toString());
//             dto.setUserId(isi.getUser().getUserId());
//             dto.setUsername(isi.getUser().getUsername());
//             dto.setEmail(isi.getUser().getEmail());
//             dto.setName(isi.getUser().getName());
//             if(isi.getMember() == null){
//                 dto.setMemberId(null);
//                 dto.setMemberName("No Membership");
//                 dto.setMemberPeriod("No Membership");
//             }else{
//                 dto.setMemberId(isi.getMember().getMemberId());
//                 dto.setMemberName(isi.getMember().getName());
//                 dto.setMemberPeriod(isi.getMember().getPeriod());
//             }
//             dto.setContact(isi.getUser().getPhone());
//             dto.setAddress(isi.getUser().getAddress());
//             dto.setExpiredAt(isi.getExpiredAt());

//             membershipDtos.add(dto);
//         });

//         when(repository.findAll()).thenReturn(membership);
//         service.findAllDto();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getMembershipByIdDto(){
//         MembershipEntity isi = EASY_RANDOM.nextObject(MembershipEntity.class);
//         MembershipDtoGet dto = new MembershipDtoGet();
//         dto.setMembershipId(isi.getMembershipId());
//             dto.setStatus(isi.getStatus());
//             dto.setCreatedAt(isi.getCreatedAt().toString());
//             dto.setUpdatedAt(isi.getUpdated_at().toString());
//             dto.setUserId(isi.getUser().getUserId());
//             dto.setUsername(isi.getUser().getUsername());
//             dto.setEmail(isi.getUser().getEmail());
//             dto.setName(isi.getUser().getName());
//             if(isi.getMember() == null){
//                 dto.setMemberId(null);
//                 dto.setMemberName("No Membership");
//                 dto.setMemberPeriod("No Membership");
//             }else{
//                 dto.setMemberId(isi.getMember().getMemberId());
//                 dto.setMemberName(isi.getMember().getName());
//                 dto.setMemberPeriod(isi.getMember().getPeriod());
//             }
//             dto.setContact(isi.getUser().getPhone());
//             dto.setExpiredAt(isi.getExpiredAt());

//         when(repository.findById(isi.getMembershipId())).thenReturn(Optional.of(isi));
//         service.getMembershipByIdDto(isi.getMembershipId());
//         verify(repository, times(2)).findById(isi.getMembershipId());
//     }
// }
