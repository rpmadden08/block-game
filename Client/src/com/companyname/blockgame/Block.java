package com.companyname.blockgame;

import java.awt.*;

import com.companyname.blockgame.BlockTypes.Hole;

//import net.ulixava.adventurecraft.BlockTypes.Hole;


public class Block extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public int[] id = {-1, -1};
	public int[] dropId = {-1, -1};
	public boolean isPassable = true;
	public int maxHitPoints = 25;
	public int hitPoints = 25;
	public boolean isMouseTouching = false;
	public boolean isDigAnimationVisible = false;
	
	
	public Block(Rectangle size, int[] id) {
		setBounds(size);
		this.id = id;
	}
	public void destroy(int x2,int y2) {
		Component.level.block[x2][y2] = new Hole(new Rectangle(x2 * Tile.tileSize, y2 * Tile.tileSize,Tile.tileSize, Tile.tileSize),Tile.hole);
		int collectibleID = Component.collectible.size();
		Component.collectible.add(new Collectible(x,y, Component.collectibleID, dropId));
		Component.collectibleID = collectibleID+1;
	}
	
	
	public void render(Graphics g) {
		
		if (id != Tile.air) {
			g.drawImage(Tile.tileset_terrain, x - (int) Component.sX, y - (int) Component.sY, x + width - (int) Component.sX, y + height - (int) Component.sY,id[0] * Tile.tileSize, id[1] * Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
		}
		
		if(Component.isMouseLeft == true && hitPoints != maxHitPoints && isDigAnimationVisible == true) {  //At some point I should change the second condition to add "&& Mouse is touching this block"
			double hitPointsPercentage = (double) hitPoints / (double)maxHitPoints* 100;
			//System.out.println(hitPointsPercentage);
			if(hitPointsPercentage < 20) {
				g.drawImage(Tile.tileset_terrain, 
						//Where to place the character
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(Tile.breaking5[0] * Tile.tileSize),
						Tile.breaking5[1] * Tile.tileSize, 
						Tile.breaking5[0] * Tile.tileSize +(Tile.tileSize),
						Tile.breaking5[1] * Tile.tileSize +(Tile.tileSize), 
						null);
			} else if(hitPointsPercentage < 40) {
				g.drawImage(Tile.tileset_terrain, 
						//Where to place the character
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(Tile.breaking4[0] * Tile.tileSize),
						Tile.breaking4[1] * Tile.tileSize, 
						Tile.breaking4[0] * Tile.tileSize +(Tile.tileSize),
						Tile.breaking4[1] * Tile.tileSize +(Tile.tileSize), 
						null);
			} else if(hitPointsPercentage < 60) {
				g.drawImage(Tile.tileset_terrain, 
						//Where to place the character
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(Tile.breaking3[0] * Tile.tileSize),
						Tile.breaking3[1] * Tile.tileSize, 
						Tile.breaking3[0] * Tile.tileSize +(Tile.tileSize),
						Tile.breaking3[1] * Tile.tileSize +(Tile.tileSize), 
						null);
			} else if(hitPointsPercentage < 80) {
				g.drawImage(Tile.tileset_terrain, 
						//Where to place the character
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(Tile.breaking2[0] * Tile.tileSize),
						Tile.breaking2[1] * Tile.tileSize, 
						Tile.breaking2[0] * Tile.tileSize +(Tile.tileSize),
						Tile.breaking2[1] * Tile.tileSize +(Tile.tileSize), 
						null);
			} else if(hitPointsPercentage < 100) {
				g.drawImage(Tile.tileset_terrain, 
						//Where to place the character
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(Tile.breaking1[0] * Tile.tileSize),
						Tile.breaking1[1] * Tile.tileSize, 
						Tile.breaking1[0] * Tile.tileSize +(Tile.tileSize),
						Tile.breaking1[1] * Tile.tileSize +(Tile.tileSize), 
						null);
			} 
		} else {
			hitPoints = maxHitPoints;
		}
		
	}
}