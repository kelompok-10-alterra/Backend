package com.capstone.kelompok10.service.implementation;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.capstone.kelompok10.model.dto.get.UserDtoGet;
import com.capstone.kelompok10.model.dto.post.UserDtoPost;
import com.capstone.kelompok10.model.entity.RoleEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.RoleRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.email.EmailValidatorService;
import com.capstone.kelompok10.service.email.PhonePasswordValidator;
import com.capstone.kelompok10.service.interfaces.RoleService;
import com.capstone.kelompok10.service.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Transactional
@Log4j2
public class UserServiceImpl implements UserService, UserDetailsService {
    UserRepository userRepository;

    @Autowired
    EmailValidatorService emailValidatorService;

    @Autowired
    PhonePasswordValidator phonePasswordValidator;

    @Autowired
    RoleService roleService;

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
    public List<UserEntity> findAll() {
        log.info("Get all User without DTO");
        List<UserEntity> user = new ArrayList<>();
        userRepository.findAll().forEach(user::add);
        return user;
    }
    
    @Override
    public Page<UserEntity> findAllPagination(int offset, int pageSize) {
        log.info("Get all User with Pagination");
        Page<UserEntity> user = userRepository.findAll(PageRequest.of(offset, pageSize));
        return user;
    }

    @Override
    public Page<UserEntity> findAllPaginationSort(int offset, int pageSize, String field){
        log.info("Get all User with Pagination and Sorting");
        Page<UserEntity> user = userRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return user;
    }

    @Override
    public List<UserDtoGet> findAllDto() {
        log.info("Get all User with DTO");
        List<UserEntity> users = userRepository.findAll();
        List<UserDtoGet> userDtos = new ArrayList<>();
        
        users.forEach(isi ->{
            UserDtoGet dto = new UserDtoGet();
            dto.setUserId(isi.getUserId());
            dto.setName(isi.getName());
            dto.setUsername(isi.getUsername());
            dto.setEmail(isi.getEmail());
            dto.setPhone(isi.getPhone());
            dto.setAddress(isi.getAddress());
            dto.setImageUrl(isi.getImageUrl());
            dto.setMembership(isi.getMembership().getMembershipId());

            userDtos.add(dto);
        });
        return userDtos;
    }

    @Override
    public UserEntity getUserById(Long userId) {
        if(userRepository.findById(userId) == null){
            log.info("User with id {} not found", userId);
            throw new IllegalStateException("User not Found");
        }
            log.info("User with id {} found", userId);
            return userRepository.findById(userId).get();
    }

    @Override
    public void createUser(UserEntity user) {
        log.info("Membuat user baru {} ke database", user.getName());
        user.setPassword(crypt.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, UserDtoPost userDtoPost) {
        if(userRepository.findById(userId) != null){
            UserEntity user2 = userRepository.findById(userId).get(); 
            user2.setName(userDtoPost.getName());
            user2.setUsername(userDtoPost.getUsername());
            user2.setPhone(userDtoPost.getPhone());
            user2.setAddress(userDtoPost.getAddress());
            user2.setImageUrl(userDtoPost.getImageUrl());
            userRepository.save(user2);
            log.info("User updated");
        }else{
            log.info("User with id {} not found", userId);
            throw new IllegalStateException("User Not Found");
        }
    }

    @Override
    public void deleteUser(Long userId) {
        if(userRepository.findById(userId) != null){
            userRepository.deleteById(userId);
            log.info("User with id {} successfully deleted", userId);
        }else{
            log.info("User with id {} not found", userId);
            throw new IllegalStateException("User Not Found");
        }
    }

    @Override
    public UserEntity getUser(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean userHaveMembership(Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        if(user.getMembership() == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean userExist(Long userId) {
        if(userRepository.findById(userId) == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void createUserDto(UserDtoPost userDtoPost) {
        UserEntity user = new UserEntity();
        boolean isValidEmail = emailValidatorService.test(userDtoPost.getEmail());
        if (isValidEmail && userRepository.findByUsername(userDtoPost.getUsername()) == null && userRepository.findByEmail(userDtoPost.getEmail()) == null){
            if(phonePasswordValidator.phoneValidator(userDtoPost.getPhone()) == true && phonePasswordValidator.passwordValidator(userDtoPost.getPassword()) == true){
                user.setUsername(userDtoPost.getUsername());
                user.setPassword(crypt.encode(userDtoPost.getPassword()));
                user.setPhone(userDtoPost.getPhone());
                user.setEmail(userDtoPost.getEmail());
                user.setName(userDtoPost.getName());
                user.setAddress(userDtoPost.getAddress());
                user.setImageUrl(userDtoPost.getImageUrl());
                user.setMembership(null);
                user.setToken(null);

                userRepository.save(user);
                log.info("user created succesfully");
                addRoleToUser(userDtoPost.getUsername(), "ROLE_USER");
            }else{
                log.info("Phone or Password invalid");
                throw new IllegalStateException("Phone or Password invalid");
            }
        } else {
            throw new IllegalStateException("Email not valid or Email or Username already used");
        }
    }
    
    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Menambahkan Role {} ke user {}", roleName, username);
        UserEntity user = userRepository.findByUsername(username);
        RoleEntity role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        
    }

    
    @Override
    public int totalUser() {
        List<UserEntity> user = new ArrayList<>();
        userRepository.findAll().forEach(user::add);
        int sum = user.size();
        return sum;
    }
}