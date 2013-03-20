package com.companyname.blockgame;

import java.awt.*;
import java.util.*;

//import net.ulixava.adventurecraft.BlockTypes.Grass;
//import net.ulixava.adventurecraft.BlockTypes.Bedrock;
//import net.ulixava.adventurecraft.BlockTypes.Hole;
import com.companyname.blockgame.BlockTypes.*;

public class Level {
	public int worldW = 50, worldH = 50;
	public Block[][] block = new Block [worldW][worldH];
	
	public Level() {
//		for(int x=0; x<block.length;x++) {
//			for(int y=0; y<block[0].length;y++) {
//				block[x][y] = new Block(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.air);
//			}
//		}
//		generateLevel();
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				//block[x][y].id = Tile.earth;
				block[x][y] = new Grass(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.grass);
			}
		}
		
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				if(y == 10) {
					block[x][y] = new Hole(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.hole);
				}
			}
		}

		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				if(x == 0 || y == 0 || x == block.length-1 || y == block.length -1) {
					block[x][y] = new Bedrock(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.bedrock);
				}
			}
		}
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				Random rand = new Random();
				int  n = rand.nextInt(10) + 1;
				if(n == 1) {
					block[x][y] = new Tree(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.tree);
				}
			}
		}
//		block[9][9] = new Bedrock(new Rectangle(9 * Tile.tileSize + (int) Component.sX, 9 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.bedrock);
//		block[11][12] = new Dirt(new Rectangle(11 * Tile.tileSize + (int) Component.sX, 12 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.earth);
//		//block[10][12] = new Sand(new Rectangle(10 * Tile.tileSize + (int) Component.sX, 12 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.sand);
//		block[10][12] = new Tree(new Rectangle(10 * Tile.tileSize + (int) Component.sX, 12 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.tree);
	}
	
//	public void generateLevel() {
//		for(int y=0; y<block.length;y++) {
//			for(int x=0; x<block[0].length;x++) {
//				//block[x][y].id = Tile.earth;
//				block[x][y] = new Grass(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.air);
//			}
//		}
//
//		for(int y=0; y<block.length;y++) {
//			for(int x=0; x<block[0].length;x++) {
//				if(x == 1 || y == 1 || x == block.length-2 || y == block.length -2) {
//					block[x][y].id = Tile.bedrock;
//				}
//			}
//		}
//	}
	
	public void building(int camX, int camY, int renW, int renH) {
		if(Component.isMouseLeft || Component.isMouseRight) {
			for(int x=(camX /Tile.tileSize); x<(camX / Tile.tileSize) + renW;x++) {
				for(int y=(camY / Tile.tileSize); y<(camY / Tile.tileSize) + renH;y++) {
					if(x >= 0 && y >= 0 && x < worldW && y < worldH) {
						if(block[x][y].contains(new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY))) {
							block[x][y].isDigAnimationVisible = true;
							int sid[] = Inventory.invBar[Inventory.selected].id;
							if(Component.isMouseLeft) {
								if(block[x][y].id != Tile.bedrock && block[x][y].id != Tile.hole) {
									block[x][y].hitPoints --;
									if(block[x][y].hitPoints < 1) {
										block[x][y].destroy(x,y);										
									}
								}
								
							} else if (Component.isMouseRight) {
								if(block[x][y].id == Tile.hole) {
									
									if(sid == Tile.earthClump) {
										block[x][y] = new Dirt(new Rectangle(x * Tile.tileSize, y * Tile.tileSize,Tile.tileSize, Tile.tileSize),Tile.earth);
										Inventory.removeFromInventory(sid, 1);
									} else if(sid == Tile.grassSeed) {
										block[x][y] = new Grass(new Rectangle(x * Tile.tileSize, y * Tile.tileSize,Tile.tileSize, Tile.tileSize),Tile.grass);
										Inventory.removeFromInventory(sid, 1);
									} else if(sid == Tile.sandClump) {
										block[x][y] = new Sand(new Rectangle(x * Tile.tileSize, y * Tile.tileSize,Tile.tileSize, Tile.tileSize),Tile.sand);
										Inventory.removeFromInventory(sid, 1);
									} 
									
									
								}
							}
							
							break;
							
						}
						block[x][y].isDigAnimationVisible = false;
						
						
					}
					
				}
			}
		} 
	}
	
	public void tick(int camX, int camY, int renW, int renH) {
		if(!Inventory.isOpen) {
			building(camX, camY, renW, renH);
		}
		
	}
	
	public void render(Graphics g, int camX, int camY, int renW, int renH) {
		for(int x=(camX /Tile.tileSize); x<(camX / Tile.tileSize) + renW;x++) {
			for(int y=(camY / Tile.tileSize); y<(camY / Tile.tileSize) + renH;y++) {
				if(x >= 0 && y >= 0 && x < worldW && y < worldH) {
					block[x][y].render(g);
					
					if(block[x][y].id != Tile.air && block[x][y].id != Tile.bedrock && !Inventory.isOpen) {
						if(block[x][y].contains(new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY))) {
							g.setColor(new Color(255,255,255, 40));
							g.drawRect(block[x][y].x - camX, block[x][y].y - camY, block[x][y].width-1, block[x][y].height-1);
						}
					}
				}
			}
		}
	}
	//For overhead graphics...
	public void render2(Graphics g, int camX, int camY, int renW, int renH) {
		
	}
}