package io.qwerty.neo.framework;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * <strong><em>BufferedImageLoader</em></strong><br /><br />
 * 
 * Anything image loading.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class BufferedImageLoader {
	
	private BufferedImage image;
	
	/**
	 *<strong><em>loadImage</em></strong><br /><br />
	 * &emsp;Loads an image.
	 * @param path - Path to image <em>E.g.</em> <sub>/res/image.png</sub>
	 * @return BufferedImage <em>image</em>.
	 * @throws IOException if the image doesn't exist.
	 * @since NEO.1
	 */
	public BufferedImage loadImage(String path) throws IOException{
		image = ImageIO.read(getClass().getResourceAsStream(path));
		
		return image;
	}
	
	/**
	 * <strong><em>loadImageFromRes</em></strong><br /><br />
	 * &emsp;Loads an image. Though the image is required to be in /res/.<br />
	 * &emsp;Anything else will throw an IOException. It can be convenient<br />
	 * &emsp;if you keep all of your images in /res/.
	 * @param path - Path to image <em>E.g.</em> <sub>image.png</sub>
	 * @return BufferedImage <em>image</em>.
	 * @throws IOException if the image doesn't exist.
	 * @since NEO.1
	 */
	public BufferedImage loadImageFromRes(String path) throws IOException{
		image = ImageIO.read(getClass().getResourceAsStream("/res/" + path));
		
		return image;
	}
	
}
