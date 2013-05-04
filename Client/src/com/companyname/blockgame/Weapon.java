package com.companyname.blockgame;

//test stuff
import java.awt.*;
import java.awt.geom.*;
//import java.lang.Math.*;

import java.awt.image.AffineTransformOp;


public class Weapon extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public int frameOffsetLeft = 5;
	public int frameWidth = 20;
	public int frameOffsetTop = 3;
	public int frameHeight = 42;
	
	public int[] id = {-1, -1};
	public boolean isInUse = false;
	public boolean isPositionedCorrectly = false; //Is sword angle positioned correctly?
	public int animation = 0;  
	public int animationFrame = 0, animationTime = 3; //probably Should be 3
	public int animationAngle0 = 0;
	public int animationAngle1 = 0;
	public int animationAngle2 = 0;
	public int animationAngle3 = 0;
	public int animationAngle4 = 0;
	
	public int animation0x = 0;
	public int animation0y = 0;
	public int animation1x = 0;
	public int animation1y = 0;
	public int animation2x = 0;
	public int animation2y = 0;
	public int animation3x = 0;
	public int animation3y = 0;
	public int animation4x = 0;
	public int animation4y = 0;
	
	public Rectangle collisionArea = bounds();
	
	public int step = 0;
	public int testLineAngle2;
	//public AffineTransformOp op2;

	
	
	public Weapon(int width, int height, int[] id) {
		setBounds((Component.pixel.width / 2) - (width / 2), (Component.pixel.height / 2) - (height / 2), width, height);
		id = this.id;
	}
	
	public Rectangle bounds() {
		return (new Rectangle(x + frameOffsetLeft,y + frameOffsetTop,frameWidth,frameHeight));
	}
	
	public void tick() {
		
		

		
		
		//getAnimationAngle(); 
		if(isInUse == true) {
			if(animationFrame >= animationTime) {
				
				
				if(step > 3) {
					isInUse = false;
					isPositionedCorrectly = false;
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
	
	public void getAnimationAngle() {
		//Get the mouse point
		Point test = new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY);
		//System.out.println("Mouse X: "+Component.mse.x);
		//System.out.println("Character X: "+Component.character.x);
		
		//Get the angle of the mouse point
		float angle = (float) Math.atan2((test.y - Component.character.y)-20, (test.x - Component.character.x)-8);
		//Convert the Radian to degrees
		angle = angle * 180 / (int) Math.PI;
	    
		if(angle < 0){
	        angle += 360;
	    } 
	    
		//testLineAngle2 = (int) angle;
		//System.out.println(angle);
		if(angle > 315 || angle < 46) {
			//right
			Character.isFacingDown = false;
			Character.isFacingUp = false;
			Character.isFacingLeft = false;
			Character.isFacingRight = true;
			animationAngle0 = 0;
			animation0x = 8;
			animation0y = -5;
			animationAngle1 = 0;
			animation1x = 8;
			animation1y = -5;
			animationAngle2 = 45;
			animation2x = 8;
			animation2y = 5;
			animationAngle3 = 90;
			animation3x = 8;
			animation3y = 12;
			animationAngle4 = 90;
			animation4x = 8;
			animation4y = 12;
			collisionArea = (new Rectangle ((int) Component.character.x - (int) Component.sX + 13,
					(int) Component.character.y - (int) Component.sY - 2,
					frameWidth,
					frameHeight
					));
			isPositionedCorrectly = true;
			
			
		} else if (angle > 45 && angle < 136) {
			//down
			Character.isFacingDown = true;
			Character.isFacingUp = false;
			Character.isFacingLeft = false;
			Character.isFacingRight = false;
			animationAngle0 = 90;
			animation0x = 8;
			animation0y = 12;
			animationAngle1 = 90;
			animation1x = 8;
			animation1y = 12;
			animationAngle2 = 135;
			animation2x = 0;
			animation2y = 18;
			animationAngle3 = 180;
			animation3x = -8;
			animation3y = 12;
			animationAngle4 = 180;
			animation4x = -8;
			animation4y = 12;
			collisionArea = (new Rectangle ((int) Component.character.x - (int) Component.sX - 13,
					(int) Component.character.y - (int) Component.sY + 25,
					frameWidth +21,
					frameHeight - 16
					));
			isPositionedCorrectly = true;
		} else if (angle > 135 && angle < 226) {
			//left
			Character.isFacingDown = false;
			Character.isFacingUp = false;
			Character.isFacingLeft = true;
			Character.isFacingRight = false;
			animationAngle0 = 180;
			animation0x = -8;
			animation0y = 12;
			animationAngle1 = 180;
			animation1x = -8;
			animation1y = 12;
			animationAngle2 = 225;
			animation2x = -8;
			animation2y = 5;
			animationAngle3 = 270;
			animation3x = -8;
			animation3y = -5;
			animationAngle4 = 270;
			animation4x = -8;
			animation4y = -5;
			collisionArea = (new Rectangle ((int) Component.character.x - (int) Component.sX -18,
					(int) Component.character.y - (int) Component.sY - 2,
					frameWidth,
					frameHeight
					));
			isPositionedCorrectly = true;
		} else if (angle > 225 && angle < 316) {
			//up
			Character.isFacingDown = false;
			Character.isFacingUp = true;
			Character.isFacingLeft = false;
			Character.isFacingRight = false;
			animationAngle0 = 270;
			animation0x = -8;
			animation0y = -5;
			animationAngle1 = 270;
			animation1x = -8;
			animation1y = -5;
			animationAngle2 = 315;
			animation2x = 0;
			animation2y = -10;
			animationAngle3 = 0;
			animation3x = 8;
			animation3y = -5;
			animationAngle4 = 0;
			animation4x = 8;
			animation4y = -5;
			collisionArea = (new Rectangle ((int) Component.character.x - (int) Component.sX - 13,
					(int) Component.character.y - (int) Component.sY - 12,
					frameWidth +21,
					frameHeight - 16
					));
			isPositionedCorrectly = true;
		}
		
	}
	

	public float getAngle() {
		Point test = new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY);
		
		float angle = (float) Math.toDegrees(Math.atan2(test.x - Component.character.x+8, test.y - Component.character.y+16));
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
		//This is lines drawn for testing purposes

		
//		int testLineAngle = 45;
//		for(int x=0; x<4;x++) {
//			int x1 = (int) Component.character.x - (int) Component.sX +8;
//			int y1 = (int) Component.character.y - (int) Component.sY + 20;
//			double angle = Math.toRadians(testLineAngle);
//			
//			double x2 = x1 - 500 * Math.cos(angle);
//			double y2 = y1 - 500 * Math.sin(angle);
//			g.setColor(new Color(0,0,0, 255));
//			g.drawLine((int)x1, (int)y1, (int) x2, (int) y2);
//			testLineAngle += 90;
//		}
			

				
		if(Inventory.invBar[Inventory.selected].id == Tile.sword && isInUse == true){ 
//			g.setColor(new Color(0,0,0, 255));
//			g.drawRect(collisionArea.x, collisionArea.y,collisionArea.width,collisionArea.height);      
			if(animation == 0) {
				double rotationRequired2 = Math.toRadians(animationAngle0);
				double locationX2 = 32;
				double locationY2 = 32;
				AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX2, locationY2);
				AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op2.filter(Tile.tile_sword, null),
						(int) Component.character.x -24- (int) Component.sX + animation0x,
						(int) Component.character.y -16 - (int) Component.sY + animation0y, 
						(int) Component.character.x + 40 - (int) Component.sX + animation0x, 
						(int) Component.character.y + 48 - (int) Component.sY + animation0y,
						0, 
						0, 
						64, 
						64, 
						null);
			}
			
			if(animation == 1) {
				double rotationRequired2 = Math.toRadians(animationAngle1);
				double locationX2 = 32;
				double locationY2 = 32;
				AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX2, locationY2);
				AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op2.filter(Tile.tile_sword, null),
						(int) Component.character.x -24- (int) Component.sX + animation1x,
						(int) Component.character.y -16 - (int) Component.sY + animation1y, 
						(int) Component.character.x + 40 - (int) Component.sX + animation1x, 
						(int) Component.character.y + 48 - (int) Component.sY  + animation1y,
						0, 
						0, 
						64, 
						64, 
						null);
				}
			
			if(animation == 2) {
				double rotationRequired2 = Math.toRadians(animationAngle2);
				double locationX2 = 32;
				double locationY2 = 32;
				AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX2, locationY2);
				AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op2.filter(Tile.tile_sword, null),
						(int) Component.character.x -24- (int) Component.sX + animation2x,
						(int) Component.character.y -16 - (int) Component.sY + animation2y, 
						(int) Component.character.x + 40 - (int) Component.sX + animation2x, 
						(int) Component.character.y + 48 - (int) Component.sY + animation2y,
						0, 
						0, 
						64, 
						64, 
						null);
					}
			if(animation == 3) {
				double rotationRequired2 = Math.toRadians(animationAngle3);
				double locationX2 = 32;
				double locationY2 = 32;
				AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX2, locationY2);
				AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op2.filter(Tile.tile_sword, null),
						(int) Component.character.x -24- (int) Component.sX + animation3x,
						(int) Component.character.y -16 - (int) Component.sY + animation3y, 
						(int) Component.character.x + 40 - (int) Component.sX + animation3x, 
						(int) Component.character.y + 48 - (int) Component.sY + animation3y,
						0, 
						0, 
						64, 
						64, 
						null);
				}
			
			if(animation == 4) {
				double rotationRequired2 = Math.toRadians(animationAngle4);
				double locationX2 = 32;
				double locationY2 = 32;
				AffineTransform tx2 = AffineTransform.getRotateInstance(rotationRequired2, locationX2, locationY2);
				AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
				g.drawImage(op2.filter(Tile.tile_sword, null),
						(int) Component.character.x -24- (int) Component.sX + animation4x,
						(int) Component.character.y -16 - (int) Component.sY + animation4y, 
						(int) Component.character.x + 40 - (int) Component.sX+ animation4x, 
						(int) Component.character.y + 48 - (int) Component.sY + animation4y,
						0, 
						0, 
						64, 
						64, 
						null);
				}
		}
	}
	
}
