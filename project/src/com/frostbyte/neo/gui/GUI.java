package com.frostbyte.neo.gui;

import java.awt.Graphics;

import com.frostbyte.neo.framework.Handler;

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
