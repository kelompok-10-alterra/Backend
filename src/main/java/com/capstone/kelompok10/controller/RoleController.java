package com.capstone.kelompok10.controller;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.RoleDtoGet;
import com.capstone.kelompok10.model.dto.post.RoleDtoPost;
import com.capstone.kelompok10.model.entity.RoleEntity;
import com.capstone.kelompok10.service.interfaces.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/capstone/role")
public class RoleController {
    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping("/managerOnly/getAllRole")
    public ResponseEntity<List<RoleEntity>> findAll(){
        List<RoleEntity> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    // @GetMapping("/managerOnly/{offset}/{pageSize}")
    // public ResponseEntity<Page<RoleEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
    //     Page<RoleEntity> roles = roleService.findAllPagination(offset, pageSize);
    //     return new ResponseEntity<>(roles, HttpStatus.OK);
    // }

    // @GetMapping("/managerOnly/{offset}/{pageSize}/{field}")
    // public ResponseEntity<Page<RoleEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
    //     Page<RoleEntity> roles = roleService.findAllPaginationSort(offset, pageSize, field);
    //     return new ResponseEntity<>(roles, HttpStatus.OK);
    // }

    @GetMapping("/managerOnly/getAllRoleWithDto")
    public ResponseEntity<List<RoleDtoGet>> findAllDto(){
        List<RoleDtoGet> roleDtos = roleService.findAllDto();
        return new ResponseEntity<>(roleDtos, HttpStatus.OK);
    }

    @GetMapping("/managerOnly/getRoleById")
    public ResponseEntity<RoleEntity> getRoleById(@RequestParam("roleId") Long roleId){
        return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);
    }

    @PostMapping("/managerOnly/createNewRole")
    public ResponseEntity<RoleDtoPost> createRoleDto(@RequestBody RoleDtoPost roleDtoPost){
        roleService.createRoleDto(roleDtoPost);
        return new ResponseEntity<>(roleDtoPost, HttpStatus.OK);
    }

    // @PutMapping("/managerOnly/updateRole/{roleId}")
    // public ResponseEntity<RoleEntity> updateRole(@PathVariable("roleId") Long roleId, @RequestBody RoleDtoPost roleDtoPost){
    //     roleService.updateRole(roleId, roleDtoPost);
    //     return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);
    // }

    // @DeleteMapping("/managerOnly/deleteRole/{roleId}")
    // public ResponseEntity<RoleEntity> deleteRole(@PathVariable("roleId") Long roleId){
    //     roleService.deleteRole(roleId);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
}
