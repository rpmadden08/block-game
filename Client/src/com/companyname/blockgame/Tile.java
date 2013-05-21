package com.companyname.blockgame;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Tile {
	public static int tileSize = 16;
	public static int invLength = 8;
	public static int invHeight = 4;
	public static int invCellSize = 25;
	public static int invCell = 8;
	public static int invCellSpace = 4;
	public static int invBorderSpace = 4;
	public static int invItemBorder = 4;
	
	//Blocks
	public static final int[] air = {-1, -1};
	public static final int[] grass = {1, 0};	
	public static final int[] earth = {0, 7};
	public static final int[] sand = {2, 0};
	public static final int[] bedrock = {3, 0};
	public static final int[] water = {3, 7};
	public static final int[] tree = {0, 4};
	public static final int[] hole = {6, 7};
	
	
	//Tile Breaking Animations
	public static final int[] breaking1 = {0, 1};
	public static final int[] breaking2 = {1, 1};
	public static final int[] breaking3 = {2, 1};
	public static final int[] breaking4 = {3, 1};
	public static final int[] breaking5 = {4, 1};
	
	//Items
	public static final int[] grassSeed = {0, 2};
	public static final int[] earthClump = {1, 2};
	public static final int[] sandClump = {2, 2};
	public static final int[] log = {3, 2};
	public static final int[] plank = {4, 2};
	
	//Tools
	public static final int[] shovel = {1, 5};
	
	//Weapons
	public static final int[] sword = {0, 5};

	//Characters
	public static int[] mobChicken = {0, 12};
	public static int[] character = {0, 14};
	
	public static BufferedImage tileset_terrain;
	public static BufferedImage tile_cell;
	public static BufferedImage tile_select;
	public static BufferedImage tile_sword;
	
	public Tile() {
		try {
			Tile.tileset_terrain = ImageIO.read(new File("res/tileset_terrain.png"));
			Tile.tile_cell = ImageIO.read(new File("res/tile_cell.png"));
			Tile.tile_select = ImageIO.read(new File("res/tile_select.png"));
			Tile.tile_sword = ImageIO.read(new File("res/sword.png"));
	
		
		} catch(Exception e){
			
		}
		
	}
}
