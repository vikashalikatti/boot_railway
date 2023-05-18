package org.jsp.irctc.service;

import org.jsp.irctc.dto.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TrainService {
	@Autowired
	TrainDao trainDao;
	@Autowired
	JwtUtil jwtUtil;

	public ResponseStructure<Train> addTrain(Train train, String token) {
		// Verify token and check if admin
		if (jwtUtil.validateToken(token) && jwtUtil.extractRole(token).equals("ADMIN")) {
			Train savedTrain = trainDao.save(train);
			ResponseStructure<Train> response = new ResponseStructure<>();
			response.setMessage("Train added successfully");
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setData(savedTrain);
			return response;
		} else {
			// Return an unauthorized error
		}
	}

	// Add more methods as needed
}
