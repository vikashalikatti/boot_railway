package org.jsp.irctc.service;

import java.util.List;

import org.jsp.irctc.dao.TicketDao;
import org.jsp.irctc.dao.UserDao;
import org.jsp.irctc.dto.Ticket;
import org.jsp.irctc.dto.User;
import org.jsp.irctc.helper.ResponseStructure;
import org.jsp.irctc.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public ResponseStructure<Ticket> bookTicket(Ticket ticket, String token) {
        String username = jwtUtil.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtUtil.validateToken(token, userDetails)) {
            User user = userDao.findByUsername(username);
            ticket.setUser(user);
            Ticket savedTicket = ticketDao.save(ticket);
            ResponseStructure<Ticket> response = new ResponseStructure<>();
            response.setMessage("Ticket booked successfully");
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setData(savedTicket);
            return response;
        } else {
            ResponseStructure<Ticket> response = new ResponseStructure<>();
            response.setMessage("Ticket not booked successfully");
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setData(null);
            return response;
        }
    }

    public ResponseStructure<Ticket> fetchById(Long id, String token) {
        String username = jwtUtil.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtUtil.validateToken(token, userDetails)) {
            Ticket ticket = ticketDao.findById(id).orElse(null);
            if (ticket != null) {
                ResponseStructure<Ticket> response = new ResponseStructure<>();
                response.setMessage("Ticket found");
                response.setStatusCode(HttpStatus.OK.value());
                response.setData(ticket);
                return response;
            }
        }
        ResponseStructure<Ticket> response = new ResponseStructure<>();
        response.setMessage("Ticket not found");
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setData(null);
        return response;
    }

    public ResponseStructure<List<Ticket>> fetchAll(String token) {
        String username = jwtUtil.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (jwtUtil.validateToken(token, userDetails)) {
            List<Ticket> tickets = ticketDao.findAll();
            if (!tickets.isEmpty()) {
                ResponseStructure<List<Ticket>> response = new ResponseStructure<>();
                response.setMessage("Tickets found");
                response.setStatusCode(HttpStatus.OK.value());
                response.setData(tickets);
                return response;
            }
        }
        ResponseStructure<List<Ticket>> response = new ResponseStructure<>();
        response.setMessage("No tickets found");
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setData(null);
        return response;
    }

    // Add more methods as needed
}
