package com.companyname.blockgame.BlockTypes;

import java.awt.Rectangle;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.Block;
import com.companyname.blockgame.Collectible;
import com.companyname.blockgame.Component;

public class Grass extends Block{
	private static final long serialVersionUID = 1L;
	
	public Grass(Rectangle size, int id) {
		
		
		super(size, id);
		isPassable = true;
		dropId = GRASS_SEED;
		
		imageXPos = 1;
		imageYPos = 0;
	}
	
	public void destroy(int x2, int y2) {
		Component.level.block[x2][y2] = new Dirt(new Rectangle((x2+(CHUNCK_SIZE * Component.level.chunkOffsetX)) * TILE_SIZE, (y2 +(CHUNCK_SIZE * Component.level.chunkOffsetY))* TILE_SIZE,TILE_SIZE, TILE_SIZE), DIRT);
		int collectibleID = Component.collectibles.size();
		Component.collectibles.add(new Collectible(x,y, Component.collectibleID, dropId));
		Component.collectibleID = collectibleID+1;
	}

}
