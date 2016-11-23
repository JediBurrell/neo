package io.qwerty.neo.exceptions;

/**
 * <strong><em>ResourceNotFoundException</strong></em><br /><br />
 * 
 * &emsp;If thrown, resource most probably does not exist.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class ResourceNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <strong><em>ResourceNotFoundException</strong></em><br /><br />
	 * 
	 * &emsp;If thrown, resource most probably does not exist.
	 * 
	 * @since NEO.1
	 * @author Jedi Burrell
	 */
    public ResourceNotFoundException() {}

    /**
     * <strong><em>ResourceNotFoundException</strong></em><br /><br />
     * 
     * &emsp;If thrown, resource most probably does not exist.
     * 
     * @since NEO.1
     * @author Jedi Burrell
     */
    public ResourceNotFoundException(String message)
    {
       super(message);
    }
}
