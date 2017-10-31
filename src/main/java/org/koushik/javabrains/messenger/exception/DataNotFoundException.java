package org.koushik.javabrains.messenger.exception;

/**
 * Custom made exception class which extends Runtime exception class
 * This class is called whenever a custom/runtime exception is thrown
 * @author M1035998
 *
 */
public class DataNotFoundException extends RuntimeException {

	/**
	 * For some reason we have to provide this 
	 */
	private static final long serialVersionUID = -6328286661536343936L;

	/**
	 * @param message IS TAKEN AND PRINTS THAT MESSAGE
	 */
	public DataNotFoundException(String message) {
		super(message);
	}

	
}
