package com.companyname.blockgame.BlockTypes;

import java.awt.Rectangle;

import com.companyname.blockgame.Block;
import com.companyname.blockgame.Collectible;
import com.companyname.blockgame.Component;
import com.companyname.blockgame.Tile;

public class Tree extends Block{
	private static final long serialVersionUID = 1L;
	
	public Tree(Rectangle size, int[] id) {
		
		
		super(size, id);
		isPassable = false;
		dropId = Tile.log;
	}
	
	public void destroy(int x2, int y2) {
		Component.level.block[x2][y2] = new Grass(new Rectangle(x2 * Tile.tileSize, y2 * Tile.tileSize,Tile.tileSize, Tile.tileSize),Tile.grass);
		int collectibleID = Component.collectible.size();
		Component.collectible.add(new Collectible(x,y, Component.collectibleID, dropId));
		Component.collectibleID = collectibleID+1;
	}

}