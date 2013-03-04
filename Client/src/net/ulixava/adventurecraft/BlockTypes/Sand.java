package net.ulixava.adventurecraft.BlockTypes;

import java.awt.Rectangle;

import net.ulixava.adventurecraft.Block;
//import net.ulixava.adventurecraft.Tile;

public class Sand extends Block{
	private static final long serialVersionUID = 1L;
	
	public Sand(Rectangle size, int[] id) {
		
		
		super(size, id);
		isPassable = true;
	}

}
