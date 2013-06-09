package com.companyname.blockgame;

import java.awt.*;
import java.awt.event.*;
import java.awt.FontMetrics;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.GameStates.*;
import com.companyname.blockgame.Items.*;

public class Inventory {
	public static Cell[] invBar= new Cell[INVENTORY_LENGTH];
	public static Cell[] invBag= new Cell[INVENTORY_LENGTH * INVENTORY_HEIGHT];
	public static Cell[] invCrafting= new Cell[2 * 2];
	public static Cell[] invCrafted = new Cell[1];
	
	public static boolean isOpen = false;
	public static boolean isHolding = false;
	
	public static int selected = 0;
	public static Item holdingItem = new NoItem();
	public static int holdingStack = 0;
	
	public Inventory() {
		for(int i=0;i < invBar.length;i++) {
			invBar[i] = new Cell(new Rectangle((Component.pixel.width /2) -((INVENTORY_LENGTH * (INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE))/2) + (i *(INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE)),
					Component.pixel.height - (INVENTORY_CELL_SIZE +INVENTORY_BORDER_SPACE),INVENTORY_CELL_SIZE, INVENTORY_CELL_SIZE ));
		}
		int x= 0, y = 0;
		for(int i = 0; i<invBag.length; i++) {
			invBag[i] = new Cell(new Rectangle((Component.pixel.width /2) -((INVENTORY_LENGTH * (INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE))/2) + (x *(INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE)),
					Component.pixel.height - (INVENTORY_CELL_SIZE +INVENTORY_BORDER_SPACE) - (INVENTORY_HEIGHT * (INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE)) + (y * (INVENTORY_CELL_SIZE+ INVENTORY_CELL_SPACE)),INVENTORY_CELL_SIZE, INVENTORY_CELL_SIZE ));
			
			x++;
			if(x == INVENTORY_LENGTH) {
				x = 0;
				y++;
			}
		}
		//Crafting
		for(int i = 0; i<invCrafting.length; i++) {
		invCrafting[i] = new Cell(new Rectangle((Component.pixel.width /2) -((INVENTORY_LENGTH * (INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE))/2) + (x *(INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE)),
					Component.pixel.height - (INVENTORY_CELL_SIZE +INVENTORY_BORDER_SPACE) - (10* (INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE)) + (y * (INVENTORY_CELL_SIZE+ INVENTORY_CELL_SPACE)),
					INVENTORY_CELL_SIZE, 
					INVENTORY_CELL_SIZE ));
		
			x++;
			if(x == 2) {
				x = 0;
				y++;
			}
		}
		
		invCrafted[0] = new Cell(new Rectangle((Component.pixel.width /2) -((4 * (INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE))/2) + (x *(INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE)),
				Component.pixel.height - (INVENTORY_CELL_SIZE +INVENTORY_BORDER_SPACE) - (11* (INVENTORY_CELL_SIZE + INVENTORY_CELL_SPACE)) + (y * (INVENTORY_CELL_SIZE+ INVENTORY_CELL_SPACE)),
				INVENTORY_CELL_SIZE, 
				INVENTORY_CELL_SIZE ));
		
		
		invBar[3].item = new Sword();
		invBar[3].stack = +1;
		invBar[1].item = new Log();
		invBar[1].stack = +3;
		invBar[2].item = new Log();
		invBar[2].stack = +98;
		invBar[0].item = new Shovel();
		invBar[0].stack = +1;
		
	}
	
	public static void addToInventory(int itemId, int amount) {
		for(int i = 0; i <invBar.length; i++) {
			if(invBar[i].item.id == itemId) {
				if(invBar[i].stack < 99) {
					//Need to account for "amount" being more than one and going over 100...
					invBar[i].stack += amount;
					return;
				}
			}
		}
		for(int i = 0; i <invBar.length; i++) {
			if(invBar[i].item.id == EMPTY) {
				invBar[i].item = getItemFromItemId(itemId);
				invBar[i].stack += amount;
				return;
			}
		}
		for(int i = 0; i <invBag.length; i++) {
			if(invBag[i].item.id == itemId) {
				if(invBag[i].stack < 99) {
					//Need to account for "amount" being more than one and going over 100...
					invBag[i].stack += amount;
					return;
				}
			}
		}
		for(int i = 0; i <invBag.length; i++) {
			if(invBag[i].item.id == EMPTY) {
				invBag[i].item = getItemFromItemId(itemId);
				invBag[i].stack += amount;
				return;
			}
		}
	}
	
	public static void removeFromInventory(int itemType, int amount) {
		for(int i = 0; i <invBar.length; i++) {
			if(invBar[i] == invBar[selected]) {
				if(invBar[i].stack > 1) {
					//Need to account for "amount" being more than one and possibly going negative
					invBar[i].stack -= amount;
					return;
				}
				if(invBar[i].stack == 1) {
					invBar[i].stack -= amount;
					invBar[i].item = new NoItem();
					return;
				}
				
			}
		}
//		for(int i = 0; i <invBar.length; i++) {
//			if(invBar[i].id == EMPTY) {
//				invBar[i].id = itemType;
//				invBar[i].stack -= amount;
//				return;
//			}
//		}
	}
	
	//Crafting recipes
	public static void crafting() {
		//Planks
		if(invCrafting[0].item.id == LOG && invCrafting[1].item.id == EMPTY && invCrafting[2].item.id == EMPTY && invCrafting[3].item.id == EMPTY) {
			invCrafted[0].item = new Plank();
			invCrafted[0].stack = +4;
		} else if(invCrafting[0].item.id == EMPTY && invCrafting[1].item.id == LOG && invCrafting[2].item.id == EMPTY && invCrafting[3].item.id == EMPTY) {
			invCrafted[0].item = new Plank();
			invCrafted[0].stack = +4;
		} else if(invCrafting[0].item.id == EMPTY && invCrafting[1].item.id == EMPTY && invCrafting[2].item.id == LOG && invCrafting[3].item.id == EMPTY) {
			invCrafted[0].item = new Plank();
			invCrafted[0].stack = +4;
		} else if(invCrafting[0].item.id == EMPTY && invCrafting[1].item.id == EMPTY && invCrafting[2].item.id == EMPTY && invCrafting[3].item.id == LOG) {
			invCrafted[0].item = new Plank();
			invCrafted[0].stack = +4;
		} else {
			invCrafted[0].item = new NoItem();
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
				invCrafting[i].item = new NoItem();
				return;
			}
		}
	}
	
	public static void click(MouseEvent e) {
		if(e.getButton() == 3) { //  Right Click
			for(int i = 0; i <invBar.length; i++) {
				if(invBar[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
					if(invBar[i].item.id != EMPTY && !isHolding) {
						if(invBar[i].stack > 1) {
							int con = invBar[i].stack / 2;
							int con2 = invBar[i].stack - con;
							holdingItem = invBar[i].item;
							holdingStack = con2;
							invBar[i].stack = con;
							isHolding = true;
						} else {
							holdingItem = invBar[i].item;
							holdingStack = invBar[i].stack;
							invBar[i].item = new NoItem();
							invBar[i].stack = 0;
							isHolding = true;
						}
					} else if(isHolding && invBar[i].item.id == EMPTY) {
						if(holdingStack > 1) {
							invBar[i].item = holdingItem;
							invBar[i].stack += 1;
							holdingStack -= 1;
						} else {
							invBar[i].item = holdingItem;
							invBar[i].stack = holdingStack;
							isHolding = false;
						}
					} else if(isHolding && invBar[i].item.id != EMPTY) {
						if(invBar[i].item.id == holdingItem.id) {
							if(invBar[i].stack > 98) {
							} else {
								invBar[i].stack += 1;
								holdingStack -= 1;
							}
						} else {
							Item con = invBar[i].item;
							int con2 = invBar[i].stack;
							
							invBar[i].item = holdingItem;
							invBar[i].stack = holdingStack;
							holdingItem = con;
							holdingStack = con2;
						}
					}
					
				}
			}
			//For the inventory when the inventory is open
			for(int i = 0; i <invBag.length; i++) {
				if(invBag[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
					if(invBag[i].item.id != EMPTY && !isHolding) {
						if(invBag[i].stack > 1) {
							int con = invBag[i].stack / 2;
							int con2 = invBag[i].stack - con;
							holdingItem = invBag[i].item;
							holdingStack = con2;
							invBag[i].stack = con;
							isHolding = true;
						} else {
							holdingItem = invBag[i].item;
							holdingStack = invBag[i].stack;
							invBag[i].item = new NoItem();
							invBag[i].stack = 0;
							
							isHolding = true;
						}
					} else if(isHolding && invBag[i].item.id == EMPTY) {
						if(holdingStack > 1) {
							invBag[i].item = holdingItem;
							invBag[i].stack += 1;
							holdingStack -= 1;
						} else {
							invBag[i].item = holdingItem;
							invBag[i].stack = holdingStack;
							isHolding = false;
						}
					} else if(isHolding && invBag[i].item.id != EMPTY) {
						if(invBag[i].item.id == holdingItem.id) {
							if(invBag[i].stack > 98) {
							} else {
								invBag[i].stack += 1;
								holdingStack -= 1;
							}
						} else {
							Item con = invBag[i].item;
							int con2 = invBag[i].stack;
							
							invBag[i].item = holdingItem;
							invBag[i].stack = holdingStack;
							holdingItem = con;
							holdingStack = con2;
						}
					}
				}
			}
			//For crafting area
			for(int i = 0; i <invCrafting.length; i++) {
				if(invCrafting[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
					if(invCrafting[i].item.id != EMPTY && !isHolding) {
						if(invCrafting[i].stack > 1) {
							int con = invCrafting[i].stack / 2;
							int con2 = invCrafting[i].stack - con;
							holdingItem = invCrafting[i].item;
							holdingStack = con2;
							invCrafting[i].stack = con;
							isHolding = true;
						} else {
							holdingItem = invCrafting[i].item;
							holdingStack = invCrafting[i].stack;
							invCrafting[i].item = new NoItem();
							invCrafting[i].stack = 0;
							
							isHolding = true;
						}
					} else if(isHolding && invCrafting[i].item.id == EMPTY) {
						if(holdingStack > 1) {
							invCrafting[i].item = holdingItem;
							invCrafting[i].stack += 1;
							holdingStack -= 1;
						} else {
							invCrafting[i].item = holdingItem;
							invCrafting[i].stack = holdingStack;
							isHolding = false;
						}
					} else if(isHolding && invCrafting[i].item.id != EMPTY) {
						if(invCrafting[i].item.id == holdingItem.id) {
							if(invCrafting[i].stack > 98) {
							} else {
								invCrafting[i].stack += 1;
								holdingStack -= 1;
							}
						} else {
							Item con = invCrafting[i].item;
							int con2 = invCrafting[i].stack;
							
							invCrafting[i].item = holdingItem;
							invCrafting[i].stack = holdingStack;
							holdingItem = con;
							holdingStack = con2;
						}
					}
				}
			}
			if(invCrafted[0].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
				//If the box is holding something and you are not...
				if(invCrafted[0].item.id != EMPTY && !isHolding) {
					holdingItem = invCrafted[0].item;
					holdingStack = invCrafted[0].stack;
					craftedItemPickedUp();
					invCrafted[0].item = new NoItem();
					invCrafted[0].stack = 0;
					
					isHolding = true;
				//If the box is empty and you are holding something
				} 
				if(invCrafted[0].item.id != EMPTY && isHolding) {
					if(invCrafted[0].item.id == holdingItem.id) {
						if(invCrafted[0].stack + holdingStack < 100) {
							holdingStack += invCrafted[0].stack;
							craftedItemPickedUp();
							invCrafted[0].item = new NoItem();
							invCrafted[0].stack = 0;
						}
					}
				}
			}
		}
		if(e.getButton() == 1) {  //left click
			if(Component.currentState.type == INVENTORY_STATE){
				//For the bottom bar
				for(int i = 0; i <invBar.length; i++) {
					if(invBar[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
						if(invBar[i].item.id != EMPTY && !isHolding) {
							holdingItem = invBar[i].item;
							holdingStack = invBar[i].stack;
							invBar[i].item = new NoItem();
							invBar[i].stack = 0;
							
							isHolding = true;
						} else if(isHolding && invBar[i].item.id == EMPTY) {
							invBar[i].item = holdingItem;
							invBar[i].stack = holdingStack;
							isHolding = false;
						} else if(isHolding && invBar[i].item.id != EMPTY) {
							if(invBar[i].item.id == holdingItem.id) {
								if(invBar[i].stack + holdingStack > 99) {
									int placeCount = (invBar[i].stack + holdingStack) - 99;
									invBar[i].stack = 99;
									holdingStack = placeCount;
								} else {
									invBar[i].stack += holdingStack;
									isHolding = false;
								}
							} else {
								Item con = invBar[i].item;
								int con2 = invBar[i].stack;
								
								invBar[i].item = holdingItem;
								invBar[i].stack = holdingStack;
								holdingItem = con;
								holdingStack = con2;
							}
						}
							
						
					}
				}
				
				//For the inventory when the inventory is open
				for(int i = 0; i <invBag.length; i++) {
					if(invBag[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
						if(invBag[i].item.id != EMPTY && !isHolding) {
							holdingItem = invBag[i].item;
							holdingStack = invBag[i].stack;
							invBag[i].item = new NoItem();
							invBag[i].stack = 0;
							
							isHolding = true;
						} else if(isHolding && invBag[i].item.id == EMPTY) {
							invBag[i].item = holdingItem;
							invBag[i].stack = holdingStack;
							isHolding = false;
						} else if(isHolding && invBag[i].item.id != EMPTY) {
							if(invBag[i].item.id == holdingItem.id) {
								if(invBag[i].stack + holdingStack > 99) {
									int placeCount = (invBag[i].stack + holdingStack) - 99;
									invBag[i].stack = 99;
									holdingStack = placeCount;
								} else {
									invBag[i].stack += holdingStack;
									isHolding = false;
								}
							} else {
								Item con = invBag[i].item;
								int con2 = invBag[i].stack;
								
								invBag[i].item = holdingItem;
								invBag[i].stack = holdingStack;
								holdingItem = con;
								holdingStack = con2;
							}
						}
					}
				}
				//For crafting area
				for(int i = 0; i <invCrafting.length; i++) {
					if(invCrafting[i].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
						if(invCrafting[i].item.id != EMPTY && !isHolding) {
							holdingItem = invCrafting[i].item;
							holdingStack = invCrafting[i].stack;
							invCrafting[i].item = new NoItem();
							invCrafting[i].stack = 0;
							
							isHolding = true;
						} else if(isHolding && invCrafting[i].item.id == EMPTY) {
							invCrafting[i].item = holdingItem;
							invCrafting[i].stack = holdingStack;
							isHolding = false;
						} else if(isHolding && invCrafting[i].item.id != EMPTY) {
							if(invCrafting[i].item == holdingItem) {
								if(invCrafting[i].stack + holdingStack > 99) {
									int placeCount = (invCrafting[i].stack + holdingStack) - 99;
									invCrafting[i].stack = 99;
									holdingStack = placeCount;
								} else {
									invCrafting[i].stack += holdingStack;
									isHolding = false;
								}
							} else {
							Item con = invCrafting[i].item;
							int con2 = invCrafting[i].stack;
							
							invCrafting[i].item = holdingItem;
							invCrafting[i].stack = holdingStack;
							holdingItem = con;
							holdingStack = con2;
							}
						}
					}
				}
				//For crafting pickup
				if(invCrafted[0].contains(new Point(Component.mse.x / Component.pixelSize,Component.mse.y / Component.pixelSize))) {
					//If the box is holding something and you are not...
					if(invCrafted[0].item.id != EMPTY && !isHolding) {
						holdingItem = invCrafted[0].item;
						holdingStack = invCrafted[0].stack;
						craftedItemPickedUp();
						invCrafted[0].item = new NoItem();
						invCrafted[0].stack = 0;
						
						isHolding = true;
					//If the box is empty and you are holding something
					} 
					if(invCrafted[0].item.id != EMPTY && isHolding) {
						if(invCrafted[0].item.id == holdingItem.id) {
							if(invCrafted[0].stack + holdingStack < 100) {
								holdingStack += invCrafted[0].stack;
								craftedItemPickedUp();
								invCrafted[0].item = new NoItem();
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
			g.drawImage(ImageAssets.TERRAIN_IMAGE, 
					(Component.mse.x / Component.pixelSize) -(INVENTORY_CELL_SIZE / 2)+INVENTORY_ITEM_BORDER,
					(Component.mse.y / Component.pixelSize) -(INVENTORY_CELL_SIZE / 2)+ INVENTORY_ITEM_BORDER,
					(Component.mse.x / Component.pixelSize)-(INVENTORY_CELL_SIZE / 2) + INVENTORY_CELL_SIZE - INVENTORY_ITEM_BORDER,
					(Component.mse.y / Component.pixelSize)-(INVENTORY_CELL_SIZE / 2)+INVENTORY_CELL_SIZE - INVENTORY_ITEM_BORDER,
					holdingItem.imageXPos * TILE_SIZE,
					holdingItem.imageYPos * TILE_SIZE,
					holdingItem.imageXPos * TILE_SIZE + TILE_SIZE,
					holdingItem.imageYPos * TILE_SIZE + TILE_SIZE,
					null);
			if(holdingStack > 1) {
				Font font = new Font("Helvetica", Font.PLAIN, 12);
				g.setFont(font);
				g.setColor(Color.WHITE);
				String stack2 = Integer.toString(holdingStack);
				FontMetrics fontMetrics = g.getFontMetrics(font);
				
				g.drawString(Integer.toString(holdingStack), 
						(Component.mse.x / Component.pixelSize) -(INVENTORY_CELL_SIZE / 2)+INVENTORY_CELL_SIZE-4 - fontMetrics.stringWidth(stack2),
						(Component.mse.y / Component.pixelSize) -(INVENTORY_CELL_SIZE / 2) +INVENTORY_CELL_SIZE-5
						//g.drawString(Integer.toString(stack), x +INVENTORY_CELL_SIZE-4 - fontMetrics.stringWidth(stack2), y+INVENTORY_CELL_SIZE-5);
						);
			}
		}
	}
	public static void toggleInventoryState() {
		if(Component.currentState.type == INVENTORY_STATE) {
			Component.currentState = new MainState();
		} else {
			Component.currentState = new InventoryState();
		}
	}
	
}
