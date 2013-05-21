package com.companyname.blockgame;

import java.awt.*;
import java.util.*;
import java.io.*;

//import net.ulixava.adventurecraft.BlockTypes.Grass;
//import net.ulixava.adventurecraft.BlockTypes.Bedrock;
//import net.ulixava.adventurecraft.BlockTypes.Hole;
import com.companyname.blockgame.BlockTypes.*;

public class Level {
	public static int chunkSize = 10;
	public static int worldW =10, worldH = 10;
	//public static Chunk[][] chunk = new Chunk [3][3];
	public Block[][] block = new Block [worldW][worldH];
	public Block[][] block2 = new Block [worldW][worldH];
	public Block[][] block3 = new Block [worldW][worldH];
	public static float[][] mapHeight = new float[worldW][worldH]; //To get elevations through perlin noise
	public int chunkOffsetX = 0;
	public int chunkOffsetY = 0;
	public Random rand = new Random();
	public Random rand2 = new Random();
	public Random seed = new Random();
	public int Size = worldW;
	public PerlinGenerator perlin;
	public BlockType test2 = new BlockType();
	public Font font = new Font("Arial", Font.PLAIN, 23);
	
	public Level() {
//		SaveGame test = new SaveGame();
//		test.loadSave();

		long rgenseed = System.currentTimeMillis();
		Random rgen = new Random();
		rgen.setSeed(rgenseed);
		//System.out.println("Random number generator seed is " + rgenseed);
		
		perlin = new PerlinGenerator((int) rgenseed);
		//Perlin = new PerlinGenerator(0);

		
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				//block[x][y].id = Tile.earth;
				
				block2[x][y] = new Block(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.air);
			}
		}
		
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				block[x][y] = new Grass(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.grass);
				mapHeight[x][y] = perlin.Noise(4 * y / (float)Size, 4 * x / (float)Size, 0);
			}
		}
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				if(y == 0 || x == 0 || y == block.length-1 || x == block[0].length-1) {
					block2[x][y] = new Bedrock(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.bedrock);	
				}
			}
		}
		
		
		

//		
		for(int y=0; y<block.length;y++) {
			for(int x=0; x<block[0].length;x++) {
				if(mapHeight[x][y] < -0.2) {
					block[x][y] = new Water(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.water);
					
				
				}
			}
		}
//TREES
//		for(int y=0; y<block2.length;y++) {
//			for(int x=0; x<block2[0].length;x++) {
//				Random rand = new Random();
//				int  n = rand.nextInt(10) + 1;
//				if(n == 1 && block[x][y].id == Tile.grass && block2[x][y].id != Tile.bedrock) {
//					block2[x][y] = new Tree(new Rectangle(x * Tile.tileSize + (int) Component.sX, y * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.tree);
//				}
//			}
//		}

		for(int y=1; y<block.length-1;y++) {
			for(int x=1; x<block[0].length-1;x++) {
					checkAutoTile(x, y);
			}
		}
		
		//TESTING FOR SAVE FILE STUFF...
		//block[14][20].id = Tile.air;
		//checkAutoTile(6, 2);
		//System.out.println(block[6][2].id[0]+","+block[6][2].id[1]);

		//block[5][5] = new Block(new Rectangle(5 * Tile.tileSize + (int) Component.sX, 5 * Tile.tileSize + (int) Component.sY, Tile.tileSize, Tile.tileSize), Tile.bedrock);

		//System.out.println(block[6][2].id[1]);
		//BlockType test2 = new BlockType();
//		System.out.println(test2.example.get("Wayne"));
//		String test15 = "Wayne";
//		test2.x1 = 5;
//		test2.y1 = 5;
//		test2.newBlock();
//		block[5][5] = test2.example.get(test15);
//		test2.x1 = 5;
//		test2.y1 = 7;
//		test2.newBlock();
//		block[5][7] = test2.example.get(test15);
	}
	
	
	
	public void checkAutoTile(int x1, int y1) {
		if(block[x1][y1].baseId != Tile.grass && x1 !=0 && x1 < worldW-1 && y1 != 0 && y1 < worldH-1) {
			boolean top = false;
			boolean left = false;
			boolean right = false;
			boolean bottom = false;
			boolean topLeft = false;
			boolean topRight = false;
			boolean bottomLeft = false;
			boolean bottomRight = false;
			block[x1][y1].autoTile1[0] = 0;
			block[x1][y1].autoTile1[1] = 0;
			block[x1][y1].autoTile2[0] = 0;
			block[x1][y1].autoTile2[1] = 0;
			block[x1][y1].autoTile3[0] = 0;
			block[x1][y1].autoTile3[1] = 0;
			block[x1][y1].autoTile4[0] = 0;
			block[x1][y1].autoTile4[1] = 0;
			
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
			if(block[x1][y1].baseId == block[x1-1][y1-1].baseId) {
				topLeft = true;
			}
			if(block[x1][y1].baseId == block[x1+1][y1-1].baseId) {
				topRight = true;
			}
			if(block[x1][y1].baseId == block[x1-1][y1+1].baseId) {
				bottomLeft = true;
			}
			if(block[x1][y1].baseId == block[x1+1][y1+1].baseId) {
				bottomRight = true;
			}
			
			
			if(top == false && left == false && right == false && bottom == false) {
			//I don't think I need to do anything because this is the base tile.  
			}
			
			if(top == false && left == false && right == true && bottom == false) {
				block[x1][y1].autoTile1[1] = 1;
				block[x1][y1].autoTile2[1] = 1;
				block[x1][y1].autoTile3[1] = 3;
				block[x1][y1].autoTile4[1] = 3;
			}
			if(top == true && left == false && right == false && bottom == false) {
				block[x1][y1].autoTile1[1] = 3;
				block[x1][y1].autoTile2[0] = 2;
				block[x1][y1].autoTile2[1] = 3;
				block[x1][y1].autoTile3[1] = 3;
				block[x1][y1].autoTile4[0] = 2;
				block[x1][y1].autoTile4[1] = 3;
				
			}
			if(top == true && left == false && right == true && bottom == false) {
				block[x1][y1].autoTile1[1] = 3;
				block[x1][y1].autoTile3[1] = 3;
				block[x1][y1].autoTile4[1] = 3;
				if(topRight == false) {
					block[x1][y1].autoTile2[0] = 2;
					
				} else {
					block[x1][y1].autoTile2[1] = 3;
					
				}
			}
			if(top == false && left == true && right == false && bottom == false) {
				block[x1][y1].autoTile1[0] = 2;
				block[x1][y1].autoTile2[0] = 2;
				block[x1][y1].autoTile3[0] = 2;
				block[x1][y1].autoTile4[0] = 2;
				block[x1][y1].autoTile1[1] = 1;
				block[x1][y1].autoTile2[1] = 1;
				block[x1][y1].autoTile3[1] = 3;
				block[x1][y1].autoTile4[1] = 3;
			}
			if(top == false && left == true && right == true && bottom == false) {
				block[x1][y1].autoTile1[0] = 1;
				block[x1][y1].autoTile2[0] = 1;
				block[x1][y1].autoTile3[0] = 1;
				block[x1][y1].autoTile4[0] = 1;
				block[x1][y1].autoTile1[1] = 1;
				block[x1][y1].autoTile2[1] = 1;
				block[x1][y1].autoTile3[1] = 3;
				block[x1][y1].autoTile4[1] = 3;
			}
			if(top == true && left == true && right == false && bottom == false) {
				block[x1][y1].autoTile2[0] = 2;
				block[x1][y1].autoTile3[0] = 2;
				block[x1][y1].autoTile4[0] = 2;
				block[x1][y1].autoTile2[1] = 3;
				block[x1][y1].autoTile3[1] = 3;
				block[x1][y1].autoTile4[1] = 3;
				if(topLeft == false) {
					block[x1][y1].autoTile1[0] = 2;
					
				} else {
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 3;
					
				}
			}
			if(top == true && left == true && right == true && bottom == false) {
				block[x1][y1].autoTile1[0] = 1;
				block[x1][y1].autoTile2[0] = 1;
				block[x1][y1].autoTile3[0] = 1;
				block[x1][y1].autoTile4[0] = 1;
				block[x1][y1].autoTile1[1] = 3;
				block[x1][y1].autoTile2[1] = 3;
				block[x1][y1].autoTile3[1] = 3;
				block[x1][y1].autoTile4[1] = 3;
				if(topRight == false && topLeft == false) {
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
					block[x1][y1].autoTile2[1] = 0;
				}else if (topRight == true && topLeft == false) {
					block[x1][y1].autoTile1[1] = 0;
					block[x1][y1].autoTile1[0] = 2;
				}else if (topRight == false && topLeft == true) {
					block[x1][y1].autoTile2[1] = 0;
					block[x1][y1].autoTile2[0] = 2;
					
				}
					
			}
			if(top == false && left == false && right == false && bottom == true) {
				block[x1][y1].autoTile1[1] = 1;
				block[x1][y1].autoTile2[0] = 2;
				block[x1][y1].autoTile2[1] = 1;
				block[x1][y1].autoTile3[1] = 1;
				block[x1][y1].autoTile4[0] = 2;
				block[x1][y1].autoTile4[1] = 1;
			}
			
			if(top == false && left == false && right == true && bottom == true) {
				block[x1][y1].autoTile1[1] = 1;
				block[x1][y1].autoTile2[1] = 1;
				block[x1][y1].autoTile3[1] = 1;
				block[x1][y1].autoTile4[1] = 1;
				
				if(bottomRight == false) {
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				}
			}
			if(top == true && left == false && right == false && bottom == true) {
				block[x1][y1].autoTile1[1] = 2;
				block[x1][y1].autoTile2[0] = 2;
				block[x1][y1].autoTile2[1] = 2;
				block[x1][y1].autoTile3[1] = 2;
				block[x1][y1].autoTile4[0] = 2;
				block[x1][y1].autoTile4[1] = 2;
			}
			if(top == true && left == false && right == true && bottom == true) {
				block[x1][y1].autoTile1[1] = 2;
				block[x1][y1].autoTile2[1] = 2;
				block[x1][y1].autoTile3[1] = 2;
				block[x1][y1].autoTile4[1] = 2;
				if(topRight == false && bottomRight == false) {
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile2[1] = 0;
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				} else if (topRight == true && bottomRight == false) {
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
					
				} else if (topRight == false && bottomRight == true) {
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile2[1] = 0;
				}
			}
			if(top == false && left == true && right == false && bottom == true) {
				block[x1][y1].autoTile1[0] = 2;
				block[x1][y1].autoTile1[1] = 1;
				block[x1][y1].autoTile2[0] = 2;
				block[x1][y1].autoTile2[1] = 1;
				block[x1][y1].autoTile3[0] = 2;
				block[x1][y1].autoTile3[1] = 1;
				block[x1][y1].autoTile4[0] = 2;
				block[x1][y1].autoTile4[1] = 1;
				if(bottomLeft == false) {
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
				}
			}
			if(top == false && left == true && right == true && bottom == true) {
				block[x1][y1].autoTile1[0] = 1;
				block[x1][y1].autoTile1[1] = 1;
				block[x1][y1].autoTile2[0] = 1;
				block[x1][y1].autoTile2[1] = 1;
				block[x1][y1].autoTile3[0] = 1;
				block[x1][y1].autoTile3[1] = 1;
				block[x1][y1].autoTile4[0] = 1;
				block[x1][y1].autoTile4[1] = 1;
				if(bottomRight == false && bottomLeft == false) {
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				} else if (bottomRight == true && bottomLeft == false) {
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
					
				} else if (bottomRight == false && bottomLeft == true) {
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				}
			}
			if(top == true && left == true && right == false && bottom == true) {
				block[x1][y1].autoTile1[0] = 2;
				block[x1][y1].autoTile1[1] = 2;
				block[x1][y1].autoTile2[0] = 2;
				block[x1][y1].autoTile2[1] = 2;
				block[x1][y1].autoTile3[0] = 2;
				block[x1][y1].autoTile3[1] = 2;
				block[x1][y1].autoTile4[0] = 2;
				block[x1][y1].autoTile4[1] = 2;
				if(topLeft == false && bottomLeft == false) {
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
				} else if (topLeft == true && bottomLeft == false) {
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
					
				} else if (topLeft == false && bottomLeft == true) {
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
				}
			}
			if(top == true && left == true && right == true && bottom == true) {
				block[x1][y1].autoTile1[0] = 1;
				block[x1][y1].autoTile1[1] = 2;
				block[x1][y1].autoTile2[0] = 1;
				block[x1][y1].autoTile2[1] = 2;
				block[x1][y1].autoTile3[0] = 1;
				block[x1][y1].autoTile3[1] = 2;
				block[x1][y1].autoTile4[0] = 1;
				block[x1][y1].autoTile4[1] = 2;
				if(topRight == false && topLeft == false && bottomRight == false && bottomLeft == false) {
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile2[1] = 0;
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				} else if (topRight == true && topLeft == false && bottomRight == false && bottomLeft == false) {
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				} else if (topRight == false && topLeft == true && bottomRight == false && bottomLeft == false) {
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile2[1] = 0;
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				} else if (topRight == false && topLeft == false && bottomRight == true && bottomLeft == false) {
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile2[1] = 0;
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
				} else if (topRight == false && topLeft == false && bottomRight == false && bottomLeft == true) {
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile2[1] = 0;
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				} else if (topRight == true && topLeft == true && bottomRight == false && bottomLeft == false) {
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
					
				} else if (topRight == false && topLeft == true && bottomRight == true && bottomLeft == false) {
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile2[1] = 0;
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
				} else if (topRight == false && topLeft == false && bottomRight == true && bottomLeft == true) {
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile2[1] = 0;
				} else if (topRight == true && topLeft == false && bottomRight == false && bottomLeft == true) {
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				} else if (topRight == true && topLeft == false && bottomRight == true && bottomLeft == false) {
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
				} else if (topRight == false && topLeft == true&& bottomRight == false && bottomLeft == true) {
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile2[1] = 0;
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				} else if (topRight == true && topLeft == true && bottomRight == true && bottomLeft == false) {
					block[x1][y1].autoTile3[0] = 2;
					block[x1][y1].autoTile3[1] = 0;
					
				} else if (topRight == false && topLeft == true && bottomRight == true && bottomLeft == true) {
					block[x1][y1].autoTile2[0] = 2;
					block[x1][y1].autoTile2[1] = 0;
					
				} else if (topRight == true && topLeft == false && bottomRight == true && bottomLeft == true) {
					
					block[x1][y1].autoTile1[0] = 2;
					block[x1][y1].autoTile1[1] = 0;
				} else if (topRight == true && topLeft == true && bottomRight == false && bottomLeft == true) {
					block[x1][y1].autoTile4[0] = 2;
					block[x1][y1].autoTile4[1] = 0;
				} 
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
								}else if(Inventory.invBar[Inventory.selected].id == Tile.shovel && block2[x][y].id == Tile.air && block[x][y].id != Tile.earth && block[x][y].id != Tile.water) {
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
		for(int y=1; y<block.length-1;y++) {
			for(int x=1; x<block[0].length-1;x++) {
				
				//if(block[x][y].heightMap > -5) {
					checkAutoTile(x, y);
				//}
			}
		}
		//System.out.println(block[2][2].autoTile1[0]+","+ block[2][2].autoTile1[1]);
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
					//block3[x][y].render(g);
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
//		g.setColor(new Color(255,0,0, 255));
//		g.fillRect(18, 18,Component.character.HP * Component.pixelSize,8); 
		//g.setFont();
		
		g.drawImage(Tile.tileset_terrain, 
				//Where to place the character
				(int) 16, 
				(int) 16, 
				(int) 19, 
				(int) 26, 
				/*Where it's cut out*/
				0, 
				11 * 16, 
				3, 
				11 * 16 + 10, 
				null);
		for(int y=0; y<Component.character.maxHP-2;y++) {
			g.drawImage(Tile.tileset_terrain, 
					//Where to place the character
					(int) 19 + y, 
					(int) 16, 
					(int) 20 + y, 
					(int) 26, 
					/*Where it's cut out*/
					3, 
					11 * 16, 
					4, 
					11 * 16 + 10, 
					null);
		}
		g.drawImage(Tile.tileset_terrain, 
				//Where to place the character
				(int) 19 + Component.character.maxHP-2, 
				(int) 16, 
				(int) 22 + Component.character.maxHP-2, 
				(int) 26, 
				/*Where it's cut out*/
				4, 
				11 * 16, 
				7, 
				11 * 16 + 10, 
				null);
		if(Component.character.HP > 0) {
			g.drawImage(Tile.tileset_terrain, 
					//Where to place the character
					(int) 18, 
					(int) 16, 
					(int) 19, 
					(int) 26, 
					/*Where it's cut out*/
					7, 
					11 * 16, 
					8, 
					11 * 16 + 10, 
					null);
		}
		for(int y=1; y<Component.character.HP-1d;y++) {
			g.drawImage(Tile.tileset_terrain, 
					//Where to place the character
					(int) 18 + y, 
					(int) 16, 
					(int) 19 + y, 
					(int) 26, 
					/*Where it's cut out*/
					8, 
					11 * 16, 
					9, 
					11 * 16 + 10, 
					null);
		}
		if(Component.character.HP == Component.character.maxHP) {
			g.drawImage(Tile.tileset_terrain, 
					//Where to place the character
					(int) 19 + Component.character.maxHP-2, 
					(int) 16, 
					(int) 20 + Component.character.maxHP-2, 
					(int) 26, 
					/*Where it's cut out*/
					9, 
					11 * 16, 
					10, 
					11 * 16 + 10, 
					null);
		}
		File test4 = new File("res/squaredance00.TTF");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, test4);
		} catch(Exception e) {
			System.out.println("Font did not load for some reason...");
		}


		//Font font = new Font(font, Font.PLAIN, 23);
		Font derivedFont = font.deriveFont(Font.BOLD, 9f);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		
		g2.setFont(derivedFont);
		g2.setColor(Color.WHITE);
		g2.drawString("HP",3, 24);
		
	}
	//For overhead graphics...
	public void render2(Graphics g, int camX, int camY, int renW, int renH) {
		
	}
}
