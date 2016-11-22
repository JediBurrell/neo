package com.frostbyte.neo.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import com.frostbyte.neo.objects.GameObject;
import com.frostbyte.neo.utils.PositionUtils;

public class TextFieldObject extends GUIObject{

	private String text = "";
	private Font f;
	private Color color = new Color(255, 255, 255);
	private int caret;
	private boolean filter = false;
	
	public char[] keyList = {
			'a', 'b', 'c', 'd', 'e', 'f', 'g',
		'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
		'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
		'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
		'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
		'V', 'W', 'X', 'Y', 'Z', '0', '1', '2',
		'3', '4', '5', '6', '7', '8', '9', '!',
		'@', '#', '$', '%', '^', '&', '&', '*',
		'(', '(', '-', '_', '=', '+', '`', '~',
		',', '<', '.', '>', '/', '?', '[', '{',
		']', '}', ';', ':', '\'', '\"', ' '};
	
	BufferedImage main, hovered, touched;
	Color cMain, cHovered, cTouched;
	
	public TextFieldObject(float x, float y) {
		super(0, 0);
		setPadding(10);
		
		cMain = cHovered = cTouched = new Color(25, 25, 25);
	}

	@Override
	public void tick(LinkedList<GameObject> objects) {
		
		super.tick(objects);
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		if(f==null)
			f = g.getFont();
		
		g.setColor(color);

		
		FontMetrics metrics = g.getFontMetrics(f);
		
	    g.setFont(f);

	    g.setClip((int)x+(leftPadding*3), (int)y+topPadding, (int)(width-leftPadding)-(rightPadding*3)-(rightPadding*3), (int)height-topPadding-bottomPadding);
	    if(!filter)
	    	g.drawString(text, (int)x+(leftPadding*3)-Math.max((int)(metrics.stringWidth(text)-(width-leftPadding*6-rightPadding)), 0), (int)y+(int)((height - metrics.getHeight()) / 2) + metrics.getAscent());
	    else
	    	g.drawString(text.replaceAll(".", "*"), (int)x+(leftPadding*3)-Math.max((int)(metrics.stringWidth(text)-(width-leftPadding*6-rightPadding)), 0), (int)y+(int)((height - metrics.getHeight()) / 2) + metrics.getAscent());
	    
	    if(hasFocus){
	    	caret++;
			
			if(caret>190)
				caret = 0;
	    }
	    
	    if(caret>50&&hasFocus){
	    	g.setColor(color);
	    	if(!filter)
	    		g.fillRect((leftPadding*3)+Math.min((int)x+leftPadding+metrics.stringWidth(text), (int)(x+(width-leftPadding)-(rightPadding*3)-(rightPadding*3)-10)), (int)(y+PositionUtils.centerY((int)height, metrics.getAscent())), 10, metrics.getAscent());
	    	else
	    		g.fillRect((leftPadding*3)+Math.min((int)x+leftPadding+metrics.stringWidth(text.replaceAll(".", "*")), (int)(x+(width-leftPadding)-(rightPadding*3)-(rightPadding*3)-10)), (int)(y+PositionUtils.centerY((int)height, metrics.getAscent())), 10, metrics.getAscent());
	    }
	    
	    g.setClip(null);
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
	
	public void setBackgrounds(BufferedImage main, BufferedImage hovered, BufferedImage touched){
		
		this.main = main;
		this.hovered = hovered;
		this.touched = touched;
		bg = main;
		this.background = true;
		
	}
	
	public void setBackgrounds(Color main, Color hovered, Color touched){
		
		this.cMain = main;
		this.cHovered = hovered;
		this.cTouched = touched;
		bgColor = cMain;
		this.background = false;
		
	}
	
	@Override
	public boolean onHover(Rectangle r) {
		bgColor = cMain;
		bg = main;
		if(!r.intersects(getBounds()))
			return true;
		
		boolean hovered = r.intersects(getBounds());
		
		if(hovered){
			bgColor = cHovered;
			bg = this.hovered;
		}else{
			bgColor = cMain;
			bg = main;
		}
		
		return false;
	}
	
	@Override
	public boolean onTouch(Rectangle r) {
		if(!r.intersects(getBounds()))
			return true;
		
		boolean touching = r.intersects(getBounds());
		
		if(touching){
			bgColor = cTouched;
			bg = touched;
		}else{
			bgColor = cMain;
			bg = main;
		}
			
		return false;
	}
	
	@Override
	public boolean onClick(Rectangle r) {
		super.onClick(r);
		
		if(!hasFocus)
			caret = 0;
		
		bgColor = cMain;
		bg = main;
		
		if(!r.intersects(getBounds()))
			return true;
		
		bgColor = cHovered;
		bg = hovered;
		
		return false;
	}
	
	@Override
	public boolean onKeyPressed(KeyEvent e) {
		if(!hasFocus)
			return true;
		
		if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
			if (text != null && text.length() > 0)
			      text = text.substring(0, text.length()-1);
		
		return false;
	}
	
	@Override
	public boolean onKeyTyped(KeyEvent e) {
		if(!hasFocus)
			return true;
		
		for(char key : keyList)
			if(key==e.getKeyChar())
				text = text + e.getKeyChar();
		
		return false;
	}
	
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}
	
	public String getText(){
		
		return text;
	}
	
	public void filter(boolean filter){
		this.filter = filter;
	}

}
