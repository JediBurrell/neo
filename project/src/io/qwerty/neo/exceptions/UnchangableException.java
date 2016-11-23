package io.qwerty.neo.exceptions;

/**
 * <strong><em>UnchangableException</strong></em><br /><br />
 * 
 * &emsp;If thrown, the value has already been set, and is final.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class UnchangableException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * <strong><em>UnchangableException</strong></em><br /><br />
     * 
     * &emsp;If thrown, the value has already been set, and is final.
     * 
     * @since NEO.1
     */
    public UnchangableException() {}

    /**
     * <strong><em>UnchangableException</strong></em><br /><br />
     * 
     * &emsp;If thrown, the value has already been set, and is final.
     * 
     * @param message
     * @since NEO.1
     */
    public UnchangableException(String message)
    {
       super(message);
    }
}
