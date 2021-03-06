package com.companyname.blockgame;

import java.io.*;

import static com.companyname.blockgame.Constants.*;



public class SaveGame{

	public SaveGame(){
	}
	
	public void saveChunk(int chunkX, int chunkY, int saveX, int saveY) { //chunkX and chunkY is the filename and actual chunk location.  saveX and saveY references where in the level it is saving from...
		
		// Create some data objects for us to save.
		try{  // Catch errors in I/O if necessary.
			// Open a file to write to, named SavedObj.sav.
			FileOutputStream saveFile = new FileOutputStream("chunks/"+chunkX+"-"+chunkY+".sav");
			//System.out.println("Working Directory = " + System.getProperty("user.dir"));
			// Create an ObjectOutputStream to put objects into save file.
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			//System.out.println(Component.level.chunk.length);
			// Now we do the save.
			for(int y2=0; y2<CHUNCK_SIZE;y2++) {
				for(int x2=0; x2<CHUNCK_SIZE;x2++) {
					save.writeObject(Component.level.chunk[saveX][saveY].cBlock[x2][y2].baseId);
					
					save.writeObject(Component.level.chunk[saveX][saveY].cBlock[x2][y2].x);
					save.writeObject(Component.level.chunk[saveX][saveY].cBlock[x2][y2].y);
					
				}
			}
			//save.writeObject(Component.character.HP);
			
			// Close the file.
			save.close(); // This also closes saveFile.
		}
		catch(Exception exc){
			exc.printStackTrace();
			System.out.println("Chunk DID NOT Save for some reason!");
		}
	}
	
	public void loadChunk(int chunkX, int chunkY) {
		// Wrap all in a try/catch block to trap I/O errors.
		try{
		// Open file to read from, named SavedObj
		FileInputStream saveFile = new FileInputStream("chunks/"+chunkX+"-"+chunkY+".sav");
		// Create an ObjectInputStream to get objects from save file.
		ObjectInputStream save = new ObjectInputStream(saveFile);

		// Now we do the restore.
		// readObject() returns a generic Object, we cast those back
		// into their original class type.
		// For primitive types, use the corresponding reference class.
		

		Component.level.tempChunk = new Chunk(chunkX,chunkY);

		
		for(int y2=0; y2<CHUNCK_SIZE;y2++) {
			for(int x2=0; x2<CHUNCK_SIZE;x2++) {
				BlockTypess test2 = new BlockTypess();
				int[] baseId = (int[]) save.readObject();
				String test18 = Integer.toString(baseId[0])+Integer.toString(baseId[1]);
				test2.x1 = (Integer) save.readObject()/TILE_SIZE;
				test2.y1 = (Integer) save.readObject()/TILE_SIZE;
				test2.newBlock();
				//System.out.println(test2.example.get(test18));
				Component.level.tempChunk.cBlock[x2][y2] = test2.example.get(test18);
				//Component.level.tempChunk.cBlock[x2][y2].hiLite = true;
				//Component.level.block[x][y] = new Block(new Rectangle(x * TILE_SIZE, y * TILE_SIZE,TILE_SIZE, TILE_SIZE),baseId);
					
			}
		}
		for(int y=1; y<Component.level.block.length-1;y++) {
			for(int x=1; x<Component.level.block[0].length-1;x++) {
					Component.level.checkAutoTile(x, y);
			}
		}
		
		//Component.character.HP = HP;
//		x = (Integer) save.readObject();
//		name = (String) save.readObject();
//		stuff = (ArrayList) save.readObject();

		// Close the file.
		
		save.close(); // This also closes saveFile.
		saveFile.close();
		}
		catch(Exception exc){
			exc.printStackTrace(); // If there was an error, print the info.
			System.out.println("FAILURE!");
		}
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
					save.writeObject(Component.level.block[x][y].baseId);
					
				}
			}
			//save.writeObject(Component.character.HP);
			
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
				BlockTypess test2 = new BlockTypess();
				int[] baseId = (int[]) save.readObject();
				String test18 = Integer.toString(baseId[0])+Integer.toString(baseId[1]);
				test2.x1 = x;
				test2.y1 = y;
				test2.newBlock();
				//System.out.println(test2.example.get(test18));
				Component.level.block[x][y] = test2.example.get(test18);
				//Component.level.block[x][y] = new Block(new Rectangle(x * TILE_SIZE, y * TILE_SIZE,TILE_SIZE, TILE_SIZE),baseId);
					
			}
		}
		for(int y=1; y<Component.level.block.length-1;y++) {
			for(int x=1; x<Component.level.block[0].length-1;x++) {
					Component.level.checkAutoTile(x, y);
			}
		}
		
		//Component.character.HP = HP;
//		x = (Integer) save.readObject();
//		name = (String) save.readObject();
//		stuff = (ArrayList) save.readObject();

		// Close the file.
		
		save.close(); // This also closes saveFile.
		saveFile.close();
		}
		catch(Exception exc){
			exc.printStackTrace(); // If there was an error, print the info.
			System.out.println("FAILURE!");
		}

		// Print the values, to see that they've been recovered.

		// All done.
	}
}

