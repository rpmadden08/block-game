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
	public Block[][] block2 = new Block [worldW][worldH];
	public float[][] mapHeight = new float[worldW][worldH]; 
	public Random rand = new Random();
	public Random rand2 = new Random();
	public Random seed = new Random();
	public int Size = worldW;
	private PerlinGenerator Perlin;
	
	public Level() {
//		for(int x=0; x<block.length;x++) {
//			for(int y=0; y<block[0].length;y++) {
//				block[x][y] = new Block(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.air);
//			}
//		}
//		generateLevel();
		
		
		
		
		
		long rgenseed = System.currentTimeMillis();
		Random rgen = new Random();
		rgen.setSeed(rgenseed);
		//System.out.println("Random number generator seed is " + rgenseed);
		
		Perlin = new PerlinGenerator((int) rgenseed);
		//Perlin = new PerlinGenerator(0);
		
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				block2[x][y] = new Block(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.air);				
			}
		}
		
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				if(x == 0 || y == 0 || x == block.length-1 || y == block.length -1) {
					block2[x][y] = new Bedrock(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.bedrock);
				}
			}
		}
		
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				//block[x][y].id = Tile.earth;
				
				block[x][y] = new Dirt(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.earth);
				//block[x][y].heightMap = Perlin.Noise(2 * y / (float)Size, 2 * x / (float)Size, 0);
				mapHeight[x][y] = Perlin.Noise(4 * y / (float)Size, 4 * x / (float)Size, 0);
			}
		}
		
		
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				if(mapHeight[x][y] > -0.15) {
					block[x][y] = new Grass(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.grass);
					
				//System.out.println(block[x][y].heightMap);
				}
				//System.out.println(block[x][y].heightMap);
			}
		}
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				if(mapHeight[x][y] < -0.2) {
					block[x][y] = new Water(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.water);
					
				
				}
			}
		}
		
		


		

		for(int y=0; y<block2.length;y++) {
			for(int x=0; x<block2[0].length;x++) {
				Random rand = new Random();
				int  n = rand.nextInt(10) + 1;
				if(n == 1 && block[x][y].id == Tile.grass && block2[x][y].id != Tile.bedrock) {
					block2[x][y] = new Tree(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.tree);
				}
			}
		}
		
		for(int y=1; y<block.length-1;y++) {
			for(int x=1; x<block[0].length-1;x++) {
				
				//if(block[x][y].heightMap > -5) {
					checkAutoTile(x, y);
				//}
			}
		}
		//block[14][20].id = Tile.air;
		//checkAutoTile(6, 2);
		System.out.println(block[6][2].id[0]+","+block[6][2].id[1]);

		
		//System.out.println(block[6][2].id[1]);
	}
	
	
	
	public void checkAutoTile(int x1, int y1) {
		if(block[x1][y1].baseId != Tile.earth) {
			boolean top = false;
			boolean left = false;
			boolean right = false;
			boolean bottom = false;
		
			if(block[x1][y1].baseId == block[x1][y1-1].baseId) {
				top = true;
			}
			if(block[x1][y1].baseId == block[x1-1][y1].baseId) {
				left = true;
			}
			if(block[x1][y1].baseId == block[x1+1][y1].baseId) {
				right = true;
			}
			if(block[x1][y1].baseId == block[x1][y1+1].baseId) {
				bottom = true;
			}
			
			if(top == false && left == false && right == false && bottom == false) {
				block[x1][y1].autoTile = 1;
			}
			if(top == false && left == false && right == true && bottom == false) {
				block[x1][y1].autoTile = 2;
			}
			if(top == true && left == false && right == false && bottom == false) {
				block[x1][y1].autoTile = 3;
			}
			if(top == true && left == false && right == true && bottom == false) {
				block[x1][y1].autoTile = 4;
			}
			if(top == false && left == true && right == false && bottom == false) {
				block[x1][y1].autoTile = 5;
			}
			if(top == false && left == true && right == true && bottom == false) {
				block[x1][y1].autoTile = 6;
			}
			if(top == true && left == true && right == false && bottom == false) {
				block[x1][y1].autoTile = 7;
			}
			if(top == true && left == true && right == true && bottom == false) {
				block[x1][y1].autoTile = 8;
			}
			if(top == false && left == false && right == false && bottom == true) {
				block[x1][y1].autoTile = 9;
			}
			if(top == false && left == false && right == true && bottom == true) {
				block[x1][y1].autoTile = 10;
			}
			if(top == true && left == false && right == false && bottom == true) {
				block[x1][y1].autoTile = 11;
			}
			if(top == true && left == false && right == true && bottom == true) {
				block[x1][y1].autoTile= 12;
			}
			if(top == false && left == true && right == false && bottom == true) {
				block[x1][y1].autoTile = 13;
			}
			if(top == false && left == true && right == true && bottom == true) {
				block[x1][y1].autoTile = 14;
			}
			if(top == true && left == true && right == false && bottom == true) {
				block[x1][y1].autoTile = 15;
			}
			if(top == true && left == true && right == true && bottom == true) {
				block[x1][y1].autoTile = 16;
			}	
		}
	}
	
	public void building(int camX, int camY, int renW, int renH) {
		if(Component.isMouseLeft || Component.isMouseRight) {
			for(int x=(camX /Tile.tileSize); x<(camX / Tile.tileSize) + renW;x++) {
				for(int y=(camY / Tile.tileSize); y<(camY / Tile.tileSize) + renH;y++) {
					if(x >= 0 && y >= 0 && x < worldW && y < worldH) {
						if(block[x][y].contains(new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY))) {
							block[x][y].isDigAnimationVisible = true;
							int sid[] = Inventory.invBar[Inventory.selected].id;
							if(Component.isMouseLeft) {
								if(Inventory.invBar[Inventory.selected].id == Tile.sword) {
									if(Component.weapon.isInUse == false) {
										Component.weapon.isInUse = true;
										Component.weapon.animationFrame = 0;
										Component.weapon.getAnimationAngle();
									}
								}else if(Inventory.invBar[Inventory.selected].id == Tile.shovel && block2[x][y].id == Tile.air && block[x][y].id != Tile.hole && block[x][y].id != Tile.water) {
										block[x][y].hitPoints --;
										if(block[x][y].hitPoints < 1) {
											block[x][y].destroy(x,y);
											autoTileCleanUp(x,y);
										}
								}
								
							} else if (Component.isMouseRight) {
								if(block[x][y].id == Tile.hole) {
									
									if(sid == Tile.earthClump) {
										block[x][y] = new Dirt(new Rectangle(x * Tile.tileSize, y * Tile.tileSize,Tile.tileSize, Tile.tileSize),Tile.earth);
										Inventory.removeFromInventory(sid, 1);
										autoTileCleanUp(x,y);
									} 
									
									
								}
								if(block[x][y].id == Tile.earth) {
									if(sid == Tile.grassSeed) {
										block[x][y] = new Grass(new Rectangle(x * Tile.tileSize, y * Tile.tileSize,Tile.tileSize, Tile.tileSize),Tile.grass);
										Inventory.removeFromInventory(sid, 1);
										autoTileCleanUp(x,y);
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
	
	public void autoTileCleanUp( int x1, int y1) {
		checkAutoTile(x1, y1);
		checkAutoTile(x1-1, y1-1);
		checkAutoTile(x1, y1-1);
		checkAutoTile(x1+1, y1-1);
		checkAutoTile(x1-1, y1);
		checkAutoTile(x1+1, y1);
		checkAutoTile(x1-1, y1+1);
		checkAutoTile(x1, y1+1);
		checkAutoTile(x1+1, y1+1);
	}
	
	public void tick(int camX, int camY, int renW, int renH) {
		if(!Inventory.isOpen) {
			building(camX, camY, renW, renH);
		}
		
		int mobSpawn = rand.nextInt(30) + 1;
		
		if(mobSpawn == 1) {
//		    int mobLocationX = rand.nextInt(worldW-2)+1;
//		    int mobLocationY = rand.nextInt(worldH-2)+1;
//			int mobID2 = Component.mob.size();
//			Component.mob.add(new Chicken(mobLocationX * Tile.tileSize,mobLocationY * Tile.tileSize,Tile.tileSize,Tile.tileSize * 2, Tile.mobChicken, Component.mobID));
//			Component.mobID = mobID2+1;
		}
	}
	
	public void render(Graphics g, int camX, int camY, int renW, int renH) {
		
		for(int x=(camX /Tile.tileSize); x<(camX / Tile.tileSize) + renW;x++) {
			for(int y=(camY / Tile.tileSize); y<(camY / Tile.tileSize) + renH;y++) {
				if(x >= 0 && y >= 0 && x < worldW && y < worldH) {
					block[x][y].render(g);
					block2[x][y].render(g);
					if(block[x][y].id != Tile.air && block[x][y].id != Tile.bedrock && !Inventory.isOpen) {
						if(block[x][y].contains(new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY))) {
							g.setColor(new Color(255,255,255, 40));
							g.drawRect(block[x][y].x - camX, block[x][y].y - camY, block[x][y].width-1, block[x][y].height-1);
						}
					}
				}
			}
		}
		//Character Health Bar
		g.setColor(new Color(255,0,0, 255));
		g.fillRect(18, 18,Component.character.HP * Component.pixelSize,8); 
	}
	//For overhead graphics...
	public void render2(Graphics g, int camX, int camY, int renW, int renH) {
		
	}
}
