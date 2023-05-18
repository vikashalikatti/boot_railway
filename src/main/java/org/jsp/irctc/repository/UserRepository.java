package org.jsp.irctc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<org.jsp.irctc.dto.User, Long> {
	Optional<org.jsp.irctc.dto.User> findByUsername(String username);
}