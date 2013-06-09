package com.companyname.blockgame;

import java.awt.*;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.BlockTypes.*;

public class Chunk {
	public Block[][] cBlock = new Block [CHUNCK_SIZE][CHUNCK_SIZE];
	public Block[][] cBlock2 = new Block [CHUNCK_SIZE][CHUNCK_SIZE];
	public int chunkX = 0;
	public int chunkY = 0;
	public int x = 0;
	public int y = 0;
	
	
	public Chunk(int ChunkX, int ChunkY) {
		chunkX = ChunkX;
		chunkY = ChunkY;
		x= chunkX * CHUNCK_SIZE *TILE_SIZE;
		y= chunkY * CHUNCK_SIZE *TILE_SIZE;
		
		for(int y=0; y<cBlock.length;y++) {
			for(int x=0; x<cBlock[0].length;x++) {
				cBlock[x][y] = new Grass(new Rectangle(x * TILE_SIZE + (chunkX * CHUNCK_SIZE *TILE_SIZE), y * TILE_SIZE+ (chunkY * CHUNCK_SIZE *TILE_SIZE), TILE_SIZE, TILE_SIZE), GRASS);
				cBlock2[x][y] = new Block(new Rectangle(x * TILE_SIZE + (chunkX * CHUNCK_SIZE *TILE_SIZE), y * TILE_SIZE+ (chunkY * CHUNCK_SIZE *TILE_SIZE), TILE_SIZE, TILE_SIZE), AIR);
				//cBlock[x][y].heightMap = Level.mapHeight[x+(chunkX * CHUNCK_SIZE)][y+(chunkY * CHUNCK_SIZE)];
			}
		}
//		for(int y=0; y<cBlock.length;y++) {
//			for(int x=0; x<cBlock[0].length;x++) {
//				if(chunkX == 0 && x == 0) {
//					cBlock2[x][y] = new Bedrock(new Rectangle(x * TILE_SIZE + (chunkX * CHUNCK_SIZE *TILE_SIZE), y * TILE_SIZE+ (chunkY * CHUNCK_SIZE *TILE_SIZE), TILE_SIZE, TILE_SIZE), Tile.bedrock);
//				}
//				if(chunkY == 0 && y == 0) {
//					cBlock2[x][y] = new Bedrock(new Rectangle(x * TILE_SIZE + (chunkX * CHUNCK_SIZE *TILE_SIZE), y * TILE_SIZE+ (chunkY * CHUNCK_SIZE *TILE_SIZE), TILE_SIZE, TILE_SIZE), Tile.bedrock);
//				}
//			}
//		}
		for(int y=0; y<cBlock.length;y++) {
			for(int x=0; x<cBlock[0].length;x++) {
				cBlock[x][y].heightMap = Level.mapHeight[x+(chunkX * CHUNCK_SIZE)][y+(chunkY * CHUNCK_SIZE)];
				//System.out.println(cBlock[x][y].heightMap);
				if(cBlock[x][y].heightMap < -0.1) {
					cBlock[x][y] = new Water(new Rectangle(x * TILE_SIZE + (chunkX * CHUNCK_SIZE *TILE_SIZE), y * TILE_SIZE+ (chunkY * CHUNCK_SIZE *TILE_SIZE), TILE_SIZE, TILE_SIZE), WATER);
				}
			}
		}
	}
	public Rectangle bounds() {
		return (new Rectangle((int) x ,(int) y ,CHUNCK_SIZE *TILE_SIZE,CHUNCK_SIZE*TILE_SIZE));
	  //return (new Rectangle(x + frameOffsetLeft,y + frameOffsetTop,frameWidth,frameHeight));
	}
	
	public void render(Graphics g) {
		
		if(bounds().contains(new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY))) {
			g.setColor(new Color(255,0,0, 255));
			g.drawRect ((int) x -(int)Component.sX,(int) y -(int)Component.sY,CHUNCK_SIZE *TILE_SIZE,CHUNCK_SIZE*TILE_SIZE);
		}
	}
		
}
