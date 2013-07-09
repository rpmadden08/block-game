package com.companyname.blockgame;

import java.awt.*;

import com.companyname.blockgame.BlockTypes.*;
import static com.companyname.blockgame.Constants.*;

public class Chunk {
	public Block[][] cBlock = new Block [CHUNK_SIZE][CHUNK_SIZE];
	public Block[][] cBlock2 = new Block [CHUNK_SIZE][CHUNK_SIZE];
	public static float[][] mapHeight = new float[CHUNK_SIZE][CHUNK_SIZE];//To get elevations through perlin noise
	public int chunkX = 0;
	public int chunkY = 0;
	public int x = 0;
	public int y = 0;
	
	
	public Chunk(int ChunkX, int ChunkY) {
		chunkX = ChunkX;
		chunkY = ChunkY;
		x= chunkX * CHUNK_SIZE *TILE_SIZE;
		y= chunkY * CHUNK_SIZE *TILE_SIZE;
		
		//GET LEVEL HEIGHT
		for(int y=0; y<cBlock.length;y++) {
			for(int x=0; x<cBlock[0].length;x++) {
				mapHeight[x][y] = Component.level.perlin.Noise(4 * ((chunkY*CHUNK_SIZE)+y) / (float)Component.level.Size, 4 * ((chunkX*CHUNK_SIZE)+x) / (float)Component.level.Size, 0);
			}
		}
		//CREATE GRASS BLOCK
		for(int y=0; y<cBlock.length;y++) {
			for(int x=0; x<cBlock[0].length;x++) {
				cBlock[x][y] = new Grass(new Rectangle(x * TILE_SIZE + (chunkX * CHUNK_SIZE *TILE_SIZE), y * TILE_SIZE+ (chunkY * CHUNK_SIZE *TILE_SIZE), TILE_SIZE, TILE_SIZE), GRASS);
				cBlock2[x][y] = new Block(new Rectangle(x * TILE_SIZE + (chunkX * CHUNK_SIZE *TILE_SIZE), y * TILE_SIZE+ (chunkY * CHUNK_SIZE *TILE_SIZE), TILE_SIZE, TILE_SIZE), AIR);
				//cBlock[x][y].heightMap = Level.mapHeight[x+(chunkX * CHUNK_SIZE)][y+(chunkY * CHUNK_SIZE)];
			}
		}
		
		//CREATE BEDROCK (EDGE OF WORLD)
//		for(int y=0; y<cBlock.length;y++) {
//			for(int x=0; x<cBlock[0].length;x++) {
//				if(chunkX == 0 && x == 0) {
//					cBlock2[x][y] = new Bedrock(new Rectangle(x * TILE_SIZE + (chunkX * CHUNK_SIZE *TILE_SIZE), y * TILE_SIZE+ (chunkY * CHUNK_SIZE *TILE_SIZE), TILE_SIZE, TILE_SIZE), Tile.bedrock);
//				}
//				if(chunkY == 0 && y == 0) {
//					cBlock2[x][y] = new Bedrock(new Rectangle(x * TILE_SIZE + (chunkX * CHUNK_SIZE *TILE_SIZE), y * TILE_SIZE+ (chunkY * CHUNK_SIZE *TILE_SIZE), TILE_SIZE, TILE_SIZE), Tile.bedrock);
//				}
//			}
//		}
		
		//FIGURE OUT HEIGHT (WATER VS.GRASS
		for(int y=0; y<cBlock.length;y++) {
			for(int x=0; x<cBlock[0].length;x++) {
//				if(chunkX < 0 && chunkY < 0) {
//					cBlock[x][y].heightMap = Level.mapHeightLU[x+(Math.abs(chunkX) * CHUNK_SIZE)][y+(Math.abs(chunkY) * CHUNK_SIZE)];
//				} else if (chunkX < 0) {
//					cBlock[x][y].heightMap = Level.mapHeightL[x+(Math.abs(chunkX) * CHUNK_SIZE)][y+(chunkY * CHUNK_SIZE)];
//				} else if (chunkY < 0) {
//					cBlock[x][y].heightMap = Level.mapHeightU[x+(chunkX * CHUNK_SIZE)][y+(Math.abs(chunkY) * CHUNK_SIZE)];
//				} else {
//					cBlock[x][y].heightMap = Level.mapHeight[x+(chunkX * CHUNK_SIZE)][y+(chunkY * CHUNK_SIZE)];
//					//System.out.println(cBlock[x][y].heightMap);
//				}
				cBlock[x][y].heightMap = mapHeight[x][y];
				
				if(cBlock[x][y].heightMap < -0.1) {
					cBlock[x][y] = new Water(new Rectangle(x * TILE_SIZE + (chunkX * CHUNK_SIZE *TILE_SIZE), y * TILE_SIZE+ (chunkY * CHUNK_SIZE *TILE_SIZE), TILE_SIZE, TILE_SIZE), WATER);
				}
			}
		}
	}
	public Rectangle bounds() {
		return (new Rectangle((int) x ,(int) y ,CHUNK_SIZE *TILE_SIZE,CHUNK_SIZE*TILE_SIZE));
	  //return (new Rectangle(x + frameOffsetLeft,y + frameOffsetTop,frameWidth,frameHeight));
	}
	
	public void render(Graphics g) {
		
		if(bounds().contains(new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY))) {
			g.setColor(new Color(255,0,0, 255));
			g.drawRect ((int) x -(int)Component.sX,(int) y -(int)Component.sY,CHUNK_SIZE *TILE_SIZE,CHUNK_SIZE*TILE_SIZE);
		}
	}
		
}