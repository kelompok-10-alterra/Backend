package com.capstone.kelompok10.controller;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.RoleDtoGet;
import com.capstone.kelompok10.model.dto.post.RoleDtoPost;
import com.capstone.kelompok10.model.entity.RoleEntity;
import com.capstone.kelompok10.service.interfaces.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/capstone/role")
public class RoleController {
    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleEntity>> findAll(){
        List<RoleEntity> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{offset}/{pageSize}")
    public ResponseEntity<Page<RoleEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize){
        Page<RoleEntity> roles = roleService.findAllPagination(offset, pageSize);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<RoleEntity>> findAllPaginationSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        Page<RoleEntity> roles = roleService.findAllPaginationSort(offset, pageSize, field);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<RoleDtoGet>> findAllDto(){
        List<RoleDtoGet> roleDtos = roleService.findAllDto();
        return new ResponseEntity<>(roleDtos, HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable("roleId") Long roleId){
        return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleEntity> createRole(@RequestBody RoleEntity role){
        roleService.createRole(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping("/dto")
    public ResponseEntity<RoleDtoPost> createRoleDto(@RequestBody RoleDtoPost roleDtoPost){
        roleService.createRoleDto(roleDtoPost);
        return new ResponseEntity<>(roleDtoPost, HttpStatus.OK);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleEntity> updateRole(@PathVariable("roleId") Long roleId, @RequestBody RoleDtoPost roleDtoPost){
        roleService.updateRole(roleId, roleDtoPost);
        return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<RoleEntity> deleteRole(@PathVariable("roleId") Long roleId){
        roleService.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
