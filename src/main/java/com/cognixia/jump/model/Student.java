package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="student_id")
	private Long id;
	
	@NotBlank
	@Column(nullable= false, columnDefinition="varchar(50) default 'N/A' ")
	private String firstName;
	
	@NotBlank
	@Column(nullable= false, columnDefinition="varchar(50) default 'N/A' ")
	private String lastName;
	
	@JsonIgnore
	@ManyToMany(mappedBy="students")
	private Set<Course> courses = new HashSet<>();
	

	public Student() {
		
	}


	public Student(Long id, @NotBlank String firstName, @NotBlank String lastName, Set<Course> courses ) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.courses = courses;
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Set<Course> getCourses() {
		return courses;
	}


	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	
	

	
	
	
	

}
