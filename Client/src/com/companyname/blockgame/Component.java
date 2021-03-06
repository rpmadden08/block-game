package com.companyname.blockgame;


import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.GameStates.*;



public class Component extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;

	private Thread animator;

	public static int maxPixelSize = 4;
	public static int minPixelSize = 1;
	public static int pixelSize = 2;
	public static int initialFrameWidth;
	public static int initialFrameHeight;
	public static int initialGameWidth = 800;
	public static int initialGameHeight = 600;
	public static SaveGame test = new SaveGame();
	
	public static double sX = 0, sY = 0;
	public static int cameraX= 0, cameraY = 0, cameraW = 300, cameraH = 200;

	public static Dimension size = new Dimension(initialGameWidth, initialGameHeight); //700, 560
	public static Dimension pixel = new Dimension(size.width / pixelSize, size.height / pixelSize);

	public static Point mse = new Point(0, 0);

	public static String name = "AWESOME GAME!";

	public static boolean isRunning = false;
	public static boolean isMouseLeft = false;
	public static boolean isMouseRight = false;
	
	public static GameState currentState;
	public static Sleeper sleeper;
	public static Debugger debugger;
	public static Level level;
	public static Character character;
	public static Inventory inventory;
	public static Weapon weapon;
	public static Day day;
	public static ArrayList<Mob> mob = new ArrayList<Mob>();
	public static ArrayList<Collectible> collectibles = new ArrayList<Collectible>();
	public static int collectibleID = 0;
	public static int mobID = 0;

	public Component() {
		setPreferredSize(size);
		addKeyListener(new Listening());
		addMouseListener(new Listening());
		addMouseMotionListener(new Listening());
		addMouseWheelListener(new Listening());
		setFocusable(true);
		requestFocus();

		debugger = new Debugger();
	}

	public void start() {
		currentState = new MainState();
		new ImageAssets();
		level = new Level();
		//level.saveChunk();
		character = new Character(24, TILE_SIZE*2);
		weapon = new Weapon(TILE_SIZE, TILE_SIZE, SWORD);
		inventory = new Inventory();
		day = new Day();
		//This is how I dropped collectibles and how I will also do mobs.  
//		int collectibleID = Component.collectible.size();
//		Component.collectible.add(new Collectible(x,y, Component.collectibleID, dropId));
//		Component.collectibleID = collectibleID+1;
		//This helps me find the location for the mob ID
//		int mobID2 = mob.size();
//		mob.add(new Chicken(450,480,24,TILE_SIZE * 2, Tile.mobChicken, mobID));
//		mobID = mobID2+1;
		
		
//		// screen resize stuff...
//		//needs to account for applets and sX/sY changing both through movement and screen resize...
		initialFrameWidth = frame.getWidth();
		initialFrameHeight = frame.getHeight();
//		
        frame.getRootPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	int diffWidth = frame.getWidth() - initialFrameWidth;
            	int diffHeight = frame.getHeight() - initialFrameHeight;
            	
            	size = new Dimension(initialGameWidth + diffWidth, initialGameHeight + diffHeight);
            	pixel = new Dimension(size.width / pixelSize, size.height / pixelSize);
            	
            	//sX = -(diffWidth / 3);
            	//sY = -(diffHeight / 3);
          
                //System.out.println("gameSize: " + size.width + ", " + size.height);
                //System.out.println("frameSize: " + frame.getWidth() + ", " + frame.getHeight());
            	
            	//size = new Dimension(700, 560); //700, 560
            	//pixel = new Dimension(size.width / pixelSize, size.height / pixelSize);
            }
        });
        
        
		isRunning = true;
		animator = new Thread(this);
		animator.start();
	}

	public void stop() {
		isRunning = false;
	}

	private static JFrame frame;

	public static void main(String args[]) {
		Component component = new Component();
		//GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		//GraphicsDevice vc=ge.getDefaultScreenDevice();
		
		frame = new JFrame();
		frame.add(component);
		frame.pack();
		
		frame.setMinimumSize(new Dimension(500, 560));
		frame.setTitle(name);
		frame.dispose();
		//frame.setUndecorated(true);
		frame.setResizable(true);
		//vc.setFullScreenWindow(frame);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	



		component.start();
	}

	private void renderToScreen() { 
		Graphics g;
		try {
			g = this.getGraphics();
			if ((g != null) && (currentState.dbImage != null))
				g.drawImage(currentState.dbImage, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
			g.dispose();
		}
		catch (Exception e) {
			System.out.println("Graphics context error: " + e);
		}
	}
	
	private void clearScreen() {
		if (currentState.dbImage == null) {
			currentState.dbImage = createImage(Component.size.width, Component.size.height);
			if (currentState.dbImage == null) {
				System.out.println("dbImage is null");
				return;
			}
			else
				currentState.dbg = currentState.dbImage.getGraphics();
		}
		
		currentState.dbg.setColor(Color.black);
		currentState.dbg.fillRect (0, 0, size.width, size.height);
	}
	
	public void run() {
		debugger.start();
		sleeper = new Sleeper();
		
		while(isRunning) {
			currentState.tick();
			clearScreen();
			currentState.renderToBuffer();
			renderToScreen();
			sleeper.sleep();
			debugger.updateStats();
		}
		System.exit(0);
	}
}
