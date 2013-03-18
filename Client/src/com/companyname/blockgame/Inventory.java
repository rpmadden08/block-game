package com.companyname.blockgame;

import java.awt.*;
import java.awt.event.*;
import java.awt.FontMetrics;

import com.companyname.blockgame.GameStates.InventoryState;
import com.companyname.blockgame.GameStates.MainState;

public class Inventory {
	public static Cell[] invBar= new Cell[Tile.invLength];
	public static Cell[] invBag= new Cell[Tile.invLength * Tile.invHeight];
	public static Cell[] invCrafting= new Cell[2 * 2];
	public static Cell[] invCrafted = new Cell[1];
	
	public static boolean isOpen = false;
	public static boolean isHolding = false;
	
	public static int selected = 0;
	public static int[] holdingID = Tile.air;
	public static int holdingStack = 0;
	
	public Inventory() {
		for(int i=0;i < invBar.length;i++) {
			invBar[i] = new Cell(new Rectangle((Component.pixel.width /2) -((Tile.invLength * (Tile.invCellSize + Tile.invCellSpace))/2) + (i *(Tile.invCellSize + Tile.invCellSpace)),
					Component.pixel.height - (Tile.invCellSize +Tile.invBorderSpace),Tile.invCellSize, Tile.invCellSize ), Tile.air);
		}
		int x= 0, y = 0;
		for(int i = 0; i<invBag.length; i++) {
			invBag[i] = new Cell(new Rectangle((Component.pixel.width /2) -((Tile.invLength * (Tile.invCellSize + Tile.invCellSpace))/2) + (x *(Tile.invCellSize + Tile.invCellSpace)),
					Component.pixel.height - (Tile.invCellSize +Tile.invBorderSpace) - (Tile.invHeight * (Tile.invCellSize + Tile.invCellSpace)) + (y * (Tile.invCellSize+ Tile.invCellSpace)),Tile.invCellSize, Tile.invCellSize ), Tile.air);
			
			x++;
			if(x == Tile.invLength) {
				x = 0;
				y++;
			}
		}
		//Crafting
		for(int i = 0; i<invCrafting.length; i++) {
		invCrafting[i] = new Cell(new Rectangle((Component.pixel.width /2) -((Tile.invLength * (Tile.invCellSize + Tile.invCellSpace))/2) + (x *(Tile.invCellSize + Tile.invCellSpace)),
					Component.pixel.height - (Tile.invCellSize +Tile.invBorderSpace) - (10* (Tile.invCellSize + Tile.invCellSpace)) + (y * (Tile.invCellSize+ Tile.invCellSpace)),
					Tile.invCellSize, 
					Tile.invCellSize ), 
					Tile.air);
		
			x++;
			if(x == 2) {
				x = 0;
				y++;
			}
		}
		
		invCrafted[0] = new Cell(new Rectangle((Component.pixel.width /2) -((4 * (Tile.invCellSize + Tile.invCellSpace))/2) + (x *(Tile.invCellSize + Tile.invCellSpace)),
				Component.pixel.height - (Tile.invCellSize +Tile.invBorderSpace) - (11* (Tile.invCellSize + Tile.invCellSpace)) + (y * (Tile.invCellSize+ Tile.invCellSpace)),
				Tile.invCellSize, 
				Tile.invCellSize ), 
				Tile.air);
		
		
		
		invBar[0].id = Tile.earthClump;
		invBar[0].stack = +4;
		invBar[1].id = Tile.log;
		invBar[1].stack = +3;
		invBar[2].id = Tile.log;
		invBar[2].stack = +98;
		
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
	
	public static void removeFromInventory(int[] itemType, int amount) {
		for(int i = 0; i <invBar.length; i++) {
			if(invBar[i]== invBar[selected]) {
				if(invBar[i].stack > 1) {
					//Need to account for "amount" being more than one and possibly going negative
					invBar[i].stack -= amount;
					return;
				}
				if(invBar[i].stack == 1) {
					invBar[i].stack -= amount;
					invBar[i].id = Tile.air;
					return;
				}
				
			}
		}
//		for(int i = 0; i <invBar.length; i++) {
//			if(invBar[i].id == Tile.air) {
//				invBar[i].id = itemType;
//				invBar[i].stack -= amount;
//				return;
//			}
//		}
	}
	
	//Crafting recipes
	public static void crafting() {
		//Planks
		if(invCrafting[0].id == Tile.log && invCrafting[1].id == Tile.air && invCrafting[2].id == Tile.air && invCrafting[3].id == Tile.air) {
			invCrafted[0].id = Tile.plank;
			invCrafted[0].stack = +4;
		} else if(invCrafting[0].id == Tile.air && invCrafting[1].id == Tile.log && invCrafting[2].id == Tile.air && invCrafting[3].id == Tile.air) {
			invCrafted[0].id = Tile.plank;
			invCrafted[0].stack = +4;
		} else if(invCrafting[0].id == Tile.air && invCrafting[1].id == Tile.air && invCrafting[2].id == Tile.log && invCrafting[3].id == Tile.air) {
			invCrafted[0].id = Tile.plank;
			invCrafted[0].stack = +4;
		} else if(invCrafting[0].id == Tile.air && invCrafting[1].id == Tile.air && invCrafting[2].id == Tile.air && invCrafting[3].id == Tile.log) {
			invCrafted[0].id = Tile.plank;
			invCrafted[0].stack = +4;
		} else {
			invCrafted[0].id = Tile.air;
			invCrafted[0].stack = 0;
		}
	}
	public static void craftedItemPickedUp() {
		for(int i = 0; i <invCrafting.length; i++) {
			if(invCrafting[i].stack > 1) {
				invCrafting[i].stack -= 1;
				return;
			}
			if(invCrafting[i].stack == 1) {
				invCrafting[i].stack -= 1;
				invCrafting[i].id = Tile.air;
				return;
			}
		}
	}
	
	public static void click(MouseEvent e) {
		if(e.getButton() == 3) { //  Right Click
			for(int i = 0; i <invBar.length; i++) {
				if(invBar[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
					if(invBar[i].id != Tile.air && !isHolding) {
						if(invBar[i].stack > 1) {
							int con = invBar[i].stack / 2;
							int con2 = invBar[i].stack - con;
							holdingID = invBar[i].id;
							holdingStack = con2;
							invBar[i].stack = con;
							isHolding = true;
						} else {
							holdingID = invBar[i].id;
							holdingStack = invBar[i].stack;
							invBar[i].id = Tile.air;
							invBar[i].stack = 0;
							isHolding = true;
						}
					} else if(isHolding && invBar[i].id == Tile.air) {
						if(holdingStack > 1) {
							invBar[i].id = holdingID;
							invBar[i].stack += 1;
							holdingStack -= 1;
						} else {
							invBar[i].id = holdingID;
							invBar[i].stack = holdingStack;
							isHolding = false;
						}
					} else if(isHolding && invBar[i].id != Tile.air) {
						if(invBar[i].id == holdingID) {
							if(invBar[i].stack > 98) {
							} else {
								invBar[i].stack += 1;
								holdingStack -= 1;
							}
						} else {
							int[] con = invBar[i].id;
							int con2 = invBar[i].stack;
							
							invBar[i].id = holdingID;
							invBar[i].stack = holdingStack;
							holdingID = con;
							holdingStack = con2;
						}
					}
					
				}
			}
			//For the inventory when the inventory is open
			for(int i = 0; i <invBag.length; i++) {
				if(invBag[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
					if(invBag[i].id != Tile.air && !isHolding) {
						if(invBag[i].stack > 1) {
							int con = invBag[i].stack / 2;
							int con2 = invBag[i].stack - con;
							holdingID = invBag[i].id;
							holdingStack = con2;
							invBag[i].stack = con;
							isHolding = true;
						} else {
							holdingID = invBag[i].id;
							holdingStack = invBag[i].stack;
							invBag[i].id = Tile.air;
							invBag[i].stack = 0;
							
							isHolding = true;
						}
					} else if(isHolding && invBag[i].id == Tile.air) {
						if(holdingStack > 1) {
							invBag[i].id = holdingID;
							invBag[i].stack += 1;
							holdingStack -= 1;
						} else {
							invBag[i].id = holdingID;
							invBag[i].stack = holdingStack;
							isHolding = false;
						}
					} else if(isHolding && invBag[i].id != Tile.air) {
						if(invBag[i].id == holdingID) {
							if(invBag[i].stack > 98) {
							} else {
								invBag[i].stack += 1;
								holdingStack -= 1;
							}
						} else {
							int[] con = invBag[i].id;
							int con2 = invBag[i].stack;
							
							invBag[i].id = holdingID;
							invBag[i].stack = holdingStack;
							holdingID = con;
							holdingStack = con2;
						}
					}
				}
			}
			//For crafting area
			for(int i = 0; i <invCrafting.length; i++) {
				if(invCrafting[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
					if(invCrafting[i].id != Tile.air && !isHolding) {
						if(invCrafting[i].stack > 1) {
							int con = invCrafting[i].stack / 2;
							int con2 = invCrafting[i].stack - con;
							holdingID = invCrafting[i].id;
							holdingStack = con2;
							invCrafting[i].stack = con;
							isHolding = true;
						} else {
							holdingID = invCrafting[i].id;
							holdingStack = invCrafting[i].stack;
							invCrafting[i].id = Tile.air;
							invCrafting[i].stack = 0;
							
							isHolding = true;
						}
					} else if(isHolding && invCrafting[i].id == Tile.air) {
						if(holdingStack > 1) {
							invCrafting[i].id = holdingID;
							invCrafting[i].stack += 1;
							holdingStack -= 1;
						} else {
							invCrafting[i].id = holdingID;
							invCrafting[i].stack = holdingStack;
							isHolding = false;
						}
					} else if(isHolding && invCrafting[i].id != Tile.air) {
						if(invCrafting[i].id == holdingID) {
							if(invCrafting[i].stack > 98) {
							} else {
								invCrafting[i].stack += 1;
								holdingStack -= 1;
							}
						} else {
						int[] con = invCrafting[i].id;
						int con2 = invCrafting[i].stack;
						
						invCrafting[i].id = holdingID;
						invCrafting[i].stack = holdingStack;
						holdingID = con;
						holdingStack = con2;
						}
					}
				}
			}
			if(invCrafted[0].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
				//If the box is holding something and you are not...
				if(invCrafted[0].id != Tile.air && !isHolding) {
					holdingID = invCrafted[0].id;
					holdingStack = invCrafted[0].stack;
					craftedItemPickedUp();
					invCrafted[0].id = Tile.air;
					invCrafted[0].stack = 0;
					
					isHolding = true;
				//If the box is empty and you are holding something
				} 
				if(invCrafted[0].id != Tile.air && isHolding) {
					if(invCrafted[0].id == holdingID) {
						if(invCrafted[0].stack + holdingStack < 100) {
							holdingStack += invCrafted[0].stack;
							craftedItemPickedUp();
							invCrafted[0].id = Tile.air;
							invCrafted[0].stack = 0;
						}
					}
				}
			}
		}
		if(e.getButton() == 1) {  //left click
			if(Component.currentState.type == Component.INVENTORY_STATE){
				//For the bottom bar
				for(int i = 0; i <invBar.length; i++) {
					if(invBar[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
						if(invBar[i].id != Tile.air && !isHolding) {
							holdingID = invBar[i].id;
							holdingStack = invBar[i].stack;
							invBar[i].id = Tile.air;
							invBar[i].stack = 0;
							
							isHolding = true;
						} else if(isHolding && invBar[i].id == Tile.air) {
							invBar[i].id = holdingID;
							invBar[i].stack = holdingStack;
							isHolding = false;
						} else if(isHolding && invBar[i].id != Tile.air) {
							if(invBar[i].id == holdingID) {
								if(invBar[i].stack + holdingStack > 99) {
									int placeCount = (invBar[i].stack + holdingStack) - 99;
									invBar[i].stack = 99;
									holdingStack = placeCount;
								} else {
									invBar[i].stack += holdingStack;
									isHolding = false;
								}
							} else {
								int[] con = invBar[i].id;
								int con2 = invBar[i].stack;
								
								invBar[i].id = holdingID;
								invBar[i].stack = holdingStack;
								holdingID = con;
								holdingStack = con2;
							}
						}
							
						
					}
				}
				
				//For the inventory when the inventory is open
				for(int i = 0; i <invBag.length; i++) {
					if(invBag[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
						if(invBag[i].id != Tile.air && !isHolding) {
							holdingID = invBag[i].id;
							holdingStack = invBag[i].stack;
							invBag[i].id = Tile.air;
							invBag[i].stack = 0;
							
							isHolding = true;
						} else if(isHolding && invBag[i].id == Tile.air) {
							invBag[i].id = holdingID;
							invBag[i].stack = holdingStack;
							isHolding = false;
						} else if(isHolding && invBag[i].id != Tile.air) {
							if(invBag[i].id == holdingID) {
								if(invBag[i].stack + holdingStack > 99) {
									int placeCount = (invBag[i].stack + holdingStack) - 99;
									invBag[i].stack = 99;
									holdingStack = placeCount;
								} else {
									invBag[i].stack += holdingStack;
									isHolding = false;
								}
							} else {
								int[] con = invBag[i].id;
								int con2 = invBag[i].stack;
								
								invBag[i].id = holdingID;
								invBag[i].stack = holdingStack;
								holdingID = con;
								holdingStack = con2;
							}
						}
					}
				}
				//For crafting area
				for(int i = 0; i <invCrafting.length; i++) {
					if(invCrafting[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
						if(invCrafting[i].id != Tile.air && !isHolding) {
							holdingID = invCrafting[i].id;
							holdingStack = invCrafting[i].stack;
							invCrafting[i].id = Tile.air;
							invCrafting[i].stack = 0;
							
							isHolding = true;
						} else if(isHolding && invCrafting[i].id == Tile.air) {
							invCrafting[i].id = holdingID;
							invCrafting[i].stack = holdingStack;
							isHolding = false;
						} else if(isHolding && invCrafting[i].id != Tile.air) {
							if(invCrafting[i].id == holdingID) {
								if(invCrafting[i].stack + holdingStack > 99) {
									int placeCount = (invCrafting[i].stack + holdingStack) - 99;
									invCrafting[i].stack = 99;
									holdingStack = placeCount;
								} else {
									invCrafting[i].stack += holdingStack;
									isHolding = false;
								}
							} else {
							int[] con = invCrafting[i].id;
							int con2 = invCrafting[i].stack;
							
							invCrafting[i].id = holdingID;
							invCrafting[i].stack = holdingStack;
							holdingID = con;
							holdingStack = con2;
							}
						}
					}
				}
				//For crafting pickup
				if(invCrafted[0].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
					//If the box is holding something and you are not...
					if(invCrafted[0].id != Tile.air && !isHolding) {
						holdingID = invCrafted[0].id;
						holdingStack = invCrafted[0].stack;
						craftedItemPickedUp();
						invCrafted[0].id = Tile.air;
						invCrafted[0].stack = 0;
						
						isHolding = true;
					//If the box is empty and you are holding something
					} 
					if(invCrafted[0].id != Tile.air && isHolding) {
						if(invCrafted[0].id == holdingID) {
							if(invCrafted[0].stack + holdingStack < 100) {
								holdingStack += invCrafted[0].stack;
								craftedItemPickedUp();
								invCrafted[0].id = Tile.air;
								invCrafted[0].stack = 0;
							}
						}
					}
				}
			}
		}
		crafting();
	}
	
	public void render(Graphics g) {
		for(int i=0;i < invBar.length; i++) {
			boolean isSelected = false;
			if(i == selected) {
				isSelected = true;
			}
			
			invBar[i].render(g, isSelected);
		}

	}
	public void renderFull(Graphics g) {
		for(int i=0;i < invBag.length;i++) {				
			invBag[i].render(g, false);
		}
		for(int i=0;i < invCrafting.length;i++) {				
			invCrafting[i].render(g, false);
		}
		invCrafted[0].render(g, false);

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
			if(holdingStack > 1) {
				Font font = new Font("Helvetica", Font.PLAIN, 12);
				g.setFont(font);
				g.setColor(Color.WHITE);
				String stack2 = Integer.toString(holdingStack);
				FontMetrics fontMetrics = g.getFontMetrics(font);
				
				g.drawString(Integer.toString(holdingStack), 
						(Component.mse.x / Component.pixelSize) -(Tile.invCellSize / 2)+Tile.invCellSize-4 - fontMetrics.stringWidth(stack2),
						(Component.mse.y / Component.pixelSize) -(Tile.invCellSize / 2) +Tile.invCellSize-5
						//g.drawString(Integer.toString(stack), x +Tile.invCellSize-4 - fontMetrics.stringWidth(stack2), y+Tile.invCellSize-5);
						);
			}
		}
	}
	public static void toggleInventoryState() {
		if(Component.currentState.type == Component.INVENTORY_STATE) {
			Component.currentState = new MainState();
		} else {
			Component.currentState = new InventoryState();
		}
	}
	
}
