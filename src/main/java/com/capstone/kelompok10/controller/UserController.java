package com.capstone.kelompok10.controller;

import com.capstone.kelompok10.model.dto.get.UserDtoGet;
import com.capstone.kelompok10.model.dto.post.UserDtoPost;
import com.capstone.kelompok10.model.dto.put.UserDtoPut;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.model.payload.RoleToUser;
import com.capstone.kelompok10.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/capstone/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/adminAccess/getAllUser")
    public ResponseEntity<List<UserEntity>> findAll(@RequestParam(required = false) String keyword){
        if(keyword == null){
            List<UserEntity> users = userService.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            List<UserEntity> users = userService.findAll(keyword);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }  
    }

    @GetMapping("/adminAccess/getAllUser/{offset}/{pageSize}")
    public ResponseEntity<Page<UserEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
        Page<UserEntity> users = userService.findAllPagination(offset, pageSize);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/adminAccess/getAllUser/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<UserEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        Page<UserEntity> users = userService.findAllPaginationSort(offset, pageSize, field);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/userAccess/findAllRoleUser")
    public ResponseEntity<List<UserEntity>> findAllRoleUser(@RequestParam(required = false) String keyword){
        List<UserEntity> users = userService.getAllRoleUser();
        return new ResponseEntity<>(users, HttpStatus.OK); 
    }

    @GetMapping("/adminAccess/findAllRoleAdmin")
    public ResponseEntity<List<UserEntity>> findAllRoleAdmin(){
        List<UserEntity> users = userService.getAllRoleAdmin();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/adminAccess/findAllRoleSuperAdmin")
    public ResponseEntity<List<UserEntity>> findAllRoleSuperAdmin(){
        List<UserEntity> users = userService.getAllRoleSuperAdmin();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/adminAccess/getAllUserWithDto")
    public ResponseEntity<List<UserDtoGet>> findAllDto(){
        List<UserDtoGet> userDtos = userService.findAllDto();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/userAccess/getUserById")
    public ResponseEntity<UserEntity> getUserById(@RequestParam(required = false) Long userId, @RequestParam(required = false) String name){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("/userAccess/updateUser/{userId}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDtoPut userDtoPut){
        userService.updateUser(userId, userDtoPut);
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping("/adminAccess/deleteUser/{userId}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/adminAccess/createUser")
    public ResponseEntity<UserDtoPost> createUser(@RequestBody UserDtoPost userDtoPost){
        userService.createUserDto(userDtoPost);
        return new ResponseEntity<>(userDtoPost, HttpStatus.OK);
    }

    @PostMapping("/managerOnly/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUser form){
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    // @PostMapping("/userAccess/addFavorite")
    // public ResponseEntity<?> AddFavorite(@RequestBody Favorite fav){
    //     userService.addFavorite(fav.getUsername(), fav.getName());
    //     return ResponseEntity.ok().build();
    // }

    @GetMapping("/adminAccess/countTotalUser")
    public int totalUser(){
        return userService.totalUser();
    }

    @GetMapping("/userAccess/getUserByUsername")
    public ResponseEntity<UserDtoGet> getUserUsingUsername(String username){
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }
}
