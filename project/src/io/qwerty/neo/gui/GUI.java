package io.qwerty.neo.gui;

import io.qwerty.neo.framework.Handler;

import java.awt.Graphics;

public class GUI {

	private Handler handler;
	
	public GUI(){
		this.handler = new Handler();
	}
	
	public void tick(){
		
		handler.tick();
		
	}
	
	public void render(Graphics g){
		
		handler.render(g);
		
	}
	
	public void addGUIObject(GUIObject obj){
		handler.addObject(obj);
	}
	
}
