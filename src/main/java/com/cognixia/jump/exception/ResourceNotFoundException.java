package com.cognixia.jump.exception;

import java.time.LocalDateTime;

public class ResourceNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private static String message = ">>>>>> SORRY! >>>>>>>> " + System.lineSeparator() 
	+ "The Object Given/Entered Cannot Be Found or Does Not Exist !"; 

	public ResourceNotFoundException(LocalDateTime dateTime){
        super(message);
    }
}
