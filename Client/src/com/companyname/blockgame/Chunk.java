package com.companyname.blockgame;

import java.awt.*;

import com.companyname.blockgame.BlockTypes.*;

public class Chunk {
	public Block[][] cBlock = new Block [Level.chunkSize][Level.chunkSize];
	public Block[][] cBlock2 = new Block [Level.chunkSize][Level.chunkSize];
	public int chunkX = 0;
	public int chunkY = 0;
	public int x = 0;
	public int y = 0;
	
	
	public Chunk(int ChunkX, int ChunkY) {
		chunkX = ChunkX;
		chunkY = ChunkY;
		x= chunkX * Level.chunkSize *Tile.tileSize;
		y= chunkY * Level.chunkSize *Tile.tileSize;
		
		for(int y=0; y<cBlock.length;y++) {
			for(int x=0; x<cBlock[0].length;x++) {
				cBlock[x][y] = new Grass(new Rectangle(x * Tile.tileSize + (chunkX * Level.chunkSize *Tile.tileSize), y * Tile.tileSize+ (chunkY * Level.chunkSize *Tile.tileSize), Tile.tileSize, Tile.tileSize), Tile.grass);
				cBlock2[x][y] = new Block(new Rectangle(x * Tile.tileSize + (chunkX * Level.chunkSize *Tile.tileSize), y * Tile.tileSize+ (chunkY * Level.chunkSize *Tile.tileSize), Tile.tileSize, Tile.tileSize), Tile.air);
				//cBlock[x][y].heightMap = Level.mapHeight[x+(chunkX * Level.chunkSize)][y+(chunkY * Level.chunkSize)];
			}
		}
//		for(int y=0; y<cBlock.length;y++) {
//			for(int x=0; x<cBlock[0].length;x++) {
//				if(chunkX == 0 && x == 0) {
//					cBlock2[x][y] = new Bedrock(new Rectangle(x * Tile.tileSize + (chunkX * Level.chunkSize *Tile.tileSize), y * Tile.tileSize+ (chunkY * Level.chunkSize *Tile.tileSize), Tile.tileSize, Tile.tileSize), Tile.bedrock);
//				}
//				if(chunkY == 0 && y == 0) {
//					cBlock2[x][y] = new Bedrock(new Rectangle(x * Tile.tileSize + (chunkX * Level.chunkSize *Tile.tileSize), y * Tile.tileSize+ (chunkY * Level.chunkSize *Tile.tileSize), Tile.tileSize, Tile.tileSize), Tile.bedrock);
//				}
//			}
//		}
		for(int y=0; y<cBlock.length;y++) {
			for(int x=0; x<cBlock[0].length;x++) {
				cBlock[x][y].heightMap = Level.mapHeight[x+(chunkX * Level.chunkSize)][y+(chunkY * Level.chunkSize)];
				//System.out.println(cBlock[x][y].heightMap);
				if(cBlock[x][y].heightMap < -0.1) {
					cBlock[x][y] = new Water(new Rectangle(x * Tile.tileSize + (chunkX * Level.chunkSize *Tile.tileSize), y * Tile.tileSize+ (chunkY * Level.chunkSize *Tile.tileSize), Tile.tileSize, Tile.tileSize), Tile.water);
				}
			}
		}
	}
	public Rectangle bounds() {
		return (new Rectangle((int) x ,(int) y ,Level.chunkSize *Tile.tileSize,Level.chunkSize*Tile.tileSize));
	  //return (new Rectangle(x + frameOffsetLeft,y + frameOffsetTop,frameWidth,frameHeight));
	}
	
	public void render(Graphics g) {
		
		if(bounds().contains(new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY))) {
			g.setColor(new Color(255,0,0, 255));
			g.drawRect ((int) x -(int)Component.sX,(int) y -(int)Component.sY,Level.chunkSize *Tile.tileSize,Level.chunkSize*Tile.tileSize);
		}
	}
		
}
