package org.jsp.irctc.controller;

import java.util.List;

import org.jsp.irctc.dto.Train;
import org.jsp.irctc.helper.ResponseStructure;
import org.jsp.irctc.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/train")
public class TrainController {
	@Autowired
	TrainService trainService;

	@PostMapping("/")
	public ResponseStructure<Train> addTrain(@RequestBody Train train, @RequestHeader("Authorization") String token) {
		return trainService.addTrain(train, token); // Only admin should be able to add trains
	}

	@GetMapping("/{id}")
	public ResponseStructure<Train> fetchById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		return trainService.fetchById(id, token);
	}

	@GetMapping("/")
	public ResponseStructure<List<Train>> fetchAll(@RequestHeader("Authorization") String token) {
		return trainService.fetchAll(token);
	}

	// Add more methods as needed
}
