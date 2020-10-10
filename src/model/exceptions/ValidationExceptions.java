/**
 * 
 */
package model.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ALLAN
 *
 */
public class ValidationExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Map<String, String> errors = new HashMap<String, String>();
	
	public ValidationExceptions(String msg) {
		super(msg);
		
	}

	/**
	 * @return the errors
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

	public void addErrors(String fildName,String errorMessage) {
		errors.put(fildName, errorMessage);
	}
	
}
