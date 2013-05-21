package com.companyname.blockgame;

import java.awt.*;
import com.companyname.blockgame.BlockTypes.*;

public class Chunk {
	public Block[][] cBlock = new Block [Level.chunkSize][Level.chunkSize];
	public int chunkX = 0;
	public int chunkY = 0;
	
	
	public Chunk(int ChunkX, int ChunkY) {
		chunkX = ChunkX;
		chunkY = ChunkY;
		for(int y=0; y<cBlock.length;y++) {
			for(int x=0; x<cBlock[0].length;x++) {
				cBlock[x][y] = new Dirt(new Rectangle(x * Tile.tileSize + (chunkX * Level.chunkSize *Tile.tileSize), y * Tile.tileSize+ (chunkY * Level.chunkSize *Tile.tileSize), Tile.tileSize, Tile.tileSize), Tile.earth);
				
			}
		}
		for(int y=0; y<cBlock.length;y++) {
			for(int x=0; x<cBlock[0].length;x++) {
				
				if(Level.mapHeight[x +(chunkX *Level.chunkSize)][y+(chunkY *Level.chunkSize)] < -0.2) {
					cBlock[x][y] = new Water(new Rectangle(x * Tile.tileSize + (chunkX * Level.chunkSize *Tile.tileSize), y * Tile.tileSize+ (chunkY * Level.chunkSize *Tile.tileSize), Tile.tileSize, Tile.tileSize), Tile.water);
				}
			}
		}
	}
}
