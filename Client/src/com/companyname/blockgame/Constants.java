package com.companyname.blockgame;

import com.companyname.blockgame.Items.*;

public class Constants {
	//block constants
	public static final int TILE_SIZE = 16;
	
	//inventory constants
	public static final int INVENTORY_LENGTH = 8;
	public static final int INVENTORY_HEIGHT = 4;
	public static final int INVENTORY_CELL_SIZE = 25;
	public static final int INVENTORY_CELL = 8;
	public static final int INVENTORY_CELL_SPACE = 4;
	public static final int INVENTORY_BORDER_SPACE = 4;
	public static final int INVENTORY_ITEM_BORDER = 4;
	
	//level constants
	public static final int CHUNK_SIZE = 20;
	public static final int NUMBER_OF_CHUNKS_ON_ONE_SIDE = 3;
	
	/* convert these to enums? */
	//game states (might convert to an enum)
	public static final int MAIN_STATE = 0;
	public static final int INVENTORY_STATE = 1;
	public static final int MAIN_MENU_STATE = 2;
	
	//Blocks
	public static final int AIR = 0;
	public static final int GRASS = 1;
	public static final int DIRT = 2;
	public static final int SAND = 3;
	public static final int BEDROCK = 4;
	public static final int WATER = 5;
	public static final int TREE = 6;
	public static final int HOLE = 7;
	
	//Animations
	public static final int[][] BREAKING_ANIMATION = {{0,1}, {1, 1}, {2, 1}, {3, 1}, {4, 1}};
	
	//Items
	public static final int NONE = 0;
	public static final int EMPTY = 0;
	public static final int GRASS_SEED = 1;
	public static final int EARTH_CLUMP = 2;
	public static final int SAND_CLUMP = 3;
	public static final int LOG = 4;
	public static final int PLANK = 5;
	
	public static final int SHOVEL = 6;
	
	public static final int SWORD = 7;
	
	/* getObjectFromId functions */
	public static Item getItemFromItemId(int id) {
		switch(id) {
		case GRASS_SEED:
			return new GrassSeed();
		case EARTH_CLUMP:
			return new EarthClump();
		case SAND_CLUMP:
			return new SandClump();
		case LOG:
			return new Log();
		case PLANK:
			return new Plank();
		case SHOVEL:
			return new Shovel();
		case SWORD:
			return new Sword();
		default:
			return new NoItem();
		}
	}
}
