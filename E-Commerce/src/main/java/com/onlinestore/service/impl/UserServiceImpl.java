package com.onlinestore.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.domain.User;
import com.onlinestore.domain.security.PasswordResetToken;
import com.onlinestore.repository.PasswordResetTokenRepository;
import com.onlinestore.repository.UserRepository;
import com.onlinestore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User createUser(User user) throws Exception {
		
		User localUser = userRepository.findByUsername(user.getUsername());
		if (localUser != null) {
			LOG.info("user {} already exists. Nothing will be done", user.getUsername());
		} else {
			localUser = userRepository.save(user);
		}
		return localUser;
	}
	
	@Override
	public void updateInfo(User user, User userUpdate) throws Exception{
		user.setAddress(userUpdate.getAddress());
		user.setEmail(userUpdate.getEmail());
		user.setDateofbirth(userUpdate.getDateofbirth());
		user.setPhone(userUpdate.getPhone());
		user.setFullname(userUpdate.getFullname());
		userRepository.save(user);
		//userRepository.updateUser(userUpdate.getFullname(), userUpdate.getAddress(), userUpdate.getDateofbirth(), userUpdate.getEmail(), userUpdate.getPhone(), user.getUsername());
	}

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
		
	};
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
}
