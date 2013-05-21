package com.companyname.blockgame;

import java.awt.Rectangle;
import java.io.*;


public class SaveGame{

	public SaveGame(){
	}
	public void saveSave() {
	
		// Create some data objects for us to save.
		//int HP2 = 3;
		try{  // Catch errors in I/O if necessary.
			// Open a file to write to, named SavedObj.sav.
			FileOutputStream saveFile=new FileOutputStream("SaveObj.sav");
			System.out.println("Working Directory = " +
		              System.getProperty("user.dir"));
			// Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			// Now we do the save.
			for(int y=0; y<Component.level.block.length;y++) {
				for(int x=0; x<Component.level.block[0].length;x++) {
					save.writeObject(Component.level.block[x][y].id);
				}
			}
			save.writeObject(Component.character.HP);
//			public int[] id = {-1, -1};
//			public int[] baseId = {-1, -1};
//			public int[] dropId = {-1, -1};
//			public int autoTile = 0;
//			public int[] autoTile1 = {0, 0};
//			public int[] autoTile2 = {0, 0};
//			public int[] autoTile3 = {0, 0};
//			public int[] autoTile4 = {0, 0};
//			
//			public boolean isPassable = true;
//			public int maxHitPoints = 25;
//			public int hitPoints = 25;
//			public boolean isMouseTouching = false;
//			public boolean isDigAnimationVisible = false;
//			
//			public float heightMap = 50;
			
			// Close the file.
			save.close(); // This also closes saveFile.
		}
		catch(Exception exc){
			System.out.println("Didn't work");
		}
	}
	public void loadSave() {
		// Wrap all in a try/catch block to trap I/O errors.
		try{
		// Open file to read from, named SavedObj.sav.
		FileInputStream saveFile = new FileInputStream("SaveObj.sav");
		// Create an ObjectInputStream to get objects from save file.
		ObjectInputStream save = new ObjectInputStream(saveFile);

		// Now we do the restore.
		// readObject() returns a generic Object, we cast those back
		// into their original class type.
		// For primitive types, use the corresponding reference class.
		for(int y=0; y<Component.level.block.length;y++) {
			for(int x=0; x<Component.level.block[0].length;x++) {
				int[] id = (int[]) save.readObject();
				Component.level.block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize,Tile.tileSize, Tile.tileSize),id);
				Component.level.checkAutoTile(x, y);
			}
		}
		
		
		//Component.character.HP = HP;
//		x = (Integer) save.readObject();
//		name = (String) save.readObject();
//		stuff = (ArrayList) save.readObject();

		// Close the file.
		save.close(); // This also closes saveFile.
		}
		catch(Exception exc){
		exc.printStackTrace(); // If there was an error, print the info.
		}

		// Print the values, to see that they've been recovered.

		// All done.
	}
}

