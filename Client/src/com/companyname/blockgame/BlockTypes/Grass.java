package com.companyname.blockgame.BlockTypes;

import java.awt.Rectangle;

import com.companyname.blockgame.Block;
import com.companyname.blockgame.Collectible;
import com.companyname.blockgame.Component;
import com.companyname.blockgame.Tile;

public class Grass extends Block{
	private static final long serialVersionUID = 1L;
	
	public Grass(Rectangle size, int[] id) {
		
		
		super(size, id);
		isPassable = true;
		dropId = Tile.grassSeed;
	}
	
	public void destroy(int x2, int y2) {
		Component.level.block[x2][y2] = new Dirt(new Rectangle(x2 * Tile.tileSize, y2 * Tile.tileSize,Tile.tileSize, Tile.tileSize),Tile.earth);
		int collectibleID = Component.collectible.size();
		Component.collectible.add(new Collectible(x,y, Component.collectibleID, dropId));
		Component.collectibleID = collectibleID+1;
	}

}
