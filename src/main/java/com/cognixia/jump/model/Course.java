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
	
	
	@ManyToMany
	@JoinTable(
			name = "student_course",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name= "student_id"))
	private Set<Student> students = new HashSet<>();
	
	
	public Course() {
		
	}

	public Course(Long id, @NotBlank String name, String duration, String description, Set<Instructor> instructors, Set<Student> students) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.description = description;
		this.instructors= instructors;
		this.students = students;
	
	}
	
	
	//add instructor to one course;
	public void enrollInstructor(Instructor inst) {
		instructors.add(inst);
	}
	
	//add student to one course;
	public void enrollStudent(Student student) {
		students.add(student);
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
	

	public Set<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", duration=" + duration + ", description=" + description
				+ ", instructors=" + instructors + ", students=" + students + "]";
	}
	
	public String showCoursesInfo() {
		String a =System.lineSeparator() + "Course:  id=" + id + " "   
				+ "name=" + name + System.lineSeparator() 
				+ " duration=" + duration + System.lineSeparator()
				+ " description=" + description + " " + System.lineSeparator() 
				+ "instructors=" + instructors + "  "+ System.lineSeparator();
		
		return a; 
	}

	
	
	
	

}
