package com.companyname.blockgame;

import java.awt.*;

public class Day {
	/*12 - 17 is midday, 17 - 20 is dusk, 20 - 1 is start of night, 
	 * 1 - 4 is end night, 4 - 7 is dawn, 7 - 12 is morning
	 */
	
	public final int MAX_HOUR = 24;
	public int ticksPerHour = 180;
	public int ticksThisHour;
	public int currentHour;
	
	public float r;
	public float g;
	public float b;
	public float a;
	private Color tint;
	

	
	
	public Day() {
		r = 1.0f;
		g = 1.0f;
		b = 1.0f;
		a = 0.11f;
		
		tint = new Color(r, g, b, a);
		currentHour = 12;
		
		ticksThisHour = 0;
	}
	
	public void tick() {
		ticksThisHour++;
		if (ticksThisHour >= ticksPerHour) {
			if (currentHour >= 24) {
				currentHour = 1;
			} else {
				currentHour++;
			}
			ticksThisHour = 0;
			//System.out.println("red value: " + r + " currentHour: " + currentHour);
		}
		
		//magic numbers...
		if (currentHour == 12 && ticksThisHour == 0) {
			r = 1.0f; g = 1.0f; b = 1.0f; a = 0.11f;
		}
		
		if (currentHour >= 12 && currentHour < 17) {
			r -= 0.001; g -= 0.001;
		} else if (currentHour >= 17 &&  currentHour < 20) {
			
		} else if (currentHour >= 20 && currentHour <= 24) {
			r += 0.001; g += 0.001;
		} else if (currentHour >= 1 && currentHour < 4) {
			
		} else if (currentHour >= 4 && currentHour < 7) {
			
		} else if (currentHour >= 7 && currentHour < 12) {
			
		}
		
		tint = new Color(r, g, b, a);
	}
	
	public void render(Graphics gr) {
		gr.setColor(tint);
	    gr.fillRect (0, 0, Component.pixel.width, Component.pixel.height);
	}
}
