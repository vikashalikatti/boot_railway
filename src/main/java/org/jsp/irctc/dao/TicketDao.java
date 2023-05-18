package org.jsp.irctc.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.irctc.dto.Ticket;
import org.jsp.irctc.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao {
    @Autowired
    private TicketRepository repository;

    public Ticket save(Ticket ticket) {
        return repository.save(ticket);
    }

    public List<Ticket> findAll() {
        return repository.findAll();
    }

    public Optional<Ticket> findById(Long id) {
        return repository.findById(id);
    }

    // Add more methods as needed
}
