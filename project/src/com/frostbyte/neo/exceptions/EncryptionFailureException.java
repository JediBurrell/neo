package com.frostbyte.neo.exceptions;

/**
 * <strong><em>EncryptionFailureException</strong></em><br /><br />
 * 
 * &emsp;If thrown, encryption failed.
 * &emsp;It's best not to ignore this.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class EncryptionFailureException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <strong><em>EncryptionFailureException</strong></em><br /><br />
	 * 
	 * &emsp;If thrown, encryption failed.
	 * &emsp;It's best not to ignore this.
	 * 
	 * @since NEO.1
	 * @author Jedi Burrell
	 */
    public EncryptionFailureException() {}

    /**
     * <strong><em>EncryptionFailureException</strong></em><br /><br />
     * 
     * &emsp;If thrown, encryption failed.
     * &emsp;It's best not to ignore this.
     * 
     * @since NEO.1
     * @author Jedi Burrell
     */
    public EncryptionFailureException(String message)
    {
       super(message);
    }
}
