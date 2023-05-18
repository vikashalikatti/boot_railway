package org.jsp.irctc.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@ManyToOne
	User user;

	Long userId;
	Long trainId;
	String source;
	String destination;
	String travelClass; // AC, Sleeper, Chair Car
	String status; // Booked, Cancelled
}