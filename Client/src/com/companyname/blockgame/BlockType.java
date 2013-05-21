package com.companyname.blockgame;
import java.util.*;  
import java.awt.*;
import com.companyname.blockgame.BlockTypes.*;

public class BlockType {
	public int x1 = 5;
	public int y1 = 5;
	public static Map<String,Block> example = new HashMap<String,Block>();
	//public static Map example = new HashMap();
	
	public BlockType() {
		//example.put( "Wayne", new String( "Rooney" ));
		//System.out.println(x1);
		//example.put( "Wayne", 	new Bedrock(new Rectangle(x1 * Tile.tileSize + (int) Component.sX, y1 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.bedrock));

	}
	public void newBlock() {
		System.out.println(x1);
		example.put( "Wayne", 	new Bedrock(new Rectangle(x1 * Tile.tileSize + (int) Component.sX, y1 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.bedrock));

	}
}
