package io.qwerty.neo;

import io.qwerty.neo.scene.Scene;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

/**
 * <strong><em>Neo</em></strong><br /><br />
 * 
 * Neo is the Game's base.<br />
 * You'll need to extend this to get the game started.
 * 
 * @since NEO.1
 * @author Jedi Burrell
 */
public class Neo extends Canvas implements Runnable, ComponentListener, MouseListener, MouseMotionListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private int fps;
	protected Window window;
	protected Thread thread;
	public Scene scene;
	
	public Neo(Window window){
		this.window = window;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}

	@Override
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		@SuppressWarnings("unused")
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				fps = frames;
				frames = 0;
				updates = 0;
			}
		}
	}
	
	/**
	 * <strong><em>getFps</em></strong><br /><br />
	 * 
	 * &emsp;Returns the FPS.
	 * 
	 * @since NEO.2
	 */
	public int getFps(){
		
		return fps;
	}
	
	/**
	 * <strong><em>fps</em></strong><br /><br />
	 * 
	 * &emsp;Returns the FPS.
	 * 
	 * @since NEO.2
	 */
	public int fps(){
		
		return fps;
	}
	
	/**
	 * <strong><em>render</em></strong><br /><br />
	 * 
	 * &emsp;Main rendering function. Not to be overriden.
	 * 
	 * @since NEO.1
	 */
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		//////////////////////////////////
		
		//If resizable, it automatically scales the graphics.
		if(window.getShouldScale())
			g2d.scale((double)getWidth()/window.getWidth(), (double)getHeight()/window.getHeight());
		
		//Clears the screen, to change, call setColor.
		g.fillRect(0, 0, (int)window.getWidth(), (int)window.getHeight());
		
		scene.render(g);
		
		
		//////////////////////////////////
		
		g2d.dispose();
		g.dispose();
		bs.show();
	}
	
	/**
	 * <strong><em>tick</em></strong><br /><br />
	 * 
	 * &emsp;Main tick function, allowing all objects to be updated for a smooth experience.
	 * 
	 * @since NEO.1
	 */
	private void tick(){
		
		scene.tick();
		
	}
	
	/**
	 * <strong><em>init</em></strong><br /><br />
	 * 
	 * &emsp;This is the initialization function, it is to be overriden.<br />
	 * &emsp;It will be called before anything else happens,<br />
	 * &emsp;allows you to set variables before accounted for.
	 * 
	 * @since NEO.1
	 */
	protected void init(){
		
	}
	
	/**
	 * <strong><em>start</em></strong><br /><br />
	 * 
	 * &emsp;Starts the game.
	 * 
	 * @since NEO.1
	 */
	public synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	/**
	 * <strong><em>width</em></strong><br /><br />
	 * 
	 * &emsp;Returns screen initial width.
	 * 
	 * @return int
	 * @since NEO.1
	 */
	public int width(){
		
		return window.getWidth();
	}
	
	/**
	 * <strong><em>height</em></strong><br /><br />
	 * 
	 * &emsp;Returns screen initial height.
	 * 
	 * @return int
	 * @since NEO.1
	 */
	public int height(){
		
		return window.getHeight();
	}
	
	//////////////////////
	
	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(scene!=null)
			scene.onClick(new Rectangle((int)(e.getX()/((double)getWidth()/window.getWidth())), (int)(e.getY()/((double)getHeight()/window.getHeight())), 1, 1));
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(scene!=null)
			scene.onTouch(new Rectangle((int)(e.getX()/((double)getWidth()/window.getWidth())), (int)(e.getY()/((double)getHeight()/window.getHeight())), 1, 1));
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(scene!=null)
			scene.onTouch(new Rectangle((int)(e.getX()/((double)getWidth()/window.getWidth())), (int)(e.getY()/((double)getHeight()/window.getHeight())), 1, 1));
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(scene!=null)
			scene.onHover(new Rectangle((int)(e.getX()/((double)getWidth()/window.getWidth())), (int)(e.getY()/((double)getHeight()/window.getHeight())), 1, 1));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(scene!=null)
			scene.onKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(scene!=null)
			scene.onKeyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(scene!=null)
			scene.onKeyTyped(e);
	}
	
}
