package io.qwerty.neo.framework;

import io.qwerty.neo.objects.GameObject;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * <strong><em>Handler</em></strong><br /><br />
 * 
 * Holds all GameObjects and handles them.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	/**
	 * <strong><em>tick</em></strong><br /><br />
	 * 
	 * &emsp;Called every time a tick is called.<br />
	 * &emsp;This allows all the objects to update frequently<br />
	 * &emsp;and keep a smooth gameplay experience.<br />
	 * 
	 * @since NEO.1
	 */
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	
	/**
	 * <strong><em>render</em></strong><br /><br />
	 * 
	 * &emsp;Called whenever rendering is necessary. Usually more often than tick.
	 * 
	 * @param g - Used to render.
	 * @since NEO.1
	 */
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	/**
	 * <strong><em>addObject</em></strong><br /><br />
	 * 
	 * &emsp;Adds a GameObject at the back of the list.
	 * 
	 * @param obj - GameObject to add.
	 * @since NEO.1
	 */
	public void addObject(GameObject obj){
		object.add(obj);
	}
	
	/**
	 * <strong><em>addObject</em></strong><br /><br />
	 * 
	 * &emsp;Adds a GameObject at a specified area of the list.<br /><br />
	 * 
	 * <strong>Notes:</strong><br />
	 * &emsp;You're probably only going to need to use this to add an object<br />
	 * &emsp;to the front of the list.
	 * 
	 * @param obj - GameObject to add.
	 * @param i - Position in object list.
	 * @since NEO.1
	 */
	public void addObject(GameObject obj, int i){
		object.add(i, obj);
	}
	
	/**
	 * <strong><em>removeObject</em></strong><br /><br />
	 * 
	 * &emsp;Removes a GameObject.
	 * 
	 * @param obj - GameObject to remove.
	 * @since NEO.1
	 */
	public void removeObject(GameObject obj){
		object.remove(obj);
	}
	
}
