package com.example.User_Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.User_Service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 User findByEmail(String email);

	 boolean existsByEmail(String email);

}
