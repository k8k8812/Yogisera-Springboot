package com.cognixia.jump.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.AlreadyExistedException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Student;
import com.cognixia.jump.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	private final StudentService service;
	
	@Autowired
	public StudentController(StudentService service) {
		this.service = service;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getAll());
	}
	
	@GetMapping("/{studentId}")
	public ResponseEntity<?> getStudentById(@PathVariable Long studentId) throws ResourceNotFoundException {
		Student stu = service.getStuById(studentId);
		return ResponseEntity.status(HttpStatus.OK).body("Successfully get student "+ stu);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addStudent(@RequestBody Student student) throws AlreadyExistedException  {
		service.addStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body("Successfully add student "+ student);
	}
	
	

}
