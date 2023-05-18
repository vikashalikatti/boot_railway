package org.jsp.irctc.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String name;
	String source;
	String destination;
	int acSeats;
	int sleeperSeats;
	int chairCarSeats;
}
