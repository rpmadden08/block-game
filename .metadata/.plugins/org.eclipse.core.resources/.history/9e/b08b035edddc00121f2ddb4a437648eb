package com.companyname.blockgame;

import java.awt.*;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.Items.*;

public class Cell extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	public Item item = new NoItem();
	public int stack = 0;
	
	public Cell(Rectangle size) {
		setBounds(size);
	}
	
	public void render(Graphics g, boolean isSelected) {
		if(Inventory.isOpen && contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
			g.setColor(new Color(255,255,255, 130));
			g.fillRect(x, y, width, height);
		}
		
		g.drawImage(ImageAssets.CELL_IMAGE, x, y, width, height, null);
		
		if(item.id != EMPTY) {
			g.drawImage(ImageAssets.TERRAIN_IMAGE, x +INVENTORY_ITEM_BORDER,y +INVENTORY_ITEM_BORDER, x - INVENTORY_ITEM_BORDER+ width, y-INVENTORY_ITEM_BORDER + height, item.imageXPos * TILE_SIZE, item.imageYPos * TILE_SIZE, item.imageXPos * TILE_SIZE + TILE_SIZE, item.imageYPos * TILE_SIZE + TILE_SIZE, null);
		}
		
		if(isSelected && !Inventory.isOpen) {
			g.drawImage(ImageAssets.SELECT_IMAGE, x-1, y-1, width +2, height +2, null);
		}
		if(stack > 1) {
			Font font = new Font("Helvetica", Font.PLAIN, 12);
			g.setFont(font);
			g.setColor(Color.WHITE);
			String stack2 = Integer.toString(stack);
			FontMetrics fontMetrics = g.getFontMetrics(font);
			
			g.drawString(Integer.toString(stack), x +INVENTORY_CELL_SIZE-4 - fontMetrics.stringWidth(stack2), y+INVENTORY_CELL_SIZE-5);
		}
	}
}
