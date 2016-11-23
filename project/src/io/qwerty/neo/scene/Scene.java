package io.qwerty.neo.scene;

import io.qwerty.neo.Neo;
import io.qwerty.neo.framework.Audio;
import io.qwerty.neo.framework.Handler;
import io.qwerty.neo.gui.GUIObject;
import io.qwerty.neo.objects.GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * <strong><em>Scene</em></strong><br /><br />
 * 
 * Scene is the content manager for every screen.<br />
 * It carries the Handler, which carries the GameObjects.<br />
 * <br />
 * You could just have a Handler in Neo,<br />
 * but letting the Scene manage it allows you to easily<br />
 * switch between multiple screens without an overly<br />
 * complicated system.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public abstract class Scene {
	
	protected Handler handler;
	protected Scene nScene;
	private Neo neo;
	private Audio audio;
	protected Scene scene;
	
	/**
	 * <strong><em>Scene</em></strong><br /><br />
	 * 
	 * Scene is the content manager for every screen.<br />
	 * It carries the Handler, which carries the GameObjects.<br />
	 * <br />
	 * You could just have a Handler in Neo,<br />
	 * but letting the Scene manage it allows you to easily<br />
	 * switch between multiple screens without an overly<br />
	 * complicated system.
	 * 
	 * @param scene - The scene that comes next after the initial one is ended.
	 * @param neo - Your Game class.<sup>which needs to extend Neo</sup>
	 */
	public Scene(Scene scene, Neo neo){
		handler = new Handler();
		nScene = scene;
		this.setNeo(neo);
	}
	
	/**
	 * <strong><em>Scene</em></strong><br /><br />
	 * 
	 * Scene is the content manager for every screen.<br />
	 * It carries the Handler, which carries the GameObjects.<br />
	 * <br />
	 * You could just have a Handler in Neo,<br />
	 * but letting the Scene manage it allows you to easily<br />
	 * switch between multiple screens without an overly<br />
	 * complicated system.
	 * 
	 * @param neo - Your Game class.<sup>which needs to extend Neo</sup>
	 */
	public Scene(Neo neo){
		handler = new Handler();
		this.setNeo(neo);
	}
	
	/**
	 * <strong><em>render</em></strong><br /><br />
	 * 
	 * &emsp;Called whenever rendering is necessary. Usually more often than tick.
	 * 
	 * @param g - Used to render.
	 * @since NEO.1
	 */
	public abstract void render(Graphics g);
	
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
		
		handler.tick();
		
	}
	
	/**
	 * <strong><em>endScene</em></strong><br /><br />
	 * 
	 * &emsp;Ends this Scene. If nScene isn't null,<br />
	 * &emsp;it will set that as the scene.
	 * 
	 * @since NEO.1
	 */
	public void endScene(){
		getNeo().scene = nScene;
		getNeo().scene.onLoad();
	}
	
	/**
	 * <strong><em>toScene</em></strong><br /><br />
	 * 
	 * &emsp;Switches scene. Set this scene to nScene if it resumes to here,<br />
	 * &emsp;it will keep everything paused and ready to resume.
	 * 
	 * @param scene - Scene to switch to.
	 * @since NEO.1
	 */
	public void toScene(Scene scene){
		getNeo().scene = scene;
	}

	/**
	 * <strong><em>getAudio</em></strong><br /><br />
	 * 
	 * &emsp;Returns Audio.
	 * 
	 * @return Audio
	 * @since NEO.1
	 */
	public Audio getAudio() {
		return audio;
	}

	/**
	 * <strong><em>setAudio</em></strong><br /><br />
	 * 
	 * &emsp;Sets the current Audio(should be from Neo).
	 * 
	 * @param Audio
	 * @since NEO.1
	 */
	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	/**
	 * <strong><em>getNeo</em></strong><br /><br />
	 * 
	 * &emsp;Returns Neo.
	 * 
	 * @return Neo
	 * @since NEO.1
	 */
	public Neo getNeo() {
		return neo;
	}

	/**
	 * <strong><em>setNeo</em></strong><br /><br />
	 * 
	 * &emsp;Sets the Neo.
	 * 
	 * @param Neo
	 * @since NEO.1
	 */
	public void setNeo(Neo neo) {
		this.neo = neo;
	}
	
	/**
	 * <strong><em>onLoad</em></strong><br /><br />
	 * 
	 * &emsp;Abstract method that should be called when everything is prepared.<br />
	 * &emsp;Usually only useful on first scene.
	 * 
	 * @since NEO.1
	 */
	public void onLoad(){
		
	}
	
	/**
	 * <strong><em>onMouse</strong></em><br /><br />
	 * 
	 * &emsp;Called when the mouse has an event.<br />
	 * &emsp;It's highly recommended you don't override it.
	 * 
	 * @return boolean
	 * @since NEO.1
	 */
	public boolean onHover(Rectangle r){
		for(GameObject obj : handler.object){
			obj.onHover(r);
		}
		
		return false;
	}
	
	/**
	 * <strong><em>onClick</strong></em><br /><br />
	 * 
	 * &emsp;Called when the mouse clicks.<br />
	 * &emsp;It's highly recommended you don't override it.
	 * 
	 * @return boolean
	 * @since NEO.1
	 */
	public boolean onClick(Rectangle r){
		for(GameObject obj : handler.object){
			obj.onClick(r);
		}
		
		return false;
	}
	
	/**
	 * <strong><em>onTouch</strong></em><br /><br />
	 * 
	 * &emsp;Called when the mouse clicks down.<br />
	 * &emsp;It's highly recommended you don't override it.
	 * 
	 * @return boolean
	 * @since NEO.1
	 */
	public boolean onTouch(Rectangle r){
		for(GameObject obj : handler.object){
			obj.onTouch(r);
		}
		
		return false;
	}
	
	/**
	 * <strong><em>onKeyPressed</strong></em><br /><br />
	 * 
	 * &emsp;Called when a key is pressed.
	 * 
	 * @return boolean
	 * @since NEO.1
	 */
	public boolean onKeyPressed(KeyEvent e){
		for(GameObject obj : handler.object){
			if(obj instanceof GUIObject)
				((GUIObject)obj).onKeyPressed(e);
		}
		
		return false;
	}
	
	/**
	 * <strong><em>onKeyReleased</strong></em><br /><br />
	 * 
	 * &emsp;Called when a key is released.
	 * 
	 * @return boolean
	 * @since NEO.1
	 */
	public boolean onKeyReleased(KeyEvent e){
		for(GameObject obj : handler.object){
			if(obj instanceof GUIObject)
				((GUIObject)obj).onKeyReleased(e);
		}
		
		return false;
	}
	
	/**
	 * <strong><em>onKeyTyped</strong></em><br /><br />
	 * 
	 * &emsp;Called when a key is typed.
	 * 
	 * @return boolean
	 * @since NEO.1
	 */
	public boolean onKeyTyped(KeyEvent e){
		for(GameObject obj : handler.object){
			if(obj instanceof GUIObject)
				((GUIObject)obj).onKeyTyped(e);
		}
		
		return false;
	}

}
