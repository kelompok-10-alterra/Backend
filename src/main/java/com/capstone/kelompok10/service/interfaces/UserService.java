package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.capstone.kelompok10.model.dto.get.UserDtoGet;
import com.capstone.kelompok10.model.dto.post.UserDtoPost;
import com.capstone.kelompok10.model.entity.UserEntity;

public interface UserService {
    //CRUD Method
    List<UserEntity> findAll();
    List<UserDtoGet> findAllDto();
    Page<UserEntity> findAllPagination(int offset, int pageSize);
    Page<UserEntity> findAllPaginationSort(int offset, int pageSize, String field);
    UserEntity getUserById(Long userId);
    void createUser(UserEntity user);
    void createUserDto(UserDtoPost userDtoPost);
    void updateUser(Long userId, UserDtoPost UserDtoPost);
    void deleteUser(Long userId);

    // Auth method
    UserEntity getUser(String username);
    void addRoleToUser(String username, String roleName);

    // Verify method
    Boolean userHaveMembership(Long userId);
    Boolean userExist(Long userId);
    int totalUser();
}

