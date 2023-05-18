package org.jsp.irctc.dao;
import org.jsp.irctc.dto.Train;
import org.jsp.irctc.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainDao {
	@Autowired
	TrainRepository repository;

	public Train save(Train train) {
		return repository.save(train);
	}

	// add more methods as needed
}
