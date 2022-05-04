package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Course;
import com.cognixia.jump.model.Instructor;
import com.cognixia.jump.model.Student;
import com.cognixia.jump.repository.CourseRepository;
import com.cognixia.jump.repository.InstructorRepository;
import com.cognixia.jump.repository.StudentRepository;

@Service
public class CourseService {
	
	
	private final CourseRepository courseRepo;
	
	@Autowired 
	private InstructorRepository instructorRepo;
	
	@Autowired 
	private StudentRepository stuRepo;
	
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
	
	//add a student to a course;
	public Course enrollStudentToCourse(Long stuId, Long courseId) throws Exception {
		
		Optional<Student> student = stuRepo.findById(stuId);
		Optional<Course> course = courseRepo.findById(courseId);
		
		if(student.isPresent() && course.isPresent()) {
			course.get().enrollStudent(student.get());
			Course toAdd = courseRepo.saveAndFlush(course.get());
			return toAdd;
		}
		throw new Exception("failed to add student into course: "+ courseId);
	}
	
	

}
