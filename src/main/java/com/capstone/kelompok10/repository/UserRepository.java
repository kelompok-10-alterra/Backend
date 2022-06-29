package com.capstone.kelompok10.repository;

import com.capstone.kelompok10.model.entity.UserEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
	public UserEntity findByEmail(String email);
	
	@Query("SELECT u FROM UserEntity u WHERE u.token = ?1")
	public UserEntity findByToken(String token);
    
    @Query("SELECT u FROM UserEntity u WHERE u.username LIKE %?1%"
            + "OR u.name LIKE %?1%"
            + "OR u.address LIKE %?1%")
    public List<UserEntity> findAll(String keyword);

    UserEntity findByUsername(String username);
    List<UserEntity> findByName(String name);
    List<UserEntity> findByRoleName(String roleName);
}
