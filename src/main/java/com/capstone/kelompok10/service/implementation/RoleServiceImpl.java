package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.get.RoleDtoGet;
import com.capstone.kelompok10.model.dto.post.RoleDtoPost;
import com.capstone.kelompok10.model.entity.RoleEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.RoleRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.interfaces.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleEntity> findAll() {
        log.info("Get all Role without DTO");
        List<RoleEntity> role = new ArrayList<>();
        roleRepository.findAll().forEach(role::add);
        return role;
    }
    
    @Override
    public Page<RoleEntity> findAllPagination(int offset, int pageSize) {
        log.info("Get all Role with Pagination");
        Page<RoleEntity> role = roleRepository.findAll(PageRequest.of(offset, pageSize));
        return role;
    }

    @Override
    public Page<RoleEntity> findAllPaginationSort(int offset, int pageSize, String field){
        log.info("Get all Role with Pagination and Sorting");
        Page<RoleEntity> role = roleRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return role;
    }

    @Override
    public List<RoleDtoGet> findAllDto() {
        log.info("Get all Role with DTO");
        List<RoleEntity> roles = roleRepository.findAll();
        List<RoleDtoGet> roleDtos = new ArrayList<>();
        
        roles.forEach(isi ->{
            RoleDtoGet dto = new RoleDtoGet();
            dto.setRoleId(isi.getRoleId());
            dto.setName(isi.getName());
            
            roleDtos.add(dto);
        });
        return roleDtos;
    }

    @Override
    public RoleEntity getRoleById(Long roleId) {
        if(roleRepository.findById(roleId) != null){
            log.info("Role with id {} found", roleId);
            return roleRepository.findById(roleId).get();
        }else{
            log.info("Role with id {} not Found");
            throw new IllegalStateException("Role not Found");
        }
    }

    @Override
    public void createRole(RoleEntity role) {
        roleRepository.save(role);
        log.info("Role created");
    }

    @Override
    public void updateRole(Long roleId, RoleDtoPost roleDtoPost) {
        if(roleRepository.findById(roleId) != null){
            RoleEntity role2 = roleRepository.findById(roleId).get();
            role2.setName(roleDtoPost.getName());
            roleRepository.save(role2);
            log.info("Role updated");
        }else{
            log.info("Role with id {} not Found");
            throw new IllegalStateException("Role not Found");
        }
    }

    @Override
    public void deleteRole(Long roleId) {
        if(roleRepository.findById(roleId) != null){
            log.info("Role with id {} successfully deleted", roleId);
            roleRepository.deleteById(roleId);  
        }else{
            log.info("Role with id {} not Found");
            throw new IllegalStateException("Role not Found");
        }
    }

	@Override
	public void createRoleDto(RoleDtoPost roleDtoPost) {
        if(roleRepository.findByName(roleDtoPost.getName()) != null){
            log.info("Role with name {} already exist", roleDtoPost.getName());
            throw new IllegalStateException("Role already exist");
        }else{
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(roleDtoPost.getName());

            roleRepository.save(roleEntity);
            log.info("Role created");
        }
	}

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Menambahkan Role {} ke user {}", roleName, username);
        UserEntity user = userRepository.findByUsername(username);
        RoleEntity role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        
    }
}

