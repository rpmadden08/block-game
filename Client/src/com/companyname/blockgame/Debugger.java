package com.companyname.blockgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import static com.companyname.blockgame.Constants.*;

public class Debugger {
	private static int NUM_FRAMES_USED_FOR_SAMPLE_MEAN = 10;
	private static long MAX_STATS_INTERVAL = 1000000000L;	//how often to record stats
	private long statsInterval = 0L;    // in ns
	   
	public static boolean isDebugging = false;
	private long totalElapsedTime = 0L;
	private long prevStatsTime;
	private long gameStartTime;
	private int timeSpentInGame = 0;    // in seconds

	public static long framesSkipped = 0L;
	private long frameCount = 0;
	private double fpsStore[];
	private long statsCount = 0;
	public static double averageFPS = 0.0;

	private long totalFramesSkipped = 0L;
	private double upsStore[];
	public static double averageUPS = 0.0;

	public static DecimalFormat df = new DecimalFormat("0.##");  // 2 dp

	public static Font font;
	


	public Debugger() {
		font = new Font("MONOSPACED", Font.PLAIN, 12);
		
		/* stats */
		fpsStore = new double[NUM_FRAMES_USED_FOR_SAMPLE_MEAN];
		upsStore = new double[NUM_FRAMES_USED_FOR_SAMPLE_MEAN];
		for (int i=0; i < NUM_FRAMES_USED_FOR_SAMPLE_MEAN; i++) {
			fpsStore[i] = 0.0;
			upsStore[i] = 0.0;
		}
	}
	
	public void start() {
		gameStartTime = java.lang.System.nanoTime();
		prevStatsTime = gameStartTime;
	}
	
	public void sleep() {
		
	}
	
	public void updateStats() {
		frameCount++;
		statsInterval += Sleeper.period;

		if (statsInterval >= MAX_STATS_INTERVAL) {     // record stats every MAX_STATS_INTERVAL
			long timeNow = java.lang.System.nanoTime();
			timeSpentInGame = (int) ((timeNow - gameStartTime)/1000000000L);  // ns --> secs

			long realElapsedTime = timeNow - prevStatsTime;   // time since last stats collection
			totalElapsedTime += realElapsedTime;

			totalFramesSkipped += framesSkipped;

			double actualFPS = 0;     // calculate the latest FPS and UPS
			double actualUPS = 0;
			if (totalElapsedTime > 0) {
				actualFPS = (((double)frameCount / totalElapsedTime) * 1000000000L);
				actualUPS = (((double)(frameCount + totalFramesSkipped) / totalElapsedTime) 
						* 1000000000L);
			}

			// store the latest FPS and UPS
			fpsStore[ (int)statsCount%NUM_FRAMES_USED_FOR_SAMPLE_MEAN ] = actualFPS;
			upsStore[ (int)statsCount%NUM_FRAMES_USED_FOR_SAMPLE_MEAN ] = actualUPS;
			statsCount = statsCount+1;

			double totalFPS = 0.0;     // total the stored FPSs and UPSs
			double totalUPS = 0.0;
			for (int i=0; i < NUM_FRAMES_USED_FOR_SAMPLE_MEAN; i++) {
				totalFPS += fpsStore[i];
				totalUPS += upsStore[i];
			}

			if (statsCount < NUM_FRAMES_USED_FOR_SAMPLE_MEAN) { // obtain the average FPS and UPS
				averageFPS = totalFPS/statsCount;
				averageUPS = totalUPS/statsCount;
			}
			else {
				averageFPS = totalFPS/NUM_FRAMES_USED_FOR_SAMPLE_MEAN;
				averageUPS = totalUPS/NUM_FRAMES_USED_FOR_SAMPLE_MEAN;
			}

			framesSkipped = 0;
			prevStatsTime = timeNow;
			statsInterval = 0L;   // reset
		}
	}
	
	public void render(Graphics g) {
		if (isDebugging) {
			g.setColor(Color.white);
			g.setFont(font);
//			String totalMemory = String.valueOf(Runtime.getRuntime().totalMemory());
//			String maxMemory = String.valueOf(Runtime.getRuntime().maxMemory());
//			String memory = String.valueOf((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) / (1024* 1024));
			
	
			g.drawString("Average FPS/UPS: " + df.format(averageFPS) + ", " + df.format(averageUPS), 20, 25);
			g.drawString("Frame Count/Loss: " + frameCount + " / " + totalFramesSkipped, 20, 35);
			g.drawString("Time Spent In Game: " + timeSpentInGame + " secs", 20, 45);
			g.drawString("x/y: " + Component.sX + " / " + Component.sY, 20, 55);
			g.drawString("Character Tile x/y: " + Component.character.x / TILE_SIZE + "/" + Component.character.y/ TILE_SIZE, 20,65);
		}
	}
	
	public static void toggle() {
		if (isDebugging) {
			isDebugging = false;
		} else {
			isDebugging = true;
		}
	}
}
