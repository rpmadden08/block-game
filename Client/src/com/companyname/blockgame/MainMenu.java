package com.companyname.blockgame;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.image.*;

import javax.imageio.*;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.BlockTypes.*;

public class MainMenu {
	public static int mainMenuLength = 4;
	public MainMenuButton[] mainMenuButtons = new MainMenuButton[mainMenuLength];
	
	public MainMenu() {
		mainMenuButtons[0] = new MainMenuButton(0,"NEW GAME", 24);
		mainMenuButtons[1] = new MainMenuButton(1,"LOAD GAME", 48 );
		mainMenuButtons[2] = new MainMenuButton(2,"OPTIONS", 72);
		mainMenuButtons[3] = new MainMenuButton(3,"QUIT", 96);
		
	}
	
	public void tick() {
		for(int x=0; x<mainMenuLength;x++) {
			mainMenuButtons[x].tick();
		}
		
	}
	
	public void buttonClicked(int c) {
		switch(c) {
		case 0:
			System.out.println("New Game");
			break;
		case 1:
			System.out.println("Load Game");
			break;
		case 2:
			System.out.println("Options");
			break;
		case 3:
			System.out.println("Quit");
			break;
			
		}
			
	}
	public void render(Graphics g) {
		for(int x=0; x<mainMenuLength;x++) {
			mainMenuButtons[x].render(g);
		}
	}
}


