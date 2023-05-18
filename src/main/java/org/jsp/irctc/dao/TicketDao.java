package org.jsp.irctc.dao;

import org.jsp.irctc.dto.Ticket;
import org.jsp.irctc.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao {
	@Autowired
	TicketRepository repository;

	public Ticket save(Ticket ticket) {
		return repository.save(ticket);
	}

	// add more methods as needed
}