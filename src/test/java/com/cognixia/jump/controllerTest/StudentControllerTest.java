package com.cognixia.jump.controllerTest;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cognixia.jump.controller.StudentController;
import com.cognixia.jump.filters.JwtRequestFilter;
import com.cognixia.jump.model.Course;
import com.cognixia.jump.model.Student;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = StudentController.class)
@ActiveProfiles("test")
public class StudentControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private StudentService service;
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private MyUserDetailsService myUserService;
	@MockBean
	private JwtRequestFilter filter;
		 
	private List<Student> studentList;

	 @BeforeEach
	 void setUp() {
		 
		 this.studentList = new ArrayList<>();
		 this.studentList.add(new Student
				 (1L, "firstName", "lastName", new HashSet<Course>()));
		 this.studentList.add(new Student
				 (2L, "May", "Wednesday", new HashSet<Course>()));
		 this.studentList.add(new Student
				 (3L, "Swagger", "Springboot", new HashSet<Course>()));
		 
		 mockMvc = MockMvcBuilders.webAppContextSetup(context)
				 				.apply(springSecurity())
				 				.build();
				 			
}
	
	 @WithMockUser(value="ADMIN")
	 @Test
	 void shouldFetchAllStudents() throws Exception {
		 given(service.getAll()).willReturn(studentList);
		 
		 this.mockMvc.perform(get("/api/student/view/all"))
//				 .with(user("admin").password("admin").roles("ADMIN")))
		 				.andExpect(status().isOk())
		 				.andExpect(jsonPath("$.size()", is(studentList.size())));
		 
		 
	 }
	 
	 
	
}
