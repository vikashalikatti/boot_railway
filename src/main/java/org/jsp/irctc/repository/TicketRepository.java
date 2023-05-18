package org.jsp.irctc.repository;

import org.jsp.irctc.dto.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	// add more methods as needed
}