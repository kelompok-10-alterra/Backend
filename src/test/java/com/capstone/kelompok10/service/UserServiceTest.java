// package com.capstone.kelompok10.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

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
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import com.capstone.kelompok10.model.dto.get.UserDtoGet;
// import com.capstone.kelompok10.model.dto.put.UserDtoPut;
// import com.capstone.kelompok10.model.entity.UserEntity;
// import com.capstone.kelompok10.repository.UserRepository;
// import com.capstone.kelompok10.service.implementation.UserServiceImpl;

// @ExtendWith(MockitoExtension.class)
// @ExtendWith(SpringExtension.class)
// public class UserServiceTest {
//     private final EasyRandom EASY_RANDOM = new EasyRandom();

//     @InjectMocks
//     private UserServiceImpl service;

//     @Mock
//     private UserRepository repository;

//     @Mock
//     private PasswordEncoder crypt;

//     @Test
//     public void loadUserByUsername(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);

//         when(repository.findByUsername(user.getUsername())).thenReturn(user);
//         service.loadUserByUsername(user.getUsername());
//         verify(repository, times(1)).findByUsername(user.getUsername());
//     }

//     @Test
//     public void findAll(){
//         List<UserEntity> users = EASY_RANDOM.objects(UserEntity.class, 2).collect(Collectors.toList());

//         when(repository.findAll()).thenReturn(users);
//         service.findAll();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     public void getAllRoleUser(){
//         List<UserEntity> users = EASY_RANDOM.objects(UserEntity.class, 2).collect(Collectors.toList());
//         users.get(1).setRoleName("ROLE_USER");
//         String rolename = users.get(1).getRoleName();

//         when(repository.findByRoleName(rolename)).thenReturn(users);
//         service.getAllRoleUser();
//         verify(repository, times(1)).findByRoleName(rolename);
//     }

//     @Test
//     public void getAllRoleAdmin(){
//         List<UserEntity> users = EASY_RANDOM.objects(UserEntity.class, 2).collect(Collectors.toList());
//         users.get(1).setRoleName("ROLE_ADMIN");
//         String rolename = users.get(1).getRoleName();

//         when(repository.findByRoleName(rolename)).thenReturn(users);
//         service.getAllRoleAdmin();
//         verify(repository, times(1)).findByRoleName(rolename);
//     }

//     @Test
//     public void getAllRoleSuperAdmin(){
//         List<UserEntity> users = EASY_RANDOM.objects(UserEntity.class, 2).collect(Collectors.toList());
//         users.get(1).setRoleName("ROLE_SUPER_ADMIN");
//         String rolename = users.get(1).getRoleName();

//         when(repository.findByRoleName(rolename)).thenReturn(users);
//         service.getAllRoleSuperAdmin();
//         verify(repository, times(1)).findByRoleName(rolename);
//     }

//     @Test
//     public void findAllDto(){
//         List<UserEntity> users = EASY_RANDOM.objects(UserEntity.class, 2).collect(Collectors.toList());
//         List<UserDtoGet> userDtos = new ArrayList<>();
        
//         users.forEach(isi ->{
//             UserDtoGet dto = new UserDtoGet();
//             dto.setUserId(isi.getUserId());
//             dto.setName(isi.getName());
//             dto.setUsername(isi.getUsername());
//             dto.setEmail(isi.getEmail());
//             dto.setPhone(isi.getPhone());
//             dto.setAddress(isi.getAddress());
//             dto.setMembership(isi.getMembership());
//             dto.setImageUrl(isi.getImageUrl());

//             userDtos.add(dto);
//         });

//         when(repository.findAll()).thenReturn(users);
//         service.findAllDto();
//         verify(repository, times(1)).findAll();
//     }

//     @Test
//     void getUserById(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);

//         when(repository.findById(user.getUserId())).thenReturn(Optional.of(user));
//         service.getUserById(user.getUserId());
//         verify(repository, times(2)).findById(user.getUserId());
//     }

//     @Test
//     public void createUser(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);

//         service.createUser(user);
//         verify(repository, times(1)).save(user);
//     }

//     @Test
//     public void updateUser(){
//         UserEntity user2 = EASY_RANDOM.nextObject(UserEntity.class);
//         UserDtoPut userDtoPut = EASY_RANDOM.nextObject(UserDtoPut.class);
//         user2.setName(userDtoPut.getName());
//         user2.setPhone(userDtoPut.getPhone());
//         user2.setAddress(userDtoPut.getAddress());
//         user2.setImageUrl(userDtoPut.getImageUrl());

//         when(repository.findById(user2.getUserId())).thenReturn(Optional.of(user2));
//         service.updateUser(user2.getUserId(), userDtoPut);
//         verify(repository, times(2)).findById(user2.getUserId());
//         verify(repository, times(1)).save(user2);
//     }

//     @Test
//     public void deleteUser(){
//         UserEntity userEntity = EASY_RANDOM.nextObject(UserEntity.class);

//         when(repository.findById(userEntity.getUserId())).thenReturn(Optional.of(userEntity));
//         service.deleteUser(userEntity.getUserId());
//         verify(repository, times(1)).deleteById(userEntity.getUserId());
//         verify(repository, times(1)).findById(userEntity.getUserId());
//     }

//     @Test
//     public void getUser(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);

//         service.getUser(user.getUsername());
//         verify(repository, times(1)).findByUsername(user.getUsername());
//     }

//     @Test
//     public void userHaveMembershipWithValueNull(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);
//         user.setMembership(null);
        
//         when(repository.findById(user.getUserId())).thenReturn(Optional.of(user));
//         service.userHaveMembership(user.getUserId());
//         verify(repository, times(1)).findById(user.getUserId());
//         assertEquals(1 ,service.userHaveMembership(user.getUserId()));
//     }

//     @Test
//     public void userHaveMembershipWithValueSilver(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);
//         user.setMembership("Silver");
//         user.setStatus(true);
        
//         when(repository.findById(user.getUserId())).thenReturn(Optional.of(user));
//         service.userHaveMembership(user.getUserId());
//         verify(repository, times(1)).findById(user.getUserId());
//         assertEquals(2 ,service.userHaveMembership(user.getUserId()));
//     }

//     @Test
//     public void userHaveMembershipWithValueGold(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);
//         user.setMembership("Gold");
//         user.setStatus(true);
        
//         when(repository.findById(user.getUserId())).thenReturn(Optional.of(user));
//         service.userHaveMembership(user.getUserId());
//         verify(repository, times(1)).findById(user.getUserId());
//         assertEquals(3 ,service.userHaveMembership(user.getUserId()));
//     }

//     @Test
//     public void userHaveMembershipWithValuePlatinum(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);
//         user.setMembership("Platinum");
//         user.setStatus(true);
        
//         when(repository.findById(user.getUserId())).thenReturn(Optional.of(user));
//         service.userHaveMembership(user.getUserId());
//         verify(repository, times(1)).findById(user.getUserId());
//         assertEquals(4 ,service.userHaveMembership(user.getUserId()));
//     }

//     @Test
//     public void userHaveMembershipWithValueSilverAndFalseStatus(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);
//         user.setMembership("Silver");
//         user.setStatus(false);
        
//         when(repository.findById(user.getUserId())).thenReturn(Optional.of(user));
//         service.userHaveMembership(user.getUserId());
//         verify(repository, times(1)).findById(user.getUserId());
//         assertEquals(99 ,service.userHaveMembership(user.getUserId()));
//     }

//     @Test
//     public void userExist(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);

//         when(repository.findById(user.getUserId())).thenReturn(Optional.of(user));
//         service.userExist(user.getUserId());
//         verify(repository, times(1)).findById(user.getUserId());
//         assertEquals(true, service.userExist(user.getUserId()));
//     }

//     @Test
//     public void totalUser(){
//         List<UserEntity> user = EASY_RANDOM.objects(UserEntity.class, 2).collect(Collectors.toList());

//         when(repository.findAll()).thenReturn(user);
//         service.totalUser();
//         assertEquals(2, user.size());
//     }

//     @Test
//     public void findAllWithKeyword(){
//         List<UserEntity> user = EASY_RANDOM.objects(UserEntity.class, 2).collect(Collectors.toList());
//         String keyword = user.get(1).getUsername();

//         when(repository.findAll(keyword)).thenReturn(user);
//         service.findAll(keyword);
//         verify(repository, times(1)).findAll(keyword);
//     }

//     @Test
//     public void getPoint(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);
//         Long point = user.getPoint();

//         when(repository.findById(user.getUserId())).thenReturn(Optional.of(user));
//         service.getPoint(user.getUserId());
//         assertEquals(point + 100L, user.getPoint());
//     }

//     // @Test
//     // public void getUserByUsername(){
//     //     UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);
//     //     String username = user.getUsername();
//     //     UserDtoGet dto = new UserDtoGet();

//     //     dto.setUserId(user.getUserId());
//     //     dto.setUsername(user.getUsername());
//     //     dto.setName(user.getName());
//     //     dto.setEmail(user.getEmail());
//     //     dto.setPhone(user.getPhone());
//     //     dto.setAddress(user.getAddress());
//     //     dto.setImageUrl(user.getImageUrl());
//     //     dto.setMembership(user.getMembership());
//     //     dto.setPoint(user.getPoint());

//     //     // when(repository.findById(user.getUserId())).thenReturn(Optional.of(user));
//     //     service.getUserByUsername(username);
//     //     verify(repository, times(1)).findById(user.getUserId());
//     // }

//     @Test
//     public void NonNativeUser(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);
//         user.setUserId(5L);

//         service.nativeUser(user.getUserId());
//         assertEquals(false, service.nativeUser(user.getUserId()));
//     }

//     @Test
//     public void nativeUser(){
//         UserEntity user = EASY_RANDOM.nextObject(UserEntity.class);
//         user.setUserId(3L);

//         service.nativeUser(user.getUserId());
//         assertEquals(true, service.nativeUser(user.getUserId()));
//     }
// }
