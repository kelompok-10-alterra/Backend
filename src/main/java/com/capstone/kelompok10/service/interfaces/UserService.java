package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.UserDtoGet;
import com.capstone.kelompok10.model.dto.post.UserDtoPost;
import com.capstone.kelompok10.model.entity.UserEntity;

public interface UserService {
    //CRUD Method
    List<UserEntity> getAllUser(String name);
    List<UserDtoGet> getAllUserDto();
    UserEntity getUserById(Long user_id);
    void createUser(UserEntity user);
    void updateUser(Long user_id, UserDtoPost UserDtoPost);
    void deleteUser(Long user_id);

    // Auth method
    UserEntity getUser(String username);
    void addRoleToUser(String username, String roleName);
}

