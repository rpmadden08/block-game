package com.companyname.blockgame.BlockTypes;

import java.awt.Rectangle;

import com.companyname.blockgame.Tile;
import com.companyname.blockgame.Block;
//import net.ulixava.adventurecraft.Tile;

public class Water extends Block{
	private static final long serialVersionUID = 1L;
	
	public Water(Rectangle size, int[] id) {
		super(size, id);
		isPassable = false;
		dropId = Tile.air;
	}

}