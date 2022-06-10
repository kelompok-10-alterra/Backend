package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.RoleDto;
import com.capstone.kelompok10.model.entity.RoleEntity;

public interface RoleService {
    List<RoleEntity> getAllRole();
    List<RoleDto> getAllRoleDto();
    RoleEntity getRoleById(Long role_id);
    void createRole(RoleEntity role);
    void updateRole(Long role_id, RoleEntity role);
    void deleteRole(Long role_id);
}
