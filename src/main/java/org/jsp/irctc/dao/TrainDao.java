package org.jsp.irctc.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.irctc.dto.Train;
import org.jsp.irctc.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainDao {
	@Autowired
	private TrainRepository repository;

	public Train save(Train train) {
		return repository.save(train);
	}

	public Optional<Train> findById(Long id) {
		return repository.findById(id);
	}

	public List<Train> findAll() {
		return repository.findAll();
	}

	// Add more methods as needed
}
