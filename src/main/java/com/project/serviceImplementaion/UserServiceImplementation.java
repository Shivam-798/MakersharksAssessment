package com.project.serviceImplementaion;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.entities.User;
import com.project.repository.UserRepository;
import com.project.service.UserService;
import com.project.exception.ResouceNotFoundException;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		User saveUser = this.userRepository.save(user);
		return saveUser;
	}

	@Override
	 public User fetchUserByUsername(String username) {
        System.out.println("Fetching user by username: " + username);
        
        User user = this.userRepository.findByUsername(username);
        
        if (user == null) {
            throw new ResouceNotFoundException("User", "username", username);
        }
        
        System.out.println("User found: " + user.getEmail());
        return user;
    }

}
