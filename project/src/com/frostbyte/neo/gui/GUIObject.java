package com.frostbyte.neo.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.frostbyte.neo.framework.Handler;
import com.frostbyte.neo.framework.ObjectID;
import com.frostbyte.neo.objects.GameObject;

public abstract class GUIObject extends GameObject{

	protected boolean background = false;
	protected boolean hasFocus = false;
	protected Handler handler;
	protected Color bgColor = new Color(0, 0, 0, 0);
	protected BufferedImage bg;
	protected int[] gravity = {2, 0};
	protected int center = 0;
	protected int topPadding, leftPadding,
			bottomPadding, rightPadding;
	
	public static int LEFT = 0, RIGHT = 1,
			TOP = 2, BOTTOM = 3;
	
	public static int CENTER_HORIZONTAL = 4,
			CENTER_VERTICAL = 5, CENTER = 6;
	
	public GUIObject(float x, float y) {
		super(x, y, ObjectID.gui);
		handler = new Handler();
	}

	@Override
	public void onCollision(GameObject object, ObjectID id) {
		
	}

	@Override
	public void render(Graphics g) {
		
		if(background){
			g.drawImage(bg, (int)x+(leftPadding), (int)y+(topPadding), (int)width-(leftPadding)-(rightPadding), (int)height-(topPadding)-(bottomPadding), null);
		}else{
			g.setColor(bgColor);
			g.fillRect((int)x+(leftPadding), (int)y+(topPadding), (int)width-(leftPadding)-(rightPadding), (int)height-(topPadding)-(bottomPadding));
		}
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle();
	}
	
	public void setPadding(int padding){
		this.topPadding = padding;
		this.leftPadding = padding;
		this.rightPadding = padding;
		this.bottomPadding = padding;
	}
	
	public void setLeftPadding(int leftPadding){
		this.leftPadding = leftPadding;
	}
	
	public void setTopPadding(int topPadding){
		this.topPadding = topPadding;
	}
	
	public void setBottomPadding(int bottomPadding){
		this.bottomPadding = bottomPadding;
	}
	
	public void setRightPadding(int rightPadding){
		this.rightPadding = rightPadding;
	}
	
	public int getLeftPadding(){
		return this.leftPadding;
	}
	
	public int getTopPadding(){
		return this.topPadding;
	}
	
	public int getBottomPadding(){
		return this.bottomPadding;
	}
	
	public int getRightPadding(){
		return this.rightPadding;
	}
	
	public void setBackground(Color bgColor){
		this.bgColor = bgColor;
		background = false;
	}
	
	public void setBackground(BufferedImage bg){
		this.bg = bg;
		background = true;
	}
	
	public void setGravity(int gravity){
		if(gravity<=BOTTOM){
			if(gravity<=RIGHT)
				this.gravity[1] = gravity;
			else
				this.gravity[0] = gravity;
		}else if(gravity<=CENTER){
		}
	}
	
	public void centerVertical(boolean b){
		if(b){
			if(center==CENTER_HORIZONTAL){
				center = CENTER;
			}else{
				center = CENTER_VERTICAL;
			}
		}else{
			if(center==CENTER){
				center = CENTER_HORIZONTAL;
			}else{
				center = 0;
			}
		}
	}

	public void centerHorizontal(boolean b){
		if(b){
			if(center==CENTER_VERTICAL){
				center = CENTER;
			}else{
				center = CENTER_HORIZONTAL;
			}
		}else{
			if(center==CENTER){
				center = CENTER_VERTICAL;
			}else{
				center = 0;
			}
		}
	}
	
	public void center(boolean b){
		if(b)
			center = CENTER;
		else
			center = 0;
	}
	
	public boolean hasFocus(){
		
		return hasFocus;
	}
	
	@Override
	public boolean onClick(Rectangle r) {
		if(r.intersects(getBounds()))
			hasFocus = true;
		else
			hasFocus = false;
		
		return super.onClick(r);
	}
	
	@Override
	public boolean onHover(Rectangle r) {
		// TODO Auto-generated method stub
		return super.onHover(r);
	}
	
	@Override
	public boolean onTouch(Rectangle r) {
		// TODO Auto-generated method stub
		return super.onTouch(r);
	}
	
	public boolean onKeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	public boolean onKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	public boolean onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		return false;
	}
	
	public abstract void addGUIObject(GUIObject object);
	
}
