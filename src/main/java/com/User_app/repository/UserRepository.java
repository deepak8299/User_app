package com.User_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.User_app.entity.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    
   // Optional<User> findByUsername(String username);
	 
	Optional<User> findByUsername(String username);

}
