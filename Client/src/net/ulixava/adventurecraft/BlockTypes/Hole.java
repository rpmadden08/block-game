package net.ulixava.adventurecraft.BlockTypes;

import java.awt.Rectangle;

import net.ulixava.adventurecraft.Tile;
import net.ulixava.adventurecraft.Block;
//import net.ulixava.adventurecraft.Tile;

public class Hole extends Block{
	private static final long serialVersionUID = 1L;
	
	public Hole(Rectangle size, int[] id) {
		super(size, id);
		isPassable = true;
		dropId = Tile.air;
	}

}
