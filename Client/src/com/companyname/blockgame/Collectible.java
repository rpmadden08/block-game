package com.companyname.blockgame;

import java.awt.*;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.Items.NoItem;

public class Collectible extends Rectangle{
	private static final long serialVersionUID = 1L;
	public Item item = new NoItem();
	public int x = 0;
	public int y = 0;
	public int arrayId = 0;
	public int frameOffsetLeft = 4;
	public int frameWidth = 9;
	public int frameOffsetTop = 4;
	public int frameHeight = 8;
	
	public Collectible(int xPosition, int yPosition, int idOfArray, int idOfCollectible) {
		item = getItemFromItemId(idOfCollectible);
		arrayId = idOfArray;
		x = xPosition;
		y = yPosition;		
		
	}
	
	public Rectangle bounds() {
		return (new Rectangle(x + frameOffsetLeft- (int)Component.sX,y + frameOffsetTop-(int)Component.sY,frameWidth,frameHeight));
	}
	
	public void tick() {

		Rectangle rectangle1 = bounds();
		Rectangle rectangle2 = Component.character.bounds();
		if (rectangle1.intersects(rectangle2)) {
			for(int i = 0; i < Component.collectibles.toArray().length; i ++) {
				if(Component.collectibles.get(i).arrayId == arrayId) {
					Inventory.addToInventory(item.id, 1);
					Component.collectibles.remove(i);
				}
			}
		}
	}
	
	public void render(Graphics g) {
				g.drawImage(ImageAssets.TERRAIN_IMAGE,
				x - (int) Component.sX,
				y - (int) Component.sY,
				x + TILE_SIZE - (int) Component.sX,
				y + TILE_SIZE - (int) Component.sY,
				item.imageXPos * TILE_SIZE,
				item.imageYPos * TILE_SIZE,
				item.imageXPos * TILE_SIZE + TILE_SIZE,
				item.imageYPos * TILE_SIZE + TILE_SIZE,
				null);
	}
}
