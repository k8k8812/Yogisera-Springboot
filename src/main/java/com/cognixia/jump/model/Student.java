package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

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
	
	@ManyToMany
	@JoinTable(
			name = "student_course",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name= "course_id"))
	private Set<Course> courses = new HashSet<>();
	

	public Student() {
		
	}


	public Student(Long id, @NotBlank String firstName, @NotBlank String lastName ) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		
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


	

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +  " ]";
	}
	
	
	

}
