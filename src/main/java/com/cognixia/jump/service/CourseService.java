package com.cognixia.jump.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Course;
import com.cognixia.jump.repository.CourseRepository;

@Service
public class CourseService {
	
	
	private final CourseRepository courseRepo;
	
	@Autowired
	public CourseService(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}
	
	
	//get all the courses;
	public List<Course> getAllCourses() {
		return courseRepo.findAll();
	}
	
	
	//add a course
	public Course addCourse(Course course) {
		return courseRepo.save(course);
	}
	
	//add an instructor to a course;
	

}
