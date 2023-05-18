package org.jsp.irctc.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String name;
	String email;
	String password;
	String role; 
	String username;
	// to differentiate between admin and user
}
