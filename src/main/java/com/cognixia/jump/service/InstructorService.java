package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.AlreadyExistedException;
import com.cognixia.jump.model.Instructor;
import com.cognixia.jump.repository.InstructorRepository;

@Service
public class InstructorService {
	
	private final InstructorRepository repo;
	
	@Autowired
	public InstructorService(InstructorRepository repo) {
		this.repo = repo;
	}
	
	public List<Instructor> getAll(){
		return repo.findAll();
	}
	
	public Instructor addInstructor(Instructor inst) throws AlreadyExistedException  {
		
		Optional<Instructor> find = repo.findByName(inst.getFirstName(), inst.getLastName());
		
		if(find.isPresent()) {
			
			//TODO: create alreadyExisted Exception;
			throw new AlreadyExistedException();
		}
		
		return repo.save(inst);
	}
	
	
}
