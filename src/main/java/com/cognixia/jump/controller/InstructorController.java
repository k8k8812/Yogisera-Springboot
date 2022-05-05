package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Instructor;
import com.cognixia.jump.service.InstructorService;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
	
private final InstructorService service;
	
	@Autowired
	public InstructorController(InstructorService service) {
		this.service = service;
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllInstructor(){
		List<Instructor> all = service.getAll();
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
								.body("All the Instructors: "+ all);
	}
	
	@GetMapping("/{instructorId}")
	public ResponseEntity<?> viewInstructorById(@PathVariable Long instructorId) 
												throws ResourceNotFoundException{
		
		return ResponseEntity.status(HttpStatus.OK).body(service.viewById(instructorId));
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addInstructor(@RequestBody Instructor inst) throws Exception{
		
		service.addInstructor(inst);
		return ResponseEntity.status(HttpStatus.OK)
						.body("New Instructor :" + inst.getFirstName() + " "
									+ inst.getLastName() +" added.");
	}

}
