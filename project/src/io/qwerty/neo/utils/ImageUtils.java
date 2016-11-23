package io.qwerty.neo.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ImageUtils {

	public static BufferedImage cropImage(BufferedImage i, int x, int y, int width, int height){		
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = bi.getGraphics();
		
		g.drawImage(i, -x, -y, width, height, null);
		
		return bi;
	}
	
	public static BufferedImage[] getSprites(BufferedImage image, Color seperator){
		int w = 0;
		int h = 0;
		int height = 0;
		int width = 0;
		
		LinkedList<BufferedImage> images = new LinkedList<BufferedImage>();
		
		while(w!=image.getWidth()||h!=image.getHeight()){
			width = 0;
			height = 0;
			for(w = w-0; w < image.getWidth()-1; w++){
				if(image.getRGB(w, height)==seperator.getRGB()){
					break;
				}
				width++;
			}
			for(h = h-0; h < image.getHeight()-1; h++){
				if(image.getRGB(w, h)==seperator.getRGB()){
					break;
				}
				height++;
			}
			if(width>0&&height>0)
				images.add(cropImage(image, w-width-1, h-height-1, width, height));
		}
		
		return (BufferedImage[])images.toArray();
	}
	
}
