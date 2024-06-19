package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
