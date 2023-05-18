package org.jsp.irctc.dao;

import org.jsp.irctc.dto.User;
import org.jsp.irctc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	UserRepository repository;

	public User save(User user) {
		return repository.save(user);
	}

	public User findByUsername(String username) {
		return repository.findByUsername(username).orElse(null);
	}
}
