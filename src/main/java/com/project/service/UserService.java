package com.project.service;

import java.util.Optional;

import com.project.entities.User;

public interface UserService {

	User registerUser(User user);
	
   User fetchUserByUsername(String username);
}
