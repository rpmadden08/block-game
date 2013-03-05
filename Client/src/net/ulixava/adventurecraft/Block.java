package net.ulixava.adventurecraft;

import java.awt.*;


public class Block extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public int[] id = {-1, -1};
	public boolean isPassable = false;
	public int maxHitPoints = 100;
	public int hitPoints = 100;
	
	
	public Block(Rectangle size, int[] id) {
		setBounds(size);
		this.id = id;
	}
	
//	public boolean isPassable() {
//		if(id == Tile.earth) {
//			return true;
//		}
//		return false;
//	}
	
	public void render(Graphics g) {
		if (id != Tile.air) {
			g.drawImage(Tile.tileset_terrain, x - (int) Component.sX, y - (int) Component.sY, x + width - (int) Component.sX, y + height - (int) Component.sY,id[0] * Tile.tileSize, id[1] * Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
		}
		if(Component.isMouseLeft == true) {
			if(hitPoints < 20) {
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
			} else if(hitPoints < 40) {
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
			} else if(hitPoints < 60) {
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
			} else if(hitPoints < 80) {
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
			} else if(hitPoints < 100) {
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
			hitPoints = 100;
		}
		
	}
}
