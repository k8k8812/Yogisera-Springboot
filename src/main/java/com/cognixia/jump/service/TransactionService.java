package com.cognixia.jump.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void coursePurchase(Long studentId, Long courseId) {
		Transaction toAdd = new Transaction();
		
		//TODO: exceptions should be thrown here; 
		Student student = stuRepo.findById(studentId).get();
		Course course = courseRepo.findById(courseId).get();
		
		toAdd.setBuyer_id(student.getId());
		toAdd.setBuyer_name(student.getFirstName() + " "+ student.getLastName());
		toAdd.setCourse_id(course.getId());
		toAdd.setCourse_name(course.getName());
		toAdd.setDatePurchase(new Date());
		
		tranRepo.save(toAdd);
	}
}
