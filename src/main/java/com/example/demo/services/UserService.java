package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.demo.models.User;
import com.example.demo.repo.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;

	public Optional<User> getUserDetails(int id) {
		
		return userRepo.findById(id);
		
	}

	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}

	public java.util.List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

}
