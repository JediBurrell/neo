package com.frostbyte.neo.utils;

/**
 * <strong><em>PositionUtils</strong></em><br /><br />
 * 
 * &emsp;Helps with positioning objects.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class PositionUtils {

	/**
	 * <strong><em>centerX</em></strong><br /><br />
	 * 
	 * &emsp;Returns the center x appropriate for that object.
	 * 
	 * @return float
	 * @since NEO.1
	 */
	public static float centerX(int parentWidth, int width){
		
		return (parentWidth/2)-(width/2);
	}
	
	/**
	 * <strong><em>centerY</em></strong><br /><br />
	 * 
	 * &emsp;Returns the center y appropriate for that object.
	 * 
	 * @return float
	 * @since NEO.1
	 */
	public static float centerY(int parentHeight, int height){
		
		return (parentHeight/2)-(height/2);
	}
	
}
