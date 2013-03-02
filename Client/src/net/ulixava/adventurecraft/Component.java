//Test 3

package net.ulixava.adventurecraft;

import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Component extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static int pixelSize = 2;
	public static double sX = 0, sY = 0;
	public static int dir = 0;
	
	
	public static Dimension realSize;
	public static Dimension size = new Dimension(700, 560);
	public static Dimension pixel = new Dimension(size.width / pixelSize, size.height / pixelSize);
	
	public static Point mse = new Point(0, 0);
	
	public static String name = "AWESOME GAME!";
	
	public static boolean isRunning = false;
	public static boolean isMoving = false;
	public static boolean isJumping = false;
	public static boolean isMouseLeft = false;
	public static boolean isMouseRight = false;
	
	private Image screen;
	
	public static Level level;
	public static Character character;
	public static Inventory inventory;
	public static Sky sky;
	public static ArrayList<Mob> mob = new ArrayList<Mob>();
	
	public Component() {
		setPreferredSize(size);
		addKeyListener(new Listening());
		addMouseListener(new Listening());
		addMouseMotionListener(new Listening());
		addMouseWheelListener(new Listening());
	}
	
	
	
	public void start() {
		
		new Tile();
		level = new Level();
		character = new Character(Tile.tileSize, Tile.tileSize*2);
		inventory = new Inventory();
		sky = new Sky();
		//mob.add(new Chicken(50,10,Tile.tileSize,Tile.tileSize * 2, Tile.mobChicken));
		
		isRunning = true;
		new Thread(this).start();
		
	}
	
	public void stop() {
		isRunning = false;
	}
	private static JFrame frame;
	public static void main(String args[]) {
		Component component = new Component();
		
		frame = new JFrame();
		frame.add(component);
		frame.pack();
		
		frame.setTitle(name);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		component.start();
	}
	
	public void tick() {
		
		character.tick();
		level.tick((int) sX, (int) sY, (pixel.width /Tile.tileSize) + 2, (pixel.height / Tile.tileSize) + 2);
		sky.tick();
		
		for(int i = 0; i < mob.toArray().length; i ++) {
			mob.get(i).tick();
		}
	}
	
	public void render() {
		Graphics g = screen.getGraphics();
		sky.render(g);
		
		level.render(g, (int) sX, (int) sY, (pixel.width /Tile.tileSize) + 2, (pixel.height / Tile.tileSize) + 2);
		character.render(g);
		inventory.render(g);
		
		for(int i = 0; i < mob.toArray().length; i ++) {
			mob.get(i).render(g);
		}
		
		g = getGraphics();
		
		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();
		
		
	}
	
	public void run() {
		screen = createVolatileImage(pixel.width, pixel.height);
		while(isRunning) {
			tick();
			render();
			
			try {
				Thread.sleep(5);
			} catch(Exception e) { }
		}
	}
}
