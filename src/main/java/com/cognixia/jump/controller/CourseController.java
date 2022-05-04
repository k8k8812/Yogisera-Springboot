package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Course;
import com.cognixia.jump.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {
	
	private final CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllCourses(){
		Optional<List<Course>> all = Optional.of(courseService.getAllCourses());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(all.get());
	}
	
	@GetMapping("/courseinfo")
	public ResponseEntity<?> courseInfo(){
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(">>>>>> Yogi Course Info: >>>>>>" 
							+ courseService.showCourseInfo());
	}
	
	//view enrolled course for login student
	@GetMapping("/getCourseList/{studentId}")
	public ResponseEntity<?> getCourseListForOneStudent(@PathVariable Long studentId) 
			throws ResourceNotFoundException{
		List<Course> find;
		
			find = courseService.getCourseListForOneStudent(studentId);
			return ResponseEntity.status(HttpStatus.OK)
					.body(">>> Courses enrolled are:  >>>>   "+ find);
	
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addCourse(@RequestBody Course course){
		courseService.addCourse(course);
		
		return ResponseEntity.status(HttpStatus.OK).body("Successfully added new course " + course.getName());
	}
	
	@PutMapping("/{courseId}/instructor/{instructorId}")
	public ResponseEntity<?> enrollInstructorToCourse(@PathVariable Long courseId,
						@PathVariable Long instructorId) throws Exception {
		courseService.enrollInstructorToCourse(instructorId, courseId);
		return ResponseEntity.status(HttpStatus.OK).body("Successfully added instructor with ID "
					+ instructorId + " to course with ID: "+ courseId);
	}
	
	@PutMapping("/{courseId}/student/{studentId}")
	public ResponseEntity<?> enrollStudentToCourse(@PathVariable Long courseId, 
				@PathVariable Long studentId) throws Exception{
		
		courseService.enrollStudentToCourse(studentId, courseId);
		
		return ResponseEntity.status(HttpStatus.OK).body("Successfully added student with ID "
				+ studentId + " to course with ID: "+ courseId);
	}
}
