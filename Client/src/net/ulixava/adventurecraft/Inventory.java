package net.ulixava.adventurecraft;

import java.awt.*;
import java.awt.event.*;

public class Inventory {
	public static Cell[] invBar= new Cell[Tile.invLength];
	public static Cell[] invBag= new Cell[Tile.invLength * Tile.invHeight];
	
	public static boolean isOpen = false;
	public static boolean isHolding = false;
	
	public static int selected = 0;
	public static int[] holdingID = Tile.air;
	
	public Inventory() {
		for(int i=0;i < invBar.length;i++) {
			invBar[i] = new Cell(new Rectangle((Component.pixel.width /2) -((Tile.invLength * (Tile.invCellSize + Tile.invCellSpace))/2) + (i *(Tile.invCellSize + Tile.invCellSpace)),Component.pixel.height - (Tile.invCellSize +Tile.invBorderSpace),Tile.invCellSize, Tile.invCellSize ), Tile.air);
		}
		int x= 0, y = 0;
		for(int i = 0; i<invBag.length; i++) {
			invBag[i] = new Cell(new Rectangle((Component.pixel.width /2) -((Tile.invLength * (Tile.invCellSize + Tile.invCellSpace))/2) + (x *(Tile.invCellSize + Tile.invCellSpace)),Component.pixel.height - (Tile.invCellSize +Tile.invBorderSpace) - (Tile.invHeight * (Tile.invCellSize + Tile.invCellSpace)) + (y * (Tile.invCellSize+ Tile.invCellSpace)),Tile.invCellSize, Tile.invCellSize ), Tile.air);
		
		x++;
		if(x == Tile.invLength) {
			x = 0;
			y++;
		}
		}
		
		invBar[0].id = Tile.earthClump;
		invBar[0].stack = +12;
		invBar[2].id = Tile.sandClump;
		invBar[2].stack = +78;
		
	}
	
	public static void addToInventory(int[] itemType, int amount) {
		for(int i = 0; i <invBar.length; i++) {
			if(invBar[i].id == itemType) {
				if(invBar[i].stack < 99) {
					//Need to account for "amount" being more than one and going over 100...
					invBar[i].stack += amount;
					return;
				}
			}
		}
		for(int i = 0; i <invBar.length; i++) {
			if(invBar[i].id == Tile.air) {
				invBar[i].id = itemType;
				invBar[i].stack += amount;
				return;
			}
		}
		for(int i = 0; i <invBag.length; i++) {
			if(invBag[i].id == itemType) {
				if(invBag[i].stack < 99) {
					//Need to account for "amount" being more than one and going over 100...
					invBag[i].stack += amount;
					return;
				}
			}
		}
		for(int i = 0; i <invBag.length; i++) {
			if(invBag[i].id == Tile.air) {
				invBag[i].id = itemType;
				invBag[i].stack += amount;
				return;
			}
		}
		
	}
	
	public static void click(MouseEvent e) {
		if(e.getButton() == 1) {
			if(isOpen){
				for(int i = 0; i <invBar.length; i++) {
					if(invBar[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
						if(invBar[i].id != Tile.air && !isHolding) {
							holdingID = invBar[i].id;
							invBar[i].id = Tile.air;
							
							isHolding = true;
						} else if(isHolding && invBar[i].id == Tile.air) {
							invBar[i].id = holdingID;
							isHolding = false;
						} else if(isHolding && invBar[i].id != Tile.air) {
							int[] con = invBar[i].id;
							
							invBar[i].id = holdingID;
							holdingID = con;
						}
							
						
					}
				}
				
				
				for(int i = 0; i <invBag.length; i++) {
					if(invBag[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
						if(invBag[i].id != Tile.air && !isHolding) {
							holdingID = invBag[i].id;
							invBag[i].id = Tile.air;
							
							isHolding = true;
						} else if(isHolding && invBag[i].id == Tile.air) {
							invBag[i].id = holdingID;
							isHolding = false;
						} else if(isHolding && invBag[i].id != Tile.air) {
							int[] con = invBag[i].id;
							
							invBag[i].id = holdingID;
							holdingID = con;
						}
							
						
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i=0;i < invBar.length;i++) {
			boolean isSelected = false;
			if(i == selected) {
				isSelected = true;
			}
			
			invBar[i].render(g, isSelected);
		}
		if(isOpen) {
			for(int i=0;i < invBag.length;i++) {				
				invBag[i].render(g, false);
			}
		}
		
		if(isHolding) {
			g.drawImage(Tile.tileset_terrain, 
					(Component.mse.x / Component.pixelSize) -(Tile.invCellSize / 2)+Tile.invItemBorder,
					(Component.mse.y / Component.pixelSize) -(Tile.invCellSize / 2)+ Tile.invItemBorder,
					(Component.mse.x / Component.pixelSize)-(Tile.invCellSize / 2) + Tile.invCellSize - Tile.invItemBorder,
					(Component.mse.y / Component.pixelSize)-(Tile.invCellSize / 2)+Tile.invCellSize - Tile.invItemBorder,
					holdingID[0] * Tile.tileSize,
					holdingID[1] * Tile.tileSize,
					holdingID[0] * Tile.tileSize + Tile.tileSize,
					holdingID[1] * Tile.tileSize + Tile.tileSize,
					null);
		}
	}
	
	
}
