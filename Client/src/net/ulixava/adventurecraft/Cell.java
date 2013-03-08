package net.ulixava.adventurecraft;

import java.awt.*;
import java.awt.FontMetrics;

public class Cell extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	public int[] id = {0, 0};
	public int stack = 0;
	
	public Cell(Rectangle size, int[] id) {
		setBounds(size);
		this.id = id;
	}
	
	public void render(Graphics g, boolean isSelected) {
		if(Inventory.isOpen && contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
			g.setColor(new Color(255,255,255, 130));
			g.fillRect(x, y, width, height);
		}
		
		g.drawImage(Tile.tile_cell, x, y, width, height, null);
		
		if(id != Tile.air) {
			g.drawImage(Tile.tileset_terrain, x +Tile.invItemBorder,y +Tile.invItemBorder, x - Tile.invItemBorder+ width, y-Tile.invItemBorder + height, id[0] * Tile.tileSize, id[1] * Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
		}
		
		if(isSelected && !Inventory.isOpen) {
			g.drawImage(Tile.tile_select, x-1, y-1, width +3, height +3, null);
		}
		if(stack > 1) {
			Font font = new Font("Helvetica", Font.PLAIN, 12);
			g.setFont(font);
			g.setColor(Color.WHITE);
			String stack2 = Integer.toString(stack);
			FontMetrics fontMetrics = g.getFontMetrics(font);
			
			g.drawString(Integer.toString(stack), x +Tile.invCellSize-4 - fontMetrics.stringWidth(stack2), y+Tile.invCellSize-5);
		}
	}
}
