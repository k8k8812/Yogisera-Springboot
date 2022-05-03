package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Course;
import com.cognixia.jump.model.Instructor;
import com.cognixia.jump.repository.CourseRepository;
import com.cognixia.jump.repository.InstructorRepository;

@Service
public class CourseService {
	
	
	private final CourseRepository courseRepo;
	
	@Autowired 
	private InstructorRepository instructorRepo;
	
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
	public Course enrollInstructorToCourse(Long instId, Long courseId) throws Exception {
		
		
		Optional<Instructor> instructor = instructorRepo.findById(instId);
		Optional<Course> course = courseRepo.findById(courseId);
		
		if(course.isPresent() && instructor.isPresent()) {
			course.get().enrollInstructor(instructor.get());
			return courseRepo.save(course.get());
		}
		
		throw new Exception("failed to add instructor into course: "+ courseId);
		
	}

}
