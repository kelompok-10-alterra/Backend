package com.capstone.kelompok10.controller;

import com.capstone.kelompok10.model.dto.get.UserDtoGet;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.model.payload.RoleToUser;
import com.capstone.kelompok10.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUser(@RequestParam(required = false) String name){
        return new ResponseEntity<>(userService.getAllUser(name), HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<UserDtoGet>> getAllUserDto(){
        List<UserDtoGet> userDtos = userService.getAllUserDto();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUserById(@RequestParam(required = false) Long user_id, @RequestParam(required = false) String name){
        return new ResponseEntity<>(userService.getUserById(user_id), HttpStatus.OK);
    }

    @PutMapping("/user/{user_id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("user_id") Long user_id, @RequestBody UserEntity user){
        userService.updateUser(user_id, user);
        return new ResponseEntity<>(userService.getUserById(user_id), HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable("user_id") Long user_id){
        userService.deleteUser(user_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addRole")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUser form){
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}
