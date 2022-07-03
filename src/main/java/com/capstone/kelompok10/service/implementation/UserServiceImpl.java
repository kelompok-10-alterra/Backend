package com.capstone.kelompok10.service.implementation;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import com.capstone.kelompok10.model.dto.get.UserDtoGet;
import com.capstone.kelompok10.model.dto.post.UserDtoPost;
import com.capstone.kelompok10.model.dto.put.UserDtoPut;
import com.capstone.kelompok10.model.entity.CartEntity;
import com.capstone.kelompok10.model.entity.MemberEntity;
import com.capstone.kelompok10.model.entity.RoleEntity;
import com.capstone.kelompok10.model.entity.UserEntity;
import com.capstone.kelompok10.repository.CartRepository;
import com.capstone.kelompok10.repository.ClassRepository;
import com.capstone.kelompok10.repository.MemberRepository;
import com.capstone.kelompok10.repository.RoleRepository;
import com.capstone.kelompok10.repository.UserRepository;
import com.capstone.kelompok10.service.email.EmailValidatorService;
import com.capstone.kelompok10.service.email.PhonePasswordValidator;
import com.capstone.kelompok10.service.interfaces.BookingService;
import com.capstone.kelompok10.service.interfaces.MembershipService;
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
    CartRepository cartRepository;

    @Autowired
    BookingService bookingService;

    @Autowired
    MembershipService membershipService;

    @Autowired
    PasswordEncoder crypt;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    MemberRepository memberRepository;

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
	public List<UserEntity> getAllRoleUser(){
		List<UserEntity> user = new ArrayList<>();
        userRepository.findByRoleName("ROLE_USER").forEach(user::add);
		return user;
	}

    @Override
	public List<UserEntity> getAllRoleAdmin(){
		List<UserEntity> user = new ArrayList<>();
        userRepository.findByRoleName("ROLE_ADMIN").forEach(user::add);
		return user;
	}

    @Override
	public List<UserEntity> getAllRoleSuperAdmin(){
		List<UserEntity> user = new ArrayList<>();
        userRepository.findByRoleName("ROLE_SUPER_ADMIN").forEach(user::add);
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
            dto.setMembership(isi.getMembership());
            dto.setImageUrl(isi.getImageUrl());

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
    public void updateUser(Long userId, UserDtoPut userDtoPut) {
        if(userRepository.findById(userId) != null && nativeUser(userId) == false){
            UserEntity user2 = userRepository.findById(userId).get(); 
            user2.setName(userDtoPut.getName());
            user2.setPhone(userDtoPut.getPhone());
            user2.setAddress(userDtoPut.getAddress());
            user2.setImageUrl(userDtoPut.getImageUrl());
            userRepository.save(user2);
            log.info("User updated");
        }else{
            log.info("User with id {} not found", userId);
            throw new IllegalStateException("User Not Found");
        }
    }

    @Override
    public void deleteUser(Long userId) {
        if(userRepository.findById(userId).isPresent() && nativeUser(userId) == false){
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
    public int userHaveMembership(Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        if(user.getMembership() == null){
            return 1;
        }if(user.getMembership() == "Silver" && user.getStatus() == true){
            return 2;
        }if(user.getMembership() == "Gold" && user.getStatus() == true){
            return 3;
        }if(user.getMembership() == "Platinum" && user.getStatus() == true){
            return 4;
        }else{
            return 99;
        }
    }

    @Override
    public Boolean userExist(Long userId) {
        if(userRepository.findById(userId).isPresent() == true){
            return true;
        }else{
            return false;
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
                user.setStatus(false);
                user.setToken(null);
                CartEntity cart = new CartEntity();
                cart.setUser(user);
                user.setCart(cart);

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
        if(userRepository.findByUsername(username) != null && roleRepository.findByName(roleName) != null){
            if(user.getRoles().isEmpty()){
                RoleEntity role = roleRepository.findByName(roleName);
                user.getRoles().add(role);
                user.setRoleName(roleName);
                userRepository.save(user);
                log.info("Succesfully added role to user {}", username);
            }else{
                user.getRoles().removeAll(user.getRoles());
                RoleEntity role = roleRepository.findByName(roleName);
                user.getRoles().add(role);
                user.setRoleName(roleName);
                userRepository.save(user);
                log.info("Previous role for {} has been changed to {}", username, roleName);
            }
        }else{
            log.info("Role / Username did'nt exist");
            throw new IllegalStateException("Role / Username did'nt exist");
        }
        
    }
    
    @Override
    public int totalUser() {
        List<UserEntity> user = new ArrayList<>();
        userRepository.findAll().forEach(user::add);
        int sum = user.size();
        return sum;
    }

    @Override
    public void getMembership(Long userId, Long memberId, Long membershipId) {
        UserEntity user = userRepository.findById(userId).get();
        MemberEntity member = memberRepository.findById(memberId).get();
        if(membershipService.membershipExpired(membershipId) == true){
            user.setStatus(false);
            user.setMembership(null);
            userRepository.save(user);
        }else{
            user.setStatus(true);
            user.setMembership(member.getName());
            userRepository.save(user);
        }
    }

    @Override
    public List<UserEntity> findAll(String keyword) {
        List<UserEntity> user = userRepository.findAll(keyword);
        return user;
    }

    @Override
    public void getPoint(Long userId){
        UserEntity user = userRepository.findById(userId).get();
        Long point = user.getPoint();
        user.setPoint(point + 100L);
    }

    @Override
    public UserDtoGet getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        UserDtoGet dto = new UserDtoGet();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setImageUrl(user.getImageUrl());
        dto.setMembership(user.getMembership());
        dto.setPoint(user.getPoint());

        return dto;
    }

    @Override
    public Boolean nativeUser(Long userId) {
        if(userId == 1 || userId == 2 || userId == 3 || userId == 4){
            log.info("You Can,t Use Native User");
            return true;
        }else{
            return false;
        }       
    }

    // @Override
    // public List<UserEntity> getAllRoleUser(String keyword) {
    //     List<UserEntity> user = new ArrayList<>();
    //     userRepository.findByRoleName("ROLE_USER", keyword).forEach(user::add);
	// 	return user;
    // }

    // @Override
    // public void addFavorite(String username, String name) {
    //     UserEntity user = userRepository.findByUsername(username);
    //     ClassEntity isi = classRepository.findByName(name);
    //     user.getClasses().add(isi);
    // }
}