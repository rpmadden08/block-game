package com.companyname.blockgame.BlockTypes;

import java.awt.Rectangle;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.Block;
//import net.ulixava.adventurecraft.Tile;

public class Bedrock extends Block{
	private static final long serialVersionUID = 1L;
	
	public Bedrock(Rectangle size, int id) { 
		super(size, id);
		isPassable = false;
		dropId = AIR;
		
		imageXPos = 3;
		imageYPos = 0;
	}

}
