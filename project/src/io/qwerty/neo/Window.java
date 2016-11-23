package io.qwerty.neo;

import io.qwerty.neo.exceptions.UnchangableException;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * <strong><em>Window</em></strong><br /><br />
 * 
 * Window is the Game's content holder.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class Window {

	protected boolean resizable = false;
	protected String title = "New Game";
	protected int width, height;

	protected JFrame frame;
	protected Neo neo;
	
	/**
	 * <strong><em>newWindow</em></strong><br /><br />
	 * 
	 * &emsp;<em>It's suggested you don't use this. Try creating a new Window instead.</em><br /><br />
	 * &emsp;Creates a template window.
	 * 
	 * @return Window
	 * @since NEO.1
	 * @deprecated
	 */
	public Window newWindow(){
		
		return new Window(this.title);
	}
	
	/**
	 * <strong><em>Window</em></strong><br /><br />
	 * 
	 * &emsp;Create's a new Window.
	 * 
	 * @param title - Title of Window.
	 * @since NEO.1
	 */
	public Window(String title){
		this.title = title;
		
		frame = new JFrame(this.title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * <strong><em>setTitle</em></strong><br /><br />
	 * 
	 * &emsp;Renames Window.
	 * 
	 * @param title - Title of Window.
	 * @return Returns same Window.
	 * @since NEO.1
	 */
	public Window setTitle(String title){
		this.title = title;
		
		frame.setTitle(this.title);
		
		return this;
	}
	
	/**
	 * <strong><em>setSize</em></strong><br /><br />
	 * 
	 * &emsp;Changes Window size.
	 * 
	 * @param width - New width of Window.
	 * @param height - New height of Window.
	 * @return Returns same Window.
	 * @since NEO.1
	 */
	public Window setSize(int width, int height){
		this.width = width;
		this.height = height;
		
		frame.setSize(this.width, this.height);
		
		return this;
	}
	
	/**
	 * <strong><em>setSize</em></strong><br /><br />
	 * 
	 * &emsp;Changes Window size.
	 * 
	 * @param d - New dimensions of Window.
	 * @return Returns same Window.
	 * @since NEO.1
	 */
	public Window setSize(Dimension d){
		this.width = d.width;
		this.height = d.height;
		
		frame.setSize(d);
		
		return this;
	}
	
	/**
	 * <strong><em>setResizable</em></strong><br /><br />
	 * 
	 * &emsp;Sets Window's resizability.
	 * 
	 * @param resizable - Whether or not Window is resizable.
	 * @return Returns same Window.
	 * @since NEO.1
	 */
	public Window setResizable(boolean resizable){
		this.resizable = resizable;
		
		frame.setResizable(this.resizable);
		
		return this;
	}
	
	public Window setContent(Neo neo) throws UnchangableException{
		if(this.neo == null)
			this.neo = neo;
		else
			throw new UnchangableException();
		
		frame.add(this.neo);
		
		return this;
	}
	
	/**
	 * <strong><em>start</em></strong><br /><br />
	 * 
	 * &emsp;Start window.
	 * 
	 * @return Returns same Window.
	 * @since NEO.1
	 */
	public Window start(){
		frame.setVisible(true);
		
		return this;
	}
	
	//
	
	/**
	 * <strong><em>getWidth</em></strong><br /><br />
	 * 
	 * &emsp;Returns Window width.
	 * 
	 * @return Window width.
	 * @since NEO.1
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * <strong><em>getHeight</em></strong><br /><br />
	 * 
	 * &emsp;Returns Window height.
	 * 
	 * @return Window height.
	 * @since NEO.1
	 */
	public int getHeight() {
		return height;
	}
	
}
