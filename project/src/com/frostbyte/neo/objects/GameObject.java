package com.frostbyte.neo.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.frostbyte.neo.framework.ObjectID;

/**
 * <strong><em>GameObject</em></strong><br /><br />
 * 
 * GameObject is an object that is meant to be interacted with<br />
 * It can be of any size, shape, color, and have certain personalities.<br />
 * Even the Player is an extension of GameObject.<br />
 * Everything rendered and ticked is a GameObject, including GUI.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public abstract class GameObject {
	
	protected float x, y;
	protected float width, height;
	protected ObjectID id;
	protected float velX = 0, velY = 0;
	protected boolean collidable = true;
	
	/**
	 * <strong><em>GameObject</em></strong>
	 * <br /><br />
	 * &emsp;Creates a GameObject.
	 * 
	 * @param x position
	 * @param y position
	 * @param id is the ObjectID to determine what an object is.
	 * @since NEO.1
	 */
	public GameObject(float x, float y, ObjectID id){
		
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	/*
	 * A list of the objects are passed through to allow checking of intersection.
	 * While you can avoid overriding tick, you shouldn't.
	 * It's okay not to call super if the object isn't a collidable,
	 * but the function checks to make sure it is.
	 * So it's good practice to do it regardless.
	 */
	/**
	 * <strong><em>tick</em></strong><br /><br />
	 * 
	 * &emsp;Called every time a tick is called.<br />
	 * &emsp;This allows the object to update frequently<br />
	 * &emsp;and keep a smooth gameplay experience.<br /><br />
	 * 
	 * <strong>Notes:</strong><br />
	 * &emsp;A list of the objects are passed through to allow checking of intersection.<br />
	 * &emsp;While you can avoid overriding tick, you shouldn't.<br />
	 * &emsp;It's okay not to call super if the object isn't a collidable,<br />
	 * &emsp;but the function checks to make sure it is.<br />
	 * &emsp;So it's good practice to do it regardless.
	 * 
	 * @param objects - LinkedList of all the objects stored in the Handler.
	 * @since NEO.1
	 */
	public void tick(LinkedList<GameObject> objects){
		if(collidable){
			for(GameObject object : objects){
				/*
				 * We should avoid checking for self collisions,
				 * because it will always return true.
				 */
				if(object!=this){
					// Make sure the boundaries aren't null to avoid an Exception.
					if(object.getBounds()!=null){
						if(object.getBounds().intersects(getBounds())){
							onCollision(object, object.getId());
						}
					}
				}
			}
		}
		
		x+=velX;
		y+=velY;
	}
	
	/*
	 * This probably shouldn't be abstract, but okay.
	 */
	/**
	 * <strong><em>onCollision</em></strong><br /><br />
	 * 
	 * &emsp;Called whenever the object collides with another object.
	 * 
	 * @param object - The object collided with.
	 * @param id - The ID of the object.
	 * @since NEO.1
	 */
	public abstract void onCollision(GameObject object, ObjectID id);

	/**
	 * <strong><em>render</em></strong><br /><br />
	 * 
	 * &emsp;Called whenever rendering is necessary. Usually more often than tick.
	 * 
	 * @param g - Used to render.
	 * @since NEO.1
	 */
	public abstract void render(Graphics g);
	/*
	 * To be overriden, getBounds() returns the boundaries of the GameObject.
	 * This allows collisions to be made by checking with the intersects function.
	 */
	/**
	 * <strong><em>getBounds</em></strong><br /><br />
	 * 
	 * &emsp;Returns boundaries to help detect collisions.
	 * 
	 * @return Rectangle
	 * @since NEO.1
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * <strong><em>getX</em></strong><br /><br />
	 * 
	 * &emsp;Returns x position.
	 * 
	 * @return float
	 * @since NEO.1
	 */
	public float getX(){
		return x;
	}
	
	/**
	 * <strong><em>getY</em></strong><br /><br />
	 * 
	 * &emsp;Returns y position.
	 * 
	 * @return float
	 * @since NEO.1
	 */
	public float getY(){
		return y;
	}
	
	/**
	 * <strong><em>getWidth</em></strong><br /><br />
	 * 
	 * &emsp;Returns width.
	 * 
	 * @return float
	 * @since NEO.1
	 */
	public float getWidth(){
		return width;
	}
	
	/**
	 * <strong><em>getHeight</em></strong><br /><br />
	 * 
	 * &emsp;Returns height.
	 * 
	 * @return float
	 * @since NEO.1
	 */
	public float getHeight(){
		return height;
	}
	
	/**
	 * <strong><em>setX</strong></em><br /><br />
	 * 
	 * &emsp;Set x position.
	 * 
	 * @param x
	 * @since NEO.1
	 */
	public void setX(float x){
		this.x = x;
	}
	
	/**
	 * <strong><em>setY</strong></em><br /><br />
	 * 
	 * &emsp;Set y position.
	 * 
	 * @param y
	 * @since NEO.1
	 */
	public void setY(float y){
		this.y = y;
	}
	
	/**
	 * <strong><em>getVelX</strong></em><br /><br />
	 * 
	 * &emsp;Get x velocity.
	 * 
	 * @return float
	 * @since NEO.1
	 */
	public float getVelX(){
		return velX;
	}
	
	/**
	 * <strong><em>getVelY</strong></em><br /><br />
	 * 
	 * &emsp;Get y velocity.
	 * 
	 * @return float
	 * @since NEO.1
	 */
	public float getVelY(){
		return velY;
	}
	
	/**
	 * <strong><em>setVelX</strong></em><br /><br />
	 * 
	 * &emsp;Set x velocity.
	 * 
	 * @param velX - X Velocity.
	 * @since NEO.1
	 */
	public void setVelX(float velX){
		this.velX = velX;
	}
	
	/**
	 * <strong><em>setVelY</strong></em><br /><br />
	 * 
	 * &emsp;Set y velocity.
	 * 
	 * @param velY - Y Velocity.
	 * @since NEO.1
	 */
	public void setVelY(float velY){
		this.velY = velY;
	}
	
	/**
	 * <strong><em>getId</strong></em><br /><br />
	 * 
	 * &emsp; Get ObjectID.
	 * 
	 * @return ObjectID
	 * @since NEO.1
	 */
	public ObjectID getId(){
		return id;
	}
	
	/**
	 * <strong><em>onHover</strong></em><br /><br />
	 * 
	 * &emsp; Called when object is hovered.
	 * 
	 * @return boolean
	 * @since NEO.1
	 */
	public boolean onHover(Rectangle r){
		
		return false;
	}
	
	/**
	 * <strong><em>onTouch</strong></em><br /><br />
	 * 
	 * &emsp; Called when the mouse is down on the object.
	 * 
	 * @return boolean
	 * @since NEO.1
	 */
	public boolean onTouch(Rectangle r){
		
		return false;
	}
	
	/**
	 * <strong><em>onClick</strong></em><br /><br />
	 * 
	 * &emsp; Called when the object has been clicked.
	 * 
	 * @return boolean
	 * @since NEO.1
	 */
	public boolean onClick(Rectangle r){
		
		return false;
	}
	
	/**
	 * <strong><em>setWidth</strong></em><br /><br />
	 * 
	 * &emsp; Sets object width.
	 * 
	 * @since NEO.1
	 */
	public void setWidth(float width){
		this.width = width;
	}
	
	/**
	 * <strong><em>setHeight</strong></em><br /><br />
	 * 
	 * &emsp; Sets object height.
	 * 
	 * @since NEO.1
	 */
	public void setHeight(float height){
		this.height = height;
	}
}
