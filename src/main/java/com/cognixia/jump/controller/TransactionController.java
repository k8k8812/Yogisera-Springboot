package com.cognixia.jump.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	
	private final TransactionService service;
	
	@Autowired
	public TransactionController(TransactionService service) {
		this.service = service;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getAll());
	}
	
	@PostMapping("/purchase/{courseId}/{studentId}") 
	public ResponseEntity<?> purchaseCourse(@PathVariable Long courseId,
											@PathVariable Long studentId) throws ResourceNotFoundException {
												
			service.coursePurchase(studentId, courseId);
			
			return ResponseEntity.status(HttpStatus.ACCEPTED)
			.body("Student with Id: " + studentId 
			+ "has successfully purchased course with Id: " + courseId);
	}
											
	
}
