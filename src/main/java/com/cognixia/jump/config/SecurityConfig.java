package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognixia.jump.filters.JwtRequestFilter;
import com.cognixia.jump.service.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(myUserDetailsService);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/authenticate").permitAll() // --retrieve jwt token;
			.antMatchers(HttpMethod.POST, "/api/register").permitAll()    // --add roles;
			
			
			 //transaction will only be made by students;
			.antMatchers(HttpMethod.POST, "/api/transaction/**").hasRole("STUDENT")
			.antMatchers(HttpMethod.GET, "/api/transaction/all").hasRole("ADMIN")
			
			// only specific student have access to his account;
			.antMatchers(HttpMethod.GET, "/api/student/{studentId}").hasRole("STUDENT") 
			.antMatchers(HttpMethod.POST, "/api/student/**").hasAnyRole("ADMIN","INSTRUCTOR")
			
			
			// course information is available to the public;
			.antMatchers(HttpMethod.GET, "/api/course/courseinfo").permitAll()
			.antMatchers(HttpMethod.GET, "/api/course/getCourseList/{studentId}").hasRole("STUDENT")
			.antMatchers(HttpMethod.POST, "/api/course/add").hasAnyRole("INSTRUCTOR","ADMIN")
			.antMatchers(HttpMethod.PUT, "/api/course/**").hasRole("ADMIN")
			
			
			.antMatchers("/api/instructor/**").hasRole("ADMIN")
			
			.antMatchers( "/swagger-ui/index.html").permitAll()
			.anyRequest().authenticated()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
}
