package org.jsp.irctc.repository;

import org.jsp.irctc.dto.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
	// add more methods as needed
}