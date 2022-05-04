package com.cognixia.jump.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.AlreadyExistedException;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Student;
import com.cognixia.jump.repository.StudentRepository;

@Service
public class StudentService {
	
	private final StudentRepository repo;
	
	@Autowired
	public StudentService(StudentRepository repo) {
		this.repo = repo;
	}
	
	
	public List<Student> getAll(){
		return repo.findAll();
	}
	
	public Student getStuById(Long id) throws ResourceNotFoundException {
		Optional<Student> find = repo.findById(id);
		if(find.isPresent()) {
			return find.get();
		}
		//TODO: gonna throw ResourceNotFound exception.
		throw new ResourceNotFoundException(LocalDateTime.now());
	}
	
	public void addStudent(Student student) throws AlreadyExistedException  {
		
		Optional<Student> find = repo.findByName(student.getFirstName(), student.getLastName());
		if(find.isPresent()) {
			//TODO: gonna throw AlreadyExisted exception.
			throw new AlreadyExistedException();
		}
		repo.save(student);
	}

}
