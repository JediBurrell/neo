package io.qwerty.neo.scene;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import io.qwerty.neo.Neo;

/**
 * <strong><em>SplashScene</em></strong><br /><br />
 * 
 * SplashScene is an extension of Scene, which allows you to add company attributions<br />
 * when launching.
 * 
 * @since NEO.2
 * @author Jedi Burrell
 */
public class SplashScene extends Scene{

	private int speed = 60;
	private LinkedList<BufferedImage> images = new LinkedList<BufferedImage>();
	private int current = 0;
	private int tick = 0;
	
	private Neo neo;
	
	/**
	 * <strong><em>SplashScene</em></strong><br /><br />
	 * 
	 * SplashScene is an extension of Scene, which allows you to add company attributions<br />
	 * when launching.
	 * 
	 * @since NEO.2
	 * @author Jedi Burrell
	 * 
	 * @param scene - The scene that comes next after the initial one is ended.
	 * @param neo - Your Game class.<sup>which needs to extend Neo</sup>
	 */
	public SplashScene(Scene scene, Neo neo) {
		super(scene, neo);
		this.neo = neo;
	}
	
	/**
	 * <strong><em>SplashScene</em></strong><br /><br />
	 * 
	 * SplashScene is an extension of Scene, which allows you to add company attributions<br />
	 * when launching.
	 * 
	 * @since NEO.2
	 * @author Jedi Burrell
	 * 
	 * @param neo - Your Game class.<sup>which needs to extend Neo</sup>
	 */
	public SplashScene(Neo neo) {
		super(neo);
		this.neo = neo;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(images.get(current), 0, 0, neo.width(), neo.height(), null);
	}
	
	/**
	 * <strong><em>setSpeed</em></strong><br /><br />
	 * 
	 * &emsp;Sets the speed. Default is 60.
	 * 
	 * @param int
	 * @since NEO.2
	 */
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	/**
	 * <strong><em>addSplash</em></strong><br /><br />
	 * 
	 * &emsp;Add a new image to be displayed in the SplashScene.
	 * 
	 * @param BufferedImage
	 * @since NEO.2
	 */
	public void addSplash(BufferedImage image){
		images.add(image);
	}
	
	@Override
	public void tick() {
		tick++;
		
		if(tick>=speed){
			current++;
			if(current>images.size()){
				current--;
				if(nScene != null)
					endScene();
			}
			tick = 0;
		}
	}

}
