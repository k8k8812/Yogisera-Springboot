package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>{
	
	@Query(value = "SELECT * FROM instructor i WHERE i.first_name=?1 AND i.last_name=?2", 
			nativeQuery= true)
	Optional<Instructor> findByName(String firstName, String lastName);
}
