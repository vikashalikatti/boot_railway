package org.jsp.irctc.service;

import java.util.Optional;

import org.jsp.irctc.dao.UserDao;
import org.jsp.irctc.dto.User;
import org.jsp.irctc.helper.ResponseStructure;
import org.jsp.irctc.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private JwtUtil jwtUtil;

	public ResponseStructure<User> register(User user) {
		User savedUser = userDao.save(user);
		ResponseStructure<User> response = new ResponseStructure<>();
		response.setMessage("User registered successfully");
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setData(savedUser);
		return response;
	}

	public ResponseStructure<String> login(User user) {
		UserDetails fetchedUser = (UserDetails) userDao.findByUsername(user.getUsername());
		ResponseStructure<String> response = new ResponseStructure<>();
		if (fetchedUser != null && fetchedUser.getPassword().equals(user.getPassword())) {
			String token = jwtUtil.generateToken(fetchedUser);
			response.setMessage("User logged in successfully");
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(token);
		} else {
			response.setMessage("Invalid username or password");
			response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		}
		return response;
	}

	public ResponseStructure<User> fetchById(Long id) {
		Optional<User> userOptional = userDao.findById(id);
		ResponseStructure<User> response = new ResponseStructure<>();
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			response.setMessage("User found");
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(user);
		} else {
			response.setMessage("User not found");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setData(null);
		}
		return response;
	}

	// Add more methods as needed
}
