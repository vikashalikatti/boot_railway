package org.jsp.irctc.controller;

import java.util.List;

import org.jsp.irctc.dto.Ticket;
import org.jsp.irctc.helper.ResponseStructure;
import org.jsp.irctc.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	@Autowired
	TicketService ticketService;

	@PostMapping("/")
	public ResponseStructure<Ticket> bookTicket(@RequestBody Ticket ticket,
			@RequestHeader("Authorization") String token) {
		return ticketService.bookTicket(ticket, token);
	}

	@GetMapping("/{id}")
	public ResponseStructure<Ticket> fetchById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		return ticketService.fetchById(id, token);
	}

	@GetMapping("/")
	public ResponseStructure<List<Ticket>> fetchAll(@RequestHeader("Authorization") String token) {
		return ticketService.fetchAll(token);
	}
}
