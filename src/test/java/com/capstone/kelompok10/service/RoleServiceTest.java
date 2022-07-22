package com.capstone.kelompok10.service;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capstone.kelompok10.model.dto.get.RoleDtoGet;
import com.capstone.kelompok10.model.dto.post.RoleDtoPost;
import com.capstone.kelompok10.model.entity.RoleEntity;
import com.capstone.kelompok10.repository.RoleRepository;
import com.capstone.kelompok10.service.implementation.RoleServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RoleServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();

    @InjectMocks
    private RoleServiceImpl service;

    @Mock
    private RoleRepository repository;

    @Test
    void findAll(){
        List<RoleEntity> role = EASY_RANDOM.objects(RoleEntity.class, 2)
        .collect(Collectors.toList());

        when(repository.findAll()).thenReturn(role);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void findAllDto(){
        List<RoleEntity> role = EASY_RANDOM.objects(RoleEntity.class, 2)
        .collect(Collectors.toList());
        List<RoleDtoGet> dtos = new ArrayList<>();
        role.forEach(isi ->{
            RoleDtoGet dto = new RoleDtoGet();
            dto.setRoleId(isi.getRoleId());
            dto.setName(isi.getName());

            dtos.add(dto);
        });

        when(repository.findAll()).thenReturn(role);
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void getRoleById(){
        RoleEntity role = EASY_RANDOM.nextObject(RoleEntity.class);

        when(repository.findById(role.getRoleId())).thenReturn(Optional.of(role));
        service.getRoleById(role.getRoleId());
        verify(repository, times(2)).findById(role.getRoleId());
    }

    @Test
    public void updateRole() {
        RoleEntity role = EASY_RANDOM.nextObject(RoleEntity.class);
        RoleDtoPost newRole = new RoleDtoPost();
        newRole.setName("Baru");

        when(repository.findById(role.getRoleId())).thenReturn(Optional.of(role));
        service.updateRole(role.getRoleId(), newRole);
        verify(repository).save(role);
        verify(repository, times(2)).findById(role.getRoleId());
    }

    @Test
    public void createRoleDto(){
        RoleEntity roleEntity = new RoleEntity();
        RoleDtoPost roleDtoPost = EASY_RANDOM.nextObject(RoleDtoPost.class);
        roleEntity.setName(roleDtoPost.getName());

        service.createRoleDto(roleDtoPost);
        verify(repository).save(roleEntity);
    }

    @Test
    public void deleteRole(){
        RoleEntity roleEntity = EASY_RANDOM.nextObject(RoleEntity.class);

        when(repository.findById(roleEntity.getRoleId())).thenReturn(Optional.of(roleEntity));
        service.deleteRole(roleEntity.getRoleId());
        verify(repository, times(1)).deleteById(roleEntity.getRoleId());
        verify(repository, times(1)).findById(roleEntity.getRoleId());
    }
}
