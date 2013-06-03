package com.companyname.blockgame;

import java.awt.*;

import com.companyname.blockgame.BlockTypes.Hole;

//import net.ulixava.adventurecraft.BlockTypes.Hole;


public class Block extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public int[] id = {-1, -1};
	public int[] baseId = {-1, -1};
	public int[] dropId = {-1, -1};
	public int autoTile = 0;
	public int[] autoTile1 = {0, 0};
	public int[] autoTile2 = {0, 0};
	public int[] autoTile3 = {0, 0};
	public int[] autoTile4 = {0, 0};
	
	public boolean hiLite = false;
	
	public boolean isPassable = true;
	public int maxHitPoints = 25;
	public int hitPoints = 25;
	public boolean isMouseTouching = false;
	public boolean isDigAnimationVisible = false;
	
	public float heightMap = 50;
	
	
	
	public Block(Rectangle size, int[] id) {
		setBounds(size);
		this.id = id;
		baseId = id;
		
	}
	public void destroy(int x2,int y2) {
		Component.level.block[x2][y2] = new Hole(new Rectangle(x2* Tile.tileSize, y2 * Tile.tileSize,Tile.tileSize, Tile.tileSize),Tile.hole);
		
		int collectibleID = Component.collectible.size();
		Component.collectible.add(new Collectible(x,y, Component.collectibleID, dropId));
		Component.collectibleID = collectibleID+1;
	}
	
	public void render(Graphics g) {
		
		if (id != Tile.air) {
			//Top Left
			g.drawImage(Tile.tileset_terrain, 
					x - (int) Component.sX, 
					y - (int) Component.sY, 
					x + width/2 - (int) Component.sX, 
					y + height/2 - (int) Component.sY,
					
					(id[0]+autoTile1[0]) * Tile.tileSize, 
					(id[1]+autoTile1[1]) * Tile.tileSize, 
					(id[0]+autoTile1[0]) * Tile.tileSize + Tile.tileSize/2,
					(id[1]+autoTile1[1]) * Tile.tileSize + Tile.tileSize/2,
					null);
			
			//Top Right
			g.drawImage(Tile.tileset_terrain, 
					x - (int) Component.sX +width/2, 
					y - (int) Component.sY, 
					x + width/2 - (int) Component.sX + width/2, 
					y + height/2 - (int) Component.sY+ height/2 - height /2,
					
					(id[0]+autoTile2[0]) * Tile.tileSize+ Tile.tileSize/2, 
					(id[1]+autoTile2[1]) * Tile.tileSize, 
					(id[0]+autoTile2[0]) * Tile.tileSize +Tile.tileSize,
					(id[1]+autoTile2[1]) * Tile.tileSize + Tile.tileSize/2,
					null);
			
			//Bottom Left
			g.drawImage(Tile.tileset_terrain, 
					x - (int) Component.sX, 
					y - (int) Component.sY + height/2, 
					x + width/2 - (int) Component.sX, 
					y + height/2 - (int) Component.sY +height/2,
					
					(id[0]+autoTile3[0]) * Tile.tileSize, 
					(id[1]+autoTile3[1]) * Tile.tileSize+ Tile.tileSize/2, 
					(id[0]+autoTile3[0]) * Tile.tileSize + Tile.tileSize/2,
					(id[1]+autoTile3[1]) * Tile.tileSize+Tile.tileSize,
					null);
			
			//Bottom Right
			g.drawImage(Tile.tileset_terrain, 
					x - (int) Component.sX + width/2, 
					y - (int) Component.sY + height/2, 
					x + width/2 - (int) Component.sX + height /2, 
					y + height/2 - (int) Component.sY + height/2,
					
					(id[0]+autoTile4[0]) * Tile.tileSize+Tile.tileSize/2, 
					(id[1]+autoTile4[1]) * Tile.tileSize + Tile.tileSize/2, 
					(id[0]+autoTile4[0]) * Tile.tileSize +Tile.tileSize,
					(id[1]+autoTile4[1]) * Tile.tileSize+Tile.tileSize,
					null);
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
		if(hiLite == true) {
			g.setColor(new Color(0,0,0, 255));
			g.drawRect ((int) x ,(int) y ,width-1,height-1);
		}
		
	}
}
