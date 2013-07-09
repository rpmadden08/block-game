package com.companyname.blockgame;
import java.util.*;  
import java.awt.*;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.BlockTypes.*;

public class BlockTypess {
	public int x1 = 0;
	public int y1 = 0;
	public static Map<String,Block> example = new HashMap<String,Block>();
	//public static Map example = new HashMap();
	
	public BlockTypess() {
		//example.put( "Wayne", new String( "Rooney" ));
		//System.out.println(x1);
		//example.put( "Wayne", 	new Bedrock(new Rectangle(x1 * TILE_SIZE + (int) Component.sX, y1 * TILE_SIZE + (int) Component.sY, TILE_SIZE, TILE_SIZE), Tile.bedrock));

	}
	public void newBlock() {
		example.put( "4", 	new Bedrock(new Rectangle(x1 * TILE_SIZE, y1 * TILE_SIZE , TILE_SIZE, TILE_SIZE), BEDROCK));
		example.put( "1", 	new Grass(new Rectangle(x1 * TILE_SIZE, y1 * TILE_SIZE, TILE_SIZE, TILE_SIZE), GRASS));
		example.put( "5", 	new Water(new Rectangle(x1 * TILE_SIZE, y1 * TILE_SIZE, TILE_SIZE, TILE_SIZE), WATER));
	}
}