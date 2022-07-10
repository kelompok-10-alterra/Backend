package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.UserDtoGet;
import com.capstone.kelompok10.model.dto.post.UserDtoPost;
import com.capstone.kelompok10.model.dto.put.UserDtoPut;
import com.capstone.kelompok10.model.entity.UserEntity;

public interface UserService {
    //CRUD Method
    List<UserEntity> findAll();
    List<UserEntity> findAll(String keyword);
    List<UserDtoGet> findAllDto();
    // Page<UserEntity> findAllPagination(int offset, int pageSize);
    // Page<UserEntity> findAllPaginationSort(int offset, int pageSize, String field);
    List<UserEntity> getAllRoleUser();
    // List<UserEntity> getAllRoleUser(String keyword);
    List<UserEntity> getAllRoleAdmin();
    List<UserEntity> getAllRoleSuperAdmin();
    UserEntity getUserById(Long userId);
    UserDtoGet getUserByUsername(String username);
    void createUser(UserEntity user);
    void createUserDto(UserDtoPost userDtoPost);
    void updateUser(Long userId, UserDtoPut userDtoPut);
    void deleteUser(Long userId);

    // Auth method
    UserEntity getUser(String username);
    void addRoleToUser(String username, String roleName);
    // void addFavorite(String username, String name);
    void getMembership(Long userId, Long memberId, Long membershipId);

    // Verify method
    int userHaveMembership(Long userId);
    Boolean userExist(Long userId);
    int totalUser();
    void getPoint(Long userId);
    Boolean nativeUser(Long userId);
}

