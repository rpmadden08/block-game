package net.ulixava.adventurecraft.BlockTypes;

import java.awt.Rectangle;

import net.ulixava.adventurecraft.Block;
//import net.ulixava.adventurecraft.Tile;

public class Bedrock extends Block{
	private static final long serialVersionUID = 1L;
	
	public Bedrock(Rectangle size, int[] id) { 
		super(size, id);
		isPassable = false;
	}

}