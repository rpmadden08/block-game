package net.ulixava.adventurecraft;


import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

import java.awt.image.AffineTransformOp;


public class Weapon extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public int[] id = {-1, -1};
	public boolean isInUse = false;
	public int animation = 0;  
	public int animationFrame = 0, animationTime = 10;
	public int step = 0;
	//public AffineTransformOp op2;

	
	
	public Weapon(int width, int height, int[] id) {
		setBounds((Component.pixel.width / 2) - (width / 2), (Component.pixel.height / 2) - (height / 2), width, height);
		id = this.id;
	}
	public void tick() {
		if(isInUse == true) {
			if(animationFrame >= animationTime) {
				
				
				if(step > 3) {
					isInUse = false;
					step = 0;
					animationFrame = 0;
				} else {
					step += 1;
					
				}
				animation = step;
				animationFrame = 0;
			} else {
				animationFrame += 1;
			}
		}
	}
	public float getAngle() {
		Point test = new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY);
		
		float angle = (float) Math.toDegrees(Math.atan2(test.y - Component.character.y+16,test.x - Component.character.x+8));
		angle += 45;
	    
		if(angle < 0){
	        angle += 360;
	    } 
	    if (angle > 360) {
	    	angle -= 360;
	    }
		return angle;
		
	}
	
	public void render(Graphics g) {
		System.out.println(animationFrame);
				
		if(Inventory.invBar[Inventory.selected].id == Tile.sword && isInUse == true){ 
			//isInUse = false;
			if(animation == 0) {
				double rotationRequired2 = Math.toRadians(getAngle() - 40);
				double locationX2 = 32;
				double locationY2 = 32;
				AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX2, locationY2);
				AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op2.filter(Tile.tile_sword, null),
						(int) Component.character.x -24- (int) Component.sX,
						(int) Component.character.y -16 - (int) Component.sY, 
						(int) Component.character.x + 40 - (int) Component.sX, 
						(int) Component.character.y + 48 - (int) Component.sY,
						0, 
						0, 
						64, 
						64, 
						null);
			}
			
			if(animation == 1) {
				double rotationRequired2 = Math.toRadians(getAngle()- 20);
				double locationX2 = 32;
				double locationY2 = 32;
				AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX2, locationY2);
				AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op2.filter(Tile.tile_sword, null),
						(int) Component.character.x -24- (int) Component.sX,
						(int) Component.character.y -16 - (int) Component.sY, 
						(int) Component.character.x + 40 - (int) Component.sX, 
						(int) Component.character.y + 48 - (int) Component.sY,
						0, 
						0, 
						64, 
						64, 
						null);
			}
			
			if(animation == 2) {
				double rotationRequired2 = Math.toRadians(getAngle());
				double locationX2 = 32;
				double locationY2 = 32;
				AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX2, locationY2);
				AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op2.filter(Tile.tile_sword, null),
						(int) Component.character.x -24- (int) Component.sX,
						(int) Component.character.y -16 - (int) Component.sY, 
						(int) Component.character.x + 40 - (int) Component.sX, 
						(int) Component.character.y + 48 - (int) Component.sY,
						0, 
						0, 
						64, 
						64, 
						null);
			}
			if(animation == 3) {
				double rotationRequired2 = Math.toRadians(getAngle()+ 20);
				double locationX2 = 32;
				double locationY2 = 32;
				AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX2, locationY2);
				AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op2.filter(Tile.tile_sword, null),
						(int) Component.character.x -24- (int) Component.sX,
						(int) Component.character.y -16 - (int) Component.sY, 
						(int) Component.character.x + 40 - (int) Component.sX, 
						(int) Component.character.y + 48 - (int) Component.sY,
						0, 
						0, 
						64, 
						64, 
						null);
			}
			
			if(animation == 4) {
				double rotationRequired2 = Math.toRadians(getAngle()+ 40);
				double locationX2 = 32;
				double locationY2 = 32;
				AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX2, locationY2);
				AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op2.filter(Tile.tile_sword, null),
						(int) Component.character.x -24- (int) Component.sX,
						(int) Component.character.y -16 - (int) Component.sY, 
						(int) Component.character.x + 40 - (int) Component.sX, 
						(int) Component.character.y + 48 - (int) Component.sY,
						0, 
						0, 
						64, 
						64, 
						null);
			}
		}
	}
	
}
