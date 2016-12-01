package io.qwerty.neo.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import io.qwerty.neo.Neo;
import io.qwerty.neo.framework.Handler;

/**
 * <strong><em>BasicMainMenuScene</em></strong><br /><br />
 * 
 * BasicMainMenuScene is an extension of Scene, which allows you to create a MainMenu with minimal effort.
 * 
 * @since NEO.2
 * @author Jedi Burrell
 */
public class BasicMainMenuScene extends Scene{

	private Neo neo;
	private Scene backgroundScene;
	private BufferedImage logo;
	
	public BasicMainMenuScene(Neo neo) {
		super(neo);
		this.neo = neo;
	}

	@Override
	public void render(Graphics g) {
		if(backgroundScene!=null)
			backgroundScene.render(g);
		
		if(logo!=null)
			g.drawImage(logo, neo.width()/2-logo.getWidth()/2,
					logo.getHeight()/2, logo.getWidth(), logo.getHeight(), null);
		
		handler.render(g);
	}
	
	/**
	 * <strong><em>setBackground</em></strong><br /><br />
	 * 
	 * Set the background to a Scene, allowing an interactive or animated background.
	 * 
	 * @since NEO.2
	 * @author Jedi Burrell
	 * 
	 * @param Scene
	 */
	public void setBackground(Scene scene){
		this.backgroundScene = scene;
	}
	
	/**
	 * <strong><em>setBackground</em></strong><br /><br />
	 * 
	 * Set the background to a BufferedImage, giving you a basic, but static background.
	 * 
	 * @since NEO.2
	 * @author Jedi Burrell
	 * 
	 * @param BufferedImage
	 */
	public void setBackground(BufferedImage backgroundImage){
		this.backgroundScene = new Scene(neo){
			
			@Override
			public void render(Graphics g) {
				g.drawImage(backgroundImage, 0, 0, neo.width(), neo.height(), null);
			}
			
		};
	}
	
	/**
	 * <strong><em>setBackground</em></strong><br /><br />
	 * 
	 * Set the background to a Color.
	 * 
	 * @since NEO.2
	 * @author Jedi Burrell
	 * 
	 * @param Color
	 */
	public void setBackground(Color color){
		this.backgroundScene = new Scene(neo){

			@Override
			public void render(Graphics g) {
				g.setColor(color);
				g.fillRect(0, 0, neo.width(), neo.height());
			}
			
		};
	}
	
	/**
	 * <strong><em>setLogo</em></strong><br /><br />
	 * 
	 * Set the logo to display. Does not render if not set.
	 * 
	 * @since NEO.2
	 * @author Jedi Burrell
	 * 
	 * @param BufferedImage
	 */
	public void setLogo(BufferedImage logo){
		this.logo = logo;
	}
	
	/**
	 * <strong><em>getHandler</em></strong><br /><br />
	 * 
	 * Gets Handler object to add and remove objects.
	 * 
	 * @since NEO.2
	 * @author Jedi Burrell
	 * 
	 * @return Handler
	 */
	public Handler getHandler(){

		return handler;
	}

}
