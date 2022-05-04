package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	
	@Query(value="SELECT * FROM course c, student_course sc "
			+ "WHERE c.id=sc.course_id "
			+ "AND sc.student_id = ?1 ", 
			nativeQuery= true)
	List<Course> getCourseListByStudentId(Long studentId);

}
