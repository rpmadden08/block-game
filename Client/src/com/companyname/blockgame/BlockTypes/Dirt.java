package com.companyname.blockgame.BlockTypes;

import java.awt.Rectangle;

import com.companyname.blockgame.Block;
import com.companyname.blockgame.Tile;

public class Dirt extends Block{
	private static final long serialVersionUID = 1L;
	
	public Dirt(Rectangle size, int[] id) {
		
		
		super(size, id);
		isPassable = true;
		dropId = Tile.earthClump;
	}

}
