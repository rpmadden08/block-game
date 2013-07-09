package com.companyname.blockgame.GameStates;
import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.Component;
import com.companyname.blockgame.GameState;

public class MainMenuState extends GameState{
	
	public MainMenuState() {
		type = MAIN_MENU_STATE;
	}
	public void tick() {
		Component.mainMenu.tick();
	}
		
	

	public void renderToBuffer() { 
		Component.mainMenu.render(dbg);

	}
	

}