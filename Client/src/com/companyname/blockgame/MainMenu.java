package com.companyname.blockgame;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.image.*;

import javax.imageio.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static com.companyname.blockgame.Constants.*;
import com.companyname.blockgame.BlockTypes.*;



public class MainMenu {
	public static int mainMenuLength = 4;
	public MainMenuButton[] mainMenuButtons = new MainMenuButton[mainMenuLength];
	public MainMenuButton[] mainMenuButtons2;
	public Component component;
	public TextField textField;
	public int mainMenuSection = 0;
	public MainMenuButton createNewWorld;
	public String[] files;
	
	public MainMenu(Component component) {
		mainMenuButtons[0] = new MainMenuButton(0,"NEW GAME", 24);
		mainMenuButtons[1] = new MainMenuButton(1,"LOAD GAME", 48 );
		mainMenuButtons[2] = new MainMenuButton(2,"OPTIONS", 72);
		mainMenuButtons[3] = new MainMenuButton(3,"QUIT", 96);
		this.component = component;
		
		File file = new File("saves/");
		files = file.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File dir, String name) {
			    return new File(dir, name).isDirectory();
			  }
			});
		int y = 24;
		mainMenuButtons2 = new MainMenuButton[files.length];
		for(int x=0; x<files.length;x++) {
			mainMenuButtons2[x] = new MainMenuButton(x,files[x], y);
			y = y +24;
		}
		
	}
	
	public void tick() {
		if(mainMenuSection == 0) {
			for(int x=0; x<mainMenuLength;x++) {
				mainMenuButtons[x].tick();
			}
		} else if(mainMenuSection == 1){
			createNewWorld.tick();
		} else if(mainMenuSection == 2){
			for(int x=0; x<files.length;x++) {
				mainMenuButtons2[x].tick();
			}
		}
		
	}
	
	public void buttonClicked(int c) {
		switch(c) {
		case 0://NEW GAME
			
			mainMenuSection = 1;

			textField  = new TextField("New World",30);
			component.add(textField);
			Font font = new Font("Ariel", Font.PLAIN, 22);
			
			
			textField.requestFocus();
			textField.setFont(font);
			textField.setSize(300,23);
			textField.setLocation(250,24);
			createNewWorld = new MainMenuButton(11, "CREATE NEW WORLD", 48);
			
			
			break;
			
		case 1://LOAD GAME
			mainMenuSection = 2;
			break;
			
		case 2://OPTIONS
			break;
			
		case 3: //QUIT
			break;
			
		case 11: //  STARTING A NEW WORLD
			//System.out.println("Create New World");
			

			
			File f = new File("saves/"+textField.getText());
			
			
			if(f.exists()) {
				System.out.println("GameSave Already exists!");
			} else {
				new File("saves/"+textField.getText()+"/map/chunks").mkdirs();
				component.remove(textField);
				component.changeListener(0);
				component.startGame();
				Component.level.saveName = textField.getText();
				Component.level.saveCurrentRender();
			}
			break;
		}
			
	}
	public void render(Graphics g) {
		//System.out.println(mainMenuOn);
		if(mainMenuSection == 0) {
			for(int x=0; x<mainMenuLength;x++) {
				mainMenuButtons[x].render(g);
			}
		} else if (mainMenuSection == 1){
			createNewWorld.render(g);
		} else if (mainMenuSection == 2){
			for(int x=0; x<files.length;x++) {
				mainMenuButtons2[x].render(g);
			}
		}
		//textField.render(g);
	}
}


