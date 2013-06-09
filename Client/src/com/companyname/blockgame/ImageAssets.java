package com.companyname.blockgame;

import java.awt.image.*;

import javax.imageio.*;

import java.io.*;

public class ImageAssets {

	public static BufferedImage TERRAIN_IMAGE;
	public static BufferedImage CELL_IMAGE;
	public static BufferedImage SELECT_IMAGE;
	public static BufferedImage SWORD_IMAGE;
	
	public ImageAssets() {
		try {
			TERRAIN_IMAGE = ImageIO.read(new File("res/tileset_terrain.png"));
			CELL_IMAGE = ImageIO.read(new File("res/tile_cell.png"));
			SELECT_IMAGE = ImageIO.read(new File("res/tile_select.png"));
			SWORD_IMAGE = ImageIO.read(new File("res/sword.png"));
		} catch(Exception e){
			System.out.println("Failed to load image with exception: " + e);
		}
		
	}
}
