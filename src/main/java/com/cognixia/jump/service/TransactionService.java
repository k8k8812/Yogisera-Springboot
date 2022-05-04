package com.cognixia.jump.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Course;
import com.cognixia.jump.model.Student;
import com.cognixia.jump.model.Transaction;
import com.cognixia.jump.repository.CourseRepository;
import com.cognixia.jump.repository.StudentRepository;
import com.cognixia.jump.repository.TransactionRepository;

@Service
public class TransactionService {

	private final TransactionRepository tranRepo;
	
	@Autowired
	private StudentRepository stuRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	public TransactionService(TransactionRepository tranRepo) {
		this.tranRepo = tranRepo;
	}
	
	
	public List<Transaction> getAll(){
		return tranRepo.findAll();
	}
	
	
	//to buy a course;
	public void coursePurchase(Long studentId, Long courseId) throws ResourceNotFoundException {
		Transaction toAdd = new Transaction();
		
		//TODO: exceptions should be thrown here; 
		Optional<Student> student = stuRepo.findById(studentId);
		Optional<Course> course = courseRepo.findById(courseId);
		
		if(student.isPresent() && course.isPresent()) {
			toAdd.setBuyer_id(student.get().getId());
			toAdd.setBuyer_name(student.get().getFirstName() + " "+ student.get().getLastName());
			toAdd.setCourse_id(course.get().getId());
			toAdd.setCourse_name(course.get().getName());
			toAdd.setDatePurchase(new Date());
			
			tranRepo.save(toAdd);
		}else {
			throw new ResourceNotFoundException(LocalDateTime.now() );
		}
		
		
	}
}
