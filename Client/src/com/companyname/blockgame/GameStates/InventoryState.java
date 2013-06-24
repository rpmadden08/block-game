package com.companyname.blockgame.GameStates;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.Component;

public class InventoryState extends MainState {
	public InventoryState() {
		type = INVENTORY_STATE;
	}
	
	public void renderMenu() {
		Component.inventory.renderFull(dbg);
	}
}
