package com.frostbyte.neo.framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * <strong><em>SpriteAnimation</em></strong><br /><br />
 * 
 * SpriteAnimation allows you to create custom animations<br >
 * using multiple images, and a custom speed.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class SpriteAnimation {
	
	private BufferedImage[] images;
	private int selectedImage = 0;
	private int speed = 10;
	private int ticker = 0;
	
	/**
	 * <strong><em>SpriteAnimation</em></strong><br /><br />
	 * 
	 * SpriteAnimation allows you to create custom animations<br >
	 * using multiple images, and a custom speed.
	 * 
	 * @param speed - Speed of which the animation is to go by.
	 * @param images - Images used in animation.
	 */
	public SpriteAnimation(int speed, BufferedImage... images){
		this.images = images;
		this.speed = speed;
	}
	
	/**
	 * <strong><em>SpriteAnimation</em></strong><br /><br />
	 * 
	 * SpriteAnimation allows you to create custom animations<br >
	 * using multiple images, and a custom speed.
	 * 
	 * @param images - Images used in animation.
	 */
	public SpriteAnimation(BufferedImage... images){
		this.images = images;
	}
	
	/**
	 * <strong><em>tick</em></strong><br /><br />
	 * 
	 * &emsp;Called every time a tick is called.<br />
	 * &emsp;This allows all the objects to update frequently<br />
	 * &emsp;and keep a smooth gameplay experience.<br />
	 * 
	 * @since NEO.1
	 */
	public void tick(){
		ticker++;
		
		if(ticker>=speed){
			if(selectedImage<images.length-1)
				selectedImage++;
			else
				selectedImage = 0;
			ticker = 0;
		}
	}
	
	/**
	 * <strong><em>draw</em></strong><br /><br />
	 * 
	 * &emsp;Draw the animation.
	 * 
	 * @param g - Graphics used to draw image.
	 * @param x - Position x.
	 * @param y - Position y.
	 * @param w - Width.
	 * @param h - Height.
	 * @since NEO.1
	 */
	public void draw(Graphics g, float x, float y, float width, float height){
		g.drawImage(images[selectedImage], (int)x, (int)y, (int)width, (int)height, null);
	}
	
	/**
	 * <strong><em>getCurrentImage</em></strong><br /><br />
	 * 
	 * &emsp;Get current image.
	 * 
	 * @return BufferedImage
	 * @since NEO.1
	 */
	public BufferedImage getCurrentImage(){
		
		return images[selectedImage];
	}
	
	/**
	 * <strong><em>setSpeed</em></strong><br /><br />
	 * 
	 * &emsp;Set animation speed.
	 * 
	 * @param speed
	 * @since NEO.1
	 */
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	/**
	 * <strong><em>getSpeed</strong></em><br /><br />
	 * 
	 * &emsp;Get animation speed.
	 * 
	 * @return int
	 * @since NEO.1
	 */
	public int getSpeed(){
		
		return speed;
	}

}
