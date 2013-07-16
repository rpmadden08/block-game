package com.companyname.blockgame;

import java.awt.*;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.BlockTypes.Hole;

//import net.ulixava.adventurecraft.BlockTypes.Hole;


public class Block extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public int id = AIR;
	public int baseId = AIR;
	public int dropId = AIR;
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
	
	public int imageXPos = 0;
	public int imageYPos = 0;
	
	public Block(Rectangle size, int id) {
		setBounds(size);
		this.id = id;
		baseId = id;
		
	}
	public void destroy(int x2,int y2) {
		Component.level.block[x2][y2] = new Hole(new Rectangle(x2* TILE_SIZE, y2 * TILE_SIZE,TILE_SIZE, TILE_SIZE), HOLE);
		
		int collectibleID = Component.collectibles.size();
		Component.collectibles.add(new Collectible(x,y, Component.collectibleID, dropId));
		Component.collectibleID = collectibleID+1;
	}
	
	public void render(Graphics g) {
		
		if (id != AIR) {
			//Top Left
			g.drawImage(ImageAssets.TERRAIN_IMAGE, 
					x - (int) Component.sX, 
					y - (int) Component.sY, 
					x + width/2 - (int) Component.sX, 
					y + height/2 - (int) Component.sY,
					
					(imageXPos+autoTile1[0]) * TILE_SIZE, 
					(imageYPos+autoTile1[1]) * TILE_SIZE, 
					(imageXPos+autoTile1[0]) * TILE_SIZE + TILE_SIZE/2,
					(imageYPos+autoTile1[1]) * TILE_SIZE + TILE_SIZE/2,
					null);
			
			//Top Right
			g.drawImage(ImageAssets.TERRAIN_IMAGE, 
					x - (int) Component.sX +width/2, 
					y - (int) Component.sY, 
					x + width/2 - (int) Component.sX + width/2, 
					y + height/2 - (int) Component.sY+ height/2 - height /2,
					
					(imageXPos+autoTile2[0]) * TILE_SIZE+ TILE_SIZE/2, 
					(imageYPos+autoTile2[1]) * TILE_SIZE, 
					(imageXPos+autoTile2[0]) * TILE_SIZE +TILE_SIZE,
					(imageYPos+autoTile2[1]) * TILE_SIZE + TILE_SIZE/2,
					null);
			
			//Bottom Left
			g.drawImage(ImageAssets.TERRAIN_IMAGE, 
					x - (int) Component.sX, 
					y - (int) Component.sY + height/2, 
					x + width/2 - (int) Component.sX, 
					y + height/2 - (int) Component.sY +height/2,
					
					(imageXPos+autoTile3[0]) * TILE_SIZE, 
					(imageYPos+autoTile3[1]) * TILE_SIZE+ TILE_SIZE/2, 
					(imageXPos+autoTile3[0]) * TILE_SIZE + TILE_SIZE/2,
					(imageYPos+autoTile3[1]) * TILE_SIZE+TILE_SIZE,
					null);
			
			//Bottom Right
			g.drawImage(ImageAssets.TERRAIN_IMAGE, 
					x - (int) Component.sX + width/2, 
					y - (int) Component.sY + height/2, 
					x + width/2 - (int) Component.sX + height /2, 
					y + height/2 - (int) Component.sY + height/2,
					
					(imageXPos+autoTile4[0]) * TILE_SIZE+TILE_SIZE/2, 
					(imageYPos+autoTile4[1]) * TILE_SIZE + TILE_SIZE/2, 
					(imageXPos+autoTile4[0]) * TILE_SIZE +TILE_SIZE,
					(imageYPos+autoTile4[1]) * TILE_SIZE+TILE_SIZE,
					null);
		}
		
		if(Component.isMouseLeft == true && hitPoints != maxHitPoints && isDigAnimationVisible == true) {  //At some point I should change the second condition to add "&& Mouse is touching this block"
			
			double hitPointsPercentage = (double) hitPoints / (double)maxHitPoints* 100;
			//System.out.println(hitPointsPercentage);
			if(hitPointsPercentage < 20) {
				g.drawImage(ImageAssets.TERRAIN_IMAGE, 
						//Where to place the character
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(BREAKING_ANIMATION[4][0] * TILE_SIZE),
						BREAKING_ANIMATION[4][1] * TILE_SIZE, 
						BREAKING_ANIMATION[4][0] * TILE_SIZE +(TILE_SIZE),
						BREAKING_ANIMATION[4][1] * TILE_SIZE +(TILE_SIZE), 
						null);
			} else if(hitPointsPercentage < 40) {
				g.drawImage(ImageAssets.TERRAIN_IMAGE, 
						//Where to place the character
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(BREAKING_ANIMATION[3][0] * TILE_SIZE),
						BREAKING_ANIMATION[3][1] * TILE_SIZE, 
						BREAKING_ANIMATION[3][0] * TILE_SIZE +(TILE_SIZE),
						BREAKING_ANIMATION[3][1] * TILE_SIZE +(TILE_SIZE), 
						null);
			} else if(hitPointsPercentage < 60) {
				g.drawImage(ImageAssets.TERRAIN_IMAGE, 
						//Where to place the character
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(BREAKING_ANIMATION[2][0] * TILE_SIZE),
						BREAKING_ANIMATION[2][1] * TILE_SIZE, 
						BREAKING_ANIMATION[2][0] * TILE_SIZE +(TILE_SIZE),
						BREAKING_ANIMATION[2][1] * TILE_SIZE +(TILE_SIZE), 
						null);
			} else if(hitPointsPercentage < 80) {
				g.drawImage(ImageAssets.TERRAIN_IMAGE, 
						//Where to place the character
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(BREAKING_ANIMATION[1][0] * TILE_SIZE),
						BREAKING_ANIMATION[1][1] * TILE_SIZE, 
						BREAKING_ANIMATION[1][0] * TILE_SIZE +(TILE_SIZE),
						BREAKING_ANIMATION[1][1] * TILE_SIZE +(TILE_SIZE), 
						null);
			} else if(hitPointsPercentage < 100) {
				g.drawImage(ImageAssets.TERRAIN_IMAGE, 
						//Where to place the character
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(BREAKING_ANIMATION[0][0] * TILE_SIZE),
						BREAKING_ANIMATION[0][1] * TILE_SIZE, 
						BREAKING_ANIMATION[0][0] * TILE_SIZE +(TILE_SIZE),
						BREAKING_ANIMATION[0][1] * TILE_SIZE +(TILE_SIZE), 
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
