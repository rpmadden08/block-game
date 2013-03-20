package com.companyname.blockgame.GameStates;

import com.companyname.blockgame.Component;
import com.companyname.blockgame.GameState;
import com.companyname.blockgame.Tile;

public class MainState extends GameState{
	public MainState() {
		type = Component.MAIN_STATE;
	}
	
	public void tick() {
		Component.character.tick();
		Component.level.tick((int) Component.sX, (int) Component.sY, (Component.pixel.width /Tile.tileSize) + 2,
				   			 (Component.pixel.height / Tile.tileSize) + 2);
		Component.day.tick();

		for(int i = 0; i < Component.mob.toArray().length; i ++) {
			Component.mob.get(i).tick();
		}
		for(int i = 0; i < Component.collectible.size(); i ++) {
			Component.collectible.get(i).tick();
		}
	}

	public void renderToBuffer() { 
		Component.level.render(dbg, (int) Component.sX, (int) Component.sY, 
							  (Component.pixel.width /Tile.tileSize) + 2, 
							  (Component.pixel.height / Tile.tileSize) + 2);
		
		Component.character.render(dbg);
		
		Component.level.render2(dbg,(int) Component.sX, (int) Component.sY, 
							   (Component.pixel.width /Tile.tileSize) + 2, 
							   (Component.pixel.height / Tile.tileSize) + 2);
		
		

		for(int i = 0; i < Component.mob.toArray().length; i ++) {
			Component.mob.get(i).render(dbg);
		}

		for(int i = 0; i < Component.collectible.toArray().length; i ++) {
			Component.collectible.get(i).render(dbg);

		}
		
		Component.day.render(dbg);
		Component.inventory.render(dbg);

		Component.debugger.render(dbg);
		renderMenu();
	}
	
	public void renderMenu() {
		
	}
}