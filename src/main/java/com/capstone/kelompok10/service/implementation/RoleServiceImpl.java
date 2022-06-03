package com.capstone.kelompok10.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capstone.kelompok10.model.dto.RoleDto;
import com.capstone.kelompok10.model.entity.RoleEntity;
import com.capstone.kelompok10.repository.RoleRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.interfaces.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

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
    public List<RoleEntity> getAllRole() {
        List<RoleEntity> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public List<RoleDto> getAllRoleDto() {
        List<RoleEntity> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        
        roles.forEach(isi ->{
            RoleDto dto = new RoleDto();
            dto.setRole_id(isi.getRole_id());
            dto.setName(isi.getName());
            dto.setDescription(isi.getDescription());
            
            roleDtos.add(dto);
        });
        return roleDtos;
    }

    @Override
    public RoleEntity getRoleById(Long role_id) {
        return roleRepository.findById(role_id).get();
    }

    @Override
    public void createRole(RoleEntity role) {
        roleRepository.save(role);
    }

    @Override
    public void updateRole(Long role_id, RoleEntity role) {
        RoleEntity role2 = roleRepository.findById(role_id).get();
        System.out.println(role2.toString());
        role2.setName(role.getName());
        role2.setDescription(role.getDescription());
        roleRepository.save(role2);
    }

    @Override
    public void deleteRole(Long role_id) {
        roleRepository.deleteById(role_id);  
    }
}

