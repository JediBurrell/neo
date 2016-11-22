package com.frostbyte.neo.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.frostbyte.neo.objects.GameObject;

public class ButtonObject extends GUIObject{
	
	BufferedImage main, hovered, touched;
	Color cMain, cHovered, cTouched;
	
	public ButtonObject(int width, int height) {
		super(0, 0);
		this.width = width;
		this.height = height;
		cMain = cHovered = cTouched = new Color(25, 25, 25);
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		
		handler.render(g);
	}
	
	@Override
	public void tick(LinkedList<GameObject> objects) {
		// TODO Auto-generated method stub
		super.tick(objects);
		handler.tick();
	}
	
	@Override
	public void addGUIObject(GUIObject object) {
		object.setWidth(width);
		object.setHeight(height);
		
		object.setX((int)x);
		object.setY((int)y);
		
		handler.addObject(object);
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
	public void setX(float x) {
		super.setX(x);
		
		for(GameObject object : handler.object){
			object.setX((int)x);
			object.setY((int)y);
		}
	}
	
	@Override
	public void setY(float y) {
		super.setY(y);
		
		for(GameObject object : handler.object){
			object.setX((int)x);
			object.setY((int)y);
		}
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
		bgColor = cMain;
		bg = main;
		
		if(!r.intersects(getBounds()))
			return true;
		
		bgColor = cHovered;
		bg = hovered;
		
		return false;
	}
	
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

}
