package com.companyname.blockgame.BlockTypes;

import java.awt.Rectangle;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.Block;

public class Dirt extends Block{
	private static final long serialVersionUID = 1L;
	
	public Dirt(Rectangle size, int id) {
		
		
		super(size, id);
		isPassable = true;
		dropId = EARTH_CLUMP;
		
		imageXPos = 0;
		imageYPos = 7;
	}

}
