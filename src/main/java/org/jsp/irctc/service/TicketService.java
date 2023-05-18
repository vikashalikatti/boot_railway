package org.jsp.irctc.service;

import org.jsp.irctc.dao.TicketDao;
import org.jsp.irctc.dao.UserDao;
import org.jsp.irctc.dto.Ticket;
import org.jsp.irctc.dto.User;
import org.jsp.irctc.helper.ResponseStructure;
import org.jsp.irctc.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
	@Autowired
	TicketDao ticketDao;
	@Autowired
	UserDao userDao;
	@Autowired
	JwtUtil jwtUtil;

	public ResponseStructure<Ticket> bookTicket(Ticket ticket, String token) {
		// Verify token
		if (jwtUtil.validateToken(token)) {
			Long userId = jwtUtil.extractUserId(token);
			User user = userDao.findById(userId);
			ticket.setUser(user);
			Ticket savedTicket = ticketDao.save(ticket);
			ResponseStructure<Ticket> response = new ResponseStructure<>();
			response.setMessage("Ticket booked successfully");
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setData(savedTicket);
			return response;
		} else {
			// Return an unauthorized error
		}
	}

	// Add more methods as needed
}
