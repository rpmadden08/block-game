package com.companyname.blockgame.GameStates;
import com.companyname.blockgame.Component;

public class InventoryState extends MainState {
	public InventoryState() {
		type = Component.INVENTORY_STATE;
	}
	
	public void renderMenu() {
		Component.inventory.renderFull(dbg);
	}
}
