package com.cognixia.jump.exception;


public class AlreadyExistedException extends Exception {

	private static final long serialVersionUID = 1L;

	private static String message = ">>>>>> SORRY! >>>>>>>> " + System.lineSeparator() 
	+ "The Object Given/Entered Has Already Existed !"; 
	
	public AlreadyExistedException() {
		super(message);
	}
	
	

}
