
package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query(value = "SELECT * FROM student s WHERE s.first_name=?1 AND s.last_name=?2", 
			nativeQuery= true)
	Optional<Student> findByName(String firstName, String lastName);
}
