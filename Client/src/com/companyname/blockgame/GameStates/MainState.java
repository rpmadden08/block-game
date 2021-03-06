package com.companyname.blockgame.GameStates;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.Component;
import com.companyname.blockgame.GameState;


public class MainState extends GameState {
	public static int tester = 0;
	
	public MainState() {
		type = MAIN_STATE;
	}
	public void tick() {
		
			
			Component.character.tick();
			
			Component.level.tick((int) Component.sX,
								(int) Component.sY, 
								(Component.pixel.width /TILE_SIZE) + 2,
					   			(Component.pixel.height /TILE_SIZE) + 2);
			Component.weapon.tick();
			Component.day.tick();
	
			for(int i = 0; i < Component.mob.toArray().length; i ++) {
				Component.mob.get(i).tick();
			}
			for(int i = 0; i < Component.collectibles.size(); i ++) {
				Component.collectibles.get(i).tick();
			}
			tester = 0;
		}
		
	

	public void renderToBuffer() { 
		//public void render(Graphics g, int camX, int camY, int renW, int renH)
		Component.level.render(dbg, 
							(int) Component.sX, 
							(int) Component.sY, 
							(Component.pixel.width /TILE_SIZE) + 2, 
							(Component.pixel.height / TILE_SIZE) + 2);
		Component.weapon.render(dbg);
		Component.character.render(dbg);
		
		
//		Component.level.render2(dbg,(int) Component.sX, (int) Component.sY, 
//							   (Component.pixel.width /TILE_SIZE) + 2, 
//							   (Component.pixel.height / TILE_SIZE) + 2);
		
		

		for(int i = 0; i < Component.mob.toArray().length; i ++) {
			Component.mob.get(i).render(dbg);
		}

		for(int i = 0; i < Component.collectibles.toArray().length; i ++) {
			Component.collectibles.get(i).render(dbg);

		}
		
//		Component.day.render(dbg);
		Component.inventory.render(dbg);

		Component.debugger.render(dbg);
		renderMenu();
	}
	
	public void renderMenu() {
		
	}
}
