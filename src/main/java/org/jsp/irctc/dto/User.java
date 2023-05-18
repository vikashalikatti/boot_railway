package org.jsp.irctc.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
//other import statements

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String name;

	@Column(unique = true)
	String email;

	String password;
	String role;

	@Column(unique = true)
	String username;
	// to differentiate between admin and user
}
