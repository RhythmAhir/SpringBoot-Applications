package com.fdmgroup.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.fdmgroup.model.User;
import com.fdmgroup.repository.UserRepository;

@Service
@Transactional
public class UserService {

	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	public void saveMyUser(User user) {
		
		userRepo.save(user);
	}
	
	public List<User> showAllUser(){
		List<User> users = new ArrayList<User>();
		for(User user:userRepo.findAll()) {
		users.add(user);	
		}		
		return users;
	}


	public void deleteMyUser(int id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
	}
	

	public User editMyUser(int id) {
			Optional<User> studentResponse =  userRepo.findById(id);
			User user = studentResponse.get();
			return user;
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return userRepo.findByUsernameAndPassword(username, password);
			}
	
	
}
