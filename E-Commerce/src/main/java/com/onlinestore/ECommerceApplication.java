package com.onlinestore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onlinestore.domain.User;
import com.onlinestore.service.UserService;
import com.onlinestore.utility.SecurityUtility;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User();
		user1.setFullname("Nguyen Van A");
		user1.setUsername("vana");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("vana@gmail.com");
		user1.setEnabled(true);
		user1.setType(true);
		userService.createUser(user1);	
	}
}
