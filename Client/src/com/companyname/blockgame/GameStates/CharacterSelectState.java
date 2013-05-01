package com.companyname.blockgame.GameStates;

import com.companyname.blockgame.Character;
import com.companyname.blockgame.CharacterSelectMenu;
import com.companyname.blockgame.Component;
import com.companyname.blockgame.GameState;

public class CharacterSelectState extends GameState {
	private CharacterSelectMenu menu;
	
	public CharacterSelectState() {
		type = Component.CHARACTER_SELECT_STATE;
		Character.isMovingDown = true;
		menu = new CharacterSelectMenu();
	}
	
	public void tick() {
		Component.character.tick();
	}
	
	public void renderToBuffer() { 
		menu.render(dbg);
		Component.character.renderBig(dbg);
	}
}
