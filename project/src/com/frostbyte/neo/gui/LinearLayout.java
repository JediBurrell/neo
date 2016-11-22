package com.frostbyte.neo.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.frostbyte.neo.objects.GameObject;
import com.frostbyte.neo.utils.PositionUtils;

public class LinearLayout extends GUIObject{

	private int orientation = 1;
	
	public static int HORIZONTAL = 0;
	public static int VERTICAL = 1;
	
	public LinearLayout(int x, int y, int width, int height){
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	public void tick(){
		
		handler.tick();
		
	}
	
	public void render(Graphics g){
		super.render(g);
		
		handler.render(g);
		
	}
	
	public void addGUIObject(GUIObject object){	
		
		GUIObject lastObject = null;
		for(GameObject ngobj : handler.object){
			GUIObject obj = (GUIObject)ngobj;
			
			lastObject = obj;
		}
		
		if(lastObject==null){
			if(center==CENTER_VERTICAL||center==CENTER)
				object.setX((int)x+PositionUtils.centerX((int)width, (int)object.getWidth()));
			else{
				if(gravity[1]==LEFT){
					object.setX((int)x);
				}else{
					object.setX((int)x+(int)width-object.getWidth());
				}
			}
			if(center==CENTER_HORIZONTAL||center==CENTER)
				object.setY((int)y+PositionUtils.centerY((int)height, (int)object.getHeight()));
			else{
				if(gravity[0]==TOP){
					object.setY((int)y);
				}else{
					object.setY((int)y+(int)height-object.getHeight());
				}
			}
			
			handler.addObject(object);
			
			return;
		}
		
		if(orientation==HORIZONTAL){
			object.setX((int)x+lastObject.getX()+object.getWidth());
			object.setY((int)lastObject.getY());
		}else{
			if(center==CENTER||center==CENTER_HORIZONTAL){
				object.setX((int)x+PositionUtils.centerX((int)width, (int)object.getWidth()));
			}else{
				object.setX((int)lastObject.getX());
			}
			
			object.setY((int)y+lastObject.getY()+object.getHeight());
		}
		
		if(orientation==HORIZONTAL){
			if(center==CENTER_HORIZONTAL||center==CENTER)
				lastObject.setX((int)lastObject.getX()-(object.getWidth()/2));
			else
				lastObject.setX((int)lastObject.getX()-(object.getWidth()));
		}else{
			if(center==CENTER_VERTICAL||center==CENTER)
				lastObject.setY((int)lastObject.getY()-(object.getHeight()));
			else
				lastObject.setY((int)lastObject.getY()-(object.getHeight()));
		}
		
		handler.addObject(object);
	}
	
	@Override
	public boolean onHover(Rectangle r) {
		for(GameObject obj : handler.object)
			obj.onHover(r);
		
		return super.onHover(r);
	}
	
	@Override
	public boolean onTouch(Rectangle r) {
		for(GameObject obj : handler.object)
			obj.onTouch(r);
		
		return super.onTouch(r);
	}
	
	@Override
	public boolean onClick(Rectangle r) {
		for(GameObject obj : handler.object)
			obj.onClick(r);
		
		return super.onClick(r);
	}
	
	@Override
	public boolean onKeyPressed(KeyEvent e) {
		for(GameObject obj : handler.object)
			((GUIObject)obj).onKeyPressed(e);
		
		return false;
	}
	
	@Override
	public boolean onKeyReleased(KeyEvent e) {
		for(GameObject obj : handler.object)
			((GUIObject)obj).onKeyReleased(e);
		
		return false;
	}
	
	@Override
	public boolean onKeyTyped(KeyEvent e) {
		for(GameObject obj : handler.object)
			((GUIObject)obj).onKeyTyped(e);
		
		return false;
	}
	
}
