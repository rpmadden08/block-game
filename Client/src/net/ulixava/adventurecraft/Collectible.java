package net.ulixava.adventurecraft;

import java.awt.*;

public class Collectible extends Rectangle{
	private static final long serialVersionUID = 1L;
	public int x = 0;
	public int y = 0;
	public int id = 0;
	public int frameOffsetLeft = 4;
	public int frameWidth = 9;
	public int frameOffsetTop = 4;
	public int frameHeight = 8;
	
	public Collectible(int x2, int y2, int id2) {
		id = id2;
		x = x2;
		y = y2;		
		
	}
	
	public Rectangle bounds() {
		return (new Rectangle(x + frameOffsetLeft,y + frameOffsetTop,frameWidth,frameHeight));
	}
	
	public void tick() {

		Rectangle rectangle1 = bounds();
		Rectangle rectangle2 = Component.character.bounds();
		if (rectangle1.intersects(rectangle2)) {
			for(int i = 0; i < Component.collectible.toArray().length; i ++) {
				if(Component.collectible.get(i).id == id) {
					Component.collectible.remove(i);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		g.drawImage(Tile.tileset_terrain,
				x - (int) Component.sX,
				y - (int) Component.sY,
				x + Tile.tileSize - (int) Component.sX,
				y + Tile.tileSize - (int) Component.sY,
				Tile.earthClump[0] * Tile.tileSize,
				Tile.earthClump[1] * Tile.tileSize,
				Tile.earthClump[0] * Tile.tileSize + Tile.tileSize,
				Tile.earthClump[1] * Tile.tileSize + Tile.tileSize,
				null);
	}
}
