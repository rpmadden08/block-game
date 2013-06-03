package com.companyname.blockgame;
import java.util.*;  
import java.awt.*;
import com.companyname.blockgame.BlockTypes.*;

public class BlockType {
	public int x1 = 0;
	public int y1 = 0;
	public static Map<String,Block> example = new HashMap<String,Block>();
	//public static Map example = new HashMap();
	
	public BlockType() {
		//example.put( "Wayne", new String( "Rooney" ));
		//System.out.println(x1);
		//example.put( "Wayne", 	new Bedrock(new Rectangle(x1 * Tile.tileSize + (int) Component.sX, y1 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.bedrock));

	}
	public void newBlock() {
		
		example.put( "30", 	new Bedrock(new Rectangle(x1 * Tile.tileSize, y1 * Tile.tileSize , Tile.tileSize, Tile.tileSize), Tile.bedrock));
		example.put( "10", 	new Grass(new Rectangle(x1 * Tile.tileSize, y1 * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.grass));
		example.put( "37", 	new Water(new Rectangle(x1 * Tile.tileSize, y1 * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.water));
//		example.put( "30", 	new Bedrock(new Rectangle(x1 * Tile.tileSize + (int) Component.sX, y1 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.bedrock));
//		example.put( "10", 	new Grass(new Rectangle(x1 * Tile.tileSize + (int) Component.sX, y1 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.grass));
//		example.put( "37", 	new Water(new Rectangle(x1 * Tile.tileSize + (int) Component.sX, y1 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.water));

	}
}
