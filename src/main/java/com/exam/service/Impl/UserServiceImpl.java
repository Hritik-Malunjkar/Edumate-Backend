package com.exam.service.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	//creating user
//	@Override
//	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
//		// TODO Auto-generated method stub
//
//		User local = this.userRepository.findByUsername(user.getUsername());
//		if (local != null) {
//			System.out.println("User is already there!");
//			throw new Exception("User already present!!");
//		} else {
//			// user create
//			for (UserRole ur : userRoles) {
//				roleRepository.save(ur.getRole());
//			}
//
//			user.getUserRoles().addAll(userRoles);
//			local = this.userRepository.save(user);
//		}
//
//		return local;
//	}
	
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
	
	    User localUserByEmail = this.userRepository.findByEmail(user.getEmail());
	    User localUserByUsername = this.userRepository.findByUsername(user.getUsername());

	    if (localUserByEmail != null || localUserByUsername != null ) {
	        System.out.println("User already exist!");
	        throw new Exception("User already exist!");
	    }
	    
	    for (UserRole ur : userRoles) {
	        roleRepository.save(ur.getRole());
	    }

	    user.getUserRoles().addAll(userRoles);
	    User createdUser = this.userRepository.save(user);

	    return createdUser;
	}


	//getting user by username
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(userId);
	}

}
