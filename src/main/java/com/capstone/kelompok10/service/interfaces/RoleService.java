package com.capstone.kelompok10.service.interfaces;

import java.util.List;

import com.capstone.kelompok10.model.dto.get.RoleDtoGet;
import com.capstone.kelompok10.model.dto.post.RoleDtoPost;
import com.capstone.kelompok10.model.entity.RoleEntity;

public interface RoleService {
    List<RoleEntity> getAllRole();
    List<RoleDtoGet> getAllRoleDto();
    RoleEntity getRoleById(Long role_id);
    void createRole(RoleEntity role);
    void createRoleDto(RoleDtoPost roleDtoPost);
    void updateRole(Long role_id, RoleEntity role);
    void deleteRole(Long role_id);
}
