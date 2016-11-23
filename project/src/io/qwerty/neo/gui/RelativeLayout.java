package io.qwerty.neo.gui;

import io.qwerty.neo.objects.GameObject;
import io.qwerty.neo.utils.PositionUtils;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class RelativeLayout extends GUIObject{

	private int orientation = 1;
	
	public static int HORIZONTAL = 0;
	public static int VERTICAL = 1;
	
	public RelativeLayout(int x, int y){
		super(x, y);
	}
	
	public void tick(){
		
		handler.tick();
		
	}
	
	public void render(Graphics g){
		
		handler.render(g);
		
	}
	
	public void addGUIObject(GUIObject object){
		handler.addObject(object);
		
		GameObject lastObject = null;
		for(GameObject ngobj : handler.object){
			lastObject = ngobj;
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
				object.setY(PositionUtils.centerY((int)height, (int)object.getHeight()));
			else{
				if(gravity[0]==TOP){
					object.setY((int)y);
				}else{
					object.setY((int)y+(int)height-object.getHeight());
				}
			}
			
			return;
		}
		
		if(orientation==HORIZONTAL){
			object.setX((int)x+lastObject.getX()+object.getWidth());
		}else{
			object.setY((int)y+lastObject.getY()+object.getHeight());
		}
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
