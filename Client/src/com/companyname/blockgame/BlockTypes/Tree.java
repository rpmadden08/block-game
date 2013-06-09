package com.companyname.blockgame.BlockTypes;

import java.awt.Rectangle;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.Block;
import com.companyname.blockgame.Collectible;
import com.companyname.blockgame.Component;

public class Tree extends Block{
	private static final long serialVersionUID = 1L;
	
	public Tree(Rectangle size, int id) {
		super(size, id);
		isPassable = false;
		dropId = LOG;
		
		imageXPos = 0;
		imageYPos = 4;
	}
	
	public void destroy(int x2, int y2) {
		Component.level.block[x2][y2] = new Grass(new Rectangle(x2 * TILE_SIZE, y2 * TILE_SIZE,TILE_SIZE, TILE_SIZE), GRASS);
		int collectibleID = Component.collectibles.size();
		Component.collectibles.add(new Collectible(x,y, Component.collectibleID, dropId));
		Component.collectibleID = collectibleID+1;
	}

}
