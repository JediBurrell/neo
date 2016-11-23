package io.qwerty.neo.exceptions;

/**
 * <strong><em>FirstRunException</strong></em><br /><br />
 * 
 * &emsp;If thrown, resources have not yet been created.<br />
 * &emsp;This isn't a bad thing, it just makes it easier<br />
 * &emsp;to recognize when the game is being run for the first time.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class FirstRunException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <strong><em>FirstRunException</strong></em><br /><br />
	 * 
	 * &emsp;If thrown, resources have not yet been created.<br />
	 * &emsp;This isn't a bad thing, it just makes it easier<br />
	 * &emsp;to recognize when the game is being run for the first time.
	 * 
	 * @since NEO.1
	 * @author Jedi Burrell
	 */
    public FirstRunException() {}

    /**
     * <strong><em>FirstRunException</strong></em><br /><br />
     * 
     * &emsp;If thrown, resources have not yet been created.<br />
     * &emsp;This isn't a bad thing, it just makes it easier<br />
     * &emsp;to recognize when the game is being run for the first time.
     * 
     * @since NEO.1
     * @author Jedi Burrell
     */
    public FirstRunException(String message)
    {
       super(message);
    }
}
