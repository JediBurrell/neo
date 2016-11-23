package io.qwerty.neo.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextObject extends GUIObject{

	private String text = "";
	private boolean filter = false;
	private Font f;
	private Color color = new Color(255, 255, 255);
	
	public TextObject(float x, float y) {
		super(0, 0);
	}

	@Override
	public void render(Graphics g) {
		
		if(f==null)
			f = g.getFont();
		
		g.setColor(color);

		
		FontMetrics metrics = g.getFontMetrics(f);
		
		if(!filter){
		    int xx = (int) ((width - metrics.stringWidth(text)) / 2);
		    int yy = (int) (((height - metrics.getHeight()) / 2) + metrics.getAscent());
		    
		    g.setFont(f);
		    g.drawString(text, (int)x+xx, (int)y+yy);
		}else{
			int xx = (int) ((width - metrics.stringWidth(text.replaceAll(".", "*"))) / 2);
		    int yy = (int) (((height - metrics.getHeight()) / 2) + metrics.getAscent());
		    
		    g.setFont(f);
		    g.drawString(text.replaceAll(".", "*"), (int)x+xx, (int)y+yy);
		}
		
	}
	
	@Override
	public void addGUIObject(GUIObject object) {
		
	}
	
	public void setText(String t){
		this.text = t;
	}
	
	public void setFont(Font f){
		this.f = f;
	}
	
	public void setFont(String f) throws FileNotFoundException, FontFormatException, IOException{
		this.f = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.class.getResourceAsStream(f));
	}
	
	public void setFontIgnoreExceptions(String f){
		try {
			this.f = Font.createFont(Font.TRUETYPE_FONT, ClassLoader.class.getResourceAsStream(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setFontSize(float s){
		f = f.deriveFont(s);
	}
	
	public void setFontColor(Color color){
		this.color = color;
	}
	
	public void filter(boolean filter){
		this.filter = filter;
	}

}
