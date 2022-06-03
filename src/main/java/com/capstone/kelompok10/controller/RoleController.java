package com.capstone.kelompok10.controller;

import java.util.List;

import com.capstone.kelompok10.model.dto.RoleDto;
import com.capstone.kelompok10.model.entity.RoleEntity;
import com.capstone.kelompok10.service.interfaces.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capstone/role")
public class RoleController {
    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getAllRole(){
        List<RoleEntity> roles = roleService.getAllRole();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<RoleDto>> getAllRoleDto(){
        List<RoleDto> roleDtos = roleService.getAllRoleDto();
        return new ResponseEntity<>(roleDtos, HttpStatus.OK);
    }

    @GetMapping("/{role_id}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable("role_id") Long role_id){
        return new ResponseEntity<>(roleService.getRoleById(role_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleEntity> createRole(@RequestBody RoleEntity role){
        roleService.createRole(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PutMapping("/{role_id}")
    public ResponseEntity<RoleEntity> updateRole(@PathVariable("role_id") Long role_id, @RequestBody RoleEntity role){
        roleService.updateRole(role_id, role);
        return new ResponseEntity<>(roleService.getRoleById(role_id), HttpStatus.OK);
    }

    @DeleteMapping("/{role_id}")
    public ResponseEntity<RoleEntity> deleteRole(@PathVariable("role_id") Long role_id){
        roleService.deleteRole(role_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
