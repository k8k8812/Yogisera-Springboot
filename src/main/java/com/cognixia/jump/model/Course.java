package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="course_id")
	private Long id;
	
	@NotBlank
	@Column(nullable= false, columnDefinition="varchar(50) default 'N/A' ")
	private String name;
	
	@Column(nullable = false, columnDefinition="varchar(50) default '1hr' ")
	private String duration;
	
	@Column(columnDefinition="varchar(255) default 'This is a course description' ")
	private String description;
	
	@ManyToMany
	@JoinTable(
			name = "instructor_course",
			joinColumns = @JoinColumn(name="course_id"),  
			inverseJoinColumns = @JoinColumn(name="instructor_id"))
	private Set<Instructor> instructors = new HashSet<>();
	
	@ManyToMany(mappedBy="courses")
	private Set<Student> students = new HashSet<>();
	
	
	public Course() {
		
	}

	public Course(Long id, @NotBlank String name, String duration, String description, Set<Instructor> instructors) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.description = description;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Courses [id=" + id + ", name=" + name + ", duration=" + duration + ", description=" + description
				+ " ]";
	}
	
	
	

}
