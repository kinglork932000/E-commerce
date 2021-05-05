package com.onlinestore.service;

import java.util.Set;

import com.onlinestore.domain.User;
import com.onlinestore.domain.security.PasswordResetToken;

public interface UserService {
	
	PasswordResetToken getPasswordResetToken(final String token);
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	User findByEmail(String email);
	
	User createUser(User user) throws Exception;
	void updateInfo(User user, User userUpdate) throws Exception;
	
	User save(User user);
}
