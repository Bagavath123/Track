package com.example.track.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.track.entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long>{

	@Query("select u from UserEntity u where u.username = :userName and u.password = :password " )
	UserEntity loginUser(@Param("userName") String userName,@Param("password") String password);

	UserEntity save(UserEntity newUser);
	
	UserEntity  findByUsername(String username);
}
