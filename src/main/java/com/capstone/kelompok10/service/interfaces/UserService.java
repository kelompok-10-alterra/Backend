package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.UserDto;
import com.capstone.kelompok10.model.entity.UserEntity;

public interface UserService {
    List<UserEntity> getAllUser();
    List<UserDto> getAllUserDto();
    UserEntity getUserById(Long user_id);
    void addRoleToUser(String username, String roleName);
    void createUser(UserEntity user);
    void updateUser(Long user_id, UserEntity User);
    void deleteUser(Long user_id);
    UserEntity getUser(String username);
}

