package com.capstone.kelompok10.service.implementation;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.capstone.kelompok10.model.dto.UserDto;
import com.capstone.kelompok10.model.entity.RoleEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.RoleRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class UserServiceImpl implements UserService, UserDetailsService {
    UserRepository userRepository;

    @Autowired
    PasswordEncoder crypt;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null){
            log.error("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        } else {
            log.info("User found : {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public List<UserEntity> getAllUser() {
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public List<UserDto> getAllUserDto() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        
        users.forEach(isi ->{
            UserDto dto = new UserDto();
            dto.setUser_id(isi.getUser_id());
            dto.setName(isi.getName());
            dto.setUsername(isi.getUsername());

            userDtos.add(dto);
        });
        return userDtos;
    }

    @Override
    public UserEntity getUserById(Long user_id) {
        return userRepository.findById(user_id).get();
    }

    @Override
    public void createUser(UserEntity user) {
        log.info("Membuat user baru {} ke database", user.getName());
        user.setPassword(crypt.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long user_id, UserEntity user) {
        UserEntity user2 = userRepository.findById(user_id).get(); 
        System.out.println(user2.toString());
        user2.setName(user.getName());
        user2.setUsername(user.getUsername());
        user2.setPassword(user.getPassword());
        userRepository.save(user2);
    }

    @Override
    public void deleteUser(Long user_id) {
        userRepository.deleteById(user_id);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Menambahkan Role {} ke user {}", roleName, username);
        UserEntity user = userRepository.findByUsername(username);
        RoleEntity role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        
    }

    @Override
    public UserEntity getUser(String username){
        return userRepository.findByUsername(username);
    }
}
