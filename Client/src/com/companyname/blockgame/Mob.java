package com.companyname.blockgame;

import java.awt.*;
import java.util.*;

public class Mob extends DoubleRectangle {
	public int[] id;
	
	public double maxSpeed = .5;
	public double knockBackSpeed = .5;
	public boolean isJumping = false;
	public boolean isMoving = false;
	public boolean isFalling = false;
	
	
	public double movementSpeed = 0.4;
	public double fallingSpeed = 1;
	public double jumpingSpeed = 8;
	//public double dir = movementSpeed;
	public int jumpingHeight = 8, jumpingCount = 0;
	public int animation = 0;  
	public int animationFrame = 0, animationTime = 25;
	public int step = 0;
	public int[] stepArr = {0,1,0,2};
	public int stepsToTake = 0;
	public int dir;
	public Random rand = new Random(1);
	public Random rand2 = new Random(2);
	
	public boolean isKnockingBack = false;
	
	public double moveX = 0;
	public double moveY = 0;
	
	public double vX;
	public double vY;
	
	public static double frameOffsetLeft = 4;
	public static double frameOffsetRight = 11;
	public static double frameOffsetBottom = 31;
	public static double frameOffsetTop = 23;
	
	public static boolean isMovingUp = false;
	public static boolean isMovingDown = false;
	public static boolean isMovingRight = false;
	public static boolean isMovingLeft = false;
	
	public static boolean isFacingUp = false;
	public static boolean isFacingDown = true;
	public static boolean isFacingRight = false;
	public static boolean isFacingLeft = false;
	
	
	public Mob(int x, int y, int width, int height, int[] id) {
		setBounds(x,y,width,height);
		this.id = id;
	}
	
	public Rectangle bounds() {
		return (new Rectangle((int)x - (int) Component.sX,(int)y+13- (int)Component.sY,(int) width,(int)height-13));
	}
	
	public double getCollisionX() {
		double horizontalExtra = 0;
		double x1 = Math.floor((x+frameOffsetLeft + moveX) /Tile.tileSize);
		double x2 = Math.floor((x+frameOffsetRight + moveX) /Tile.tileSize);
		double y1 = Math.floor((y+frameOffsetTop) /Tile.tileSize);
		double y2 = Math.floor((y+frameOffsetBottom) /Tile.tileSize);
		
		if(isMovingLeft) {
			if(Component.level.block[(int)x1][(int)y1].isPassable == false || Component.level.block[(int)x1][(int)y2].isPassable == false) {
				horizontalExtra = (x + frameOffsetLeft) - x1 * Tile.tileSize - Tile.tileSize;
				return Math.min(0, -(horizontalExtra));
			}
		}
		if(isMovingRight) {
			if(Component.level.block[(int)x2][(int)y1].isPassable == false || Component.level.block[(int)x2][(int)y2].isPassable == false) {
				horizontalExtra = x2 * Tile.tileSize - (x + frameOffsetRight) - 1;
				return Math.max(0, horizontalExtra);
			}
		}
		return moveX;
	}
	
	public double getCollisionY() {
		double verticalExtra = 0;
		double x1 = Math.floor((x+frameOffsetLeft) /Tile.tileSize);
		double x2 = Math.floor((x+frameOffsetRight) /Tile.tileSize);
		double y1 = Math.floor((y+frameOffsetTop + moveY) /Tile.tileSize);
		double y2 = Math.floor((y+frameOffsetBottom + moveY) /Tile.tileSize);
		
		if(isMovingDown) {
			if(Component.level.block[(int)x2][(int)y2].isPassable == false || Component.level.block[(int)x1][(int)y2].isPassable == false) {
				verticalExtra = y2 * Tile.tileSize - (y + frameOffsetBottom) - 1;
				return Math.max(0, verticalExtra);
				
			}
		}
		if(isMovingUp) {
			if(Component.level.block[(int)x1][(int)y1].isPassable == false || Component.level.block[(int)x2][(int)y1].isPassable == false) {
				verticalExtra = (y + frameOffsetTop) - y1 * Tile.tileSize - Tile.tileSize;
				return Math.min(0, -(verticalExtra));
			}
		}
		return moveY;	
	}
	
	public void knockBack() {
		float angle = (float) Math.atan2(((y+24- (int) Component.sY) - (Component.character.y+20-(int) Component.sY)), 
				((x+8 -(int) Component.sX) - (Component.character.x+8 - (int) Component.sX)));
		//Convert the Radian to degrees
		angle = angle * 180 / (int) Math.PI;
		if(angle < 0){
	        angle += 360;
	    } 
		
		
		
		double newX = ((int) x) + 100 * Math.cos(Math.toRadians(angle));
		double newY = ((int) y) + 100 * Math.sin(Math.toRadians(angle));
		
		vX = newX - x;
		vY = newY - y;
		
		double length = Math.sqrt((vX*vX)+(vY*vY));
		
		vX = vX/length;
		vY = vY/length;
		
		
		
		//isKnockingBack = false;
	}
	
	public void tick() {
		//Collision with main characters weapon.  
		if(Component.weapon.isInUse == true && Component.weapon.isPositionedCorrectly == true) {
			Rectangle rectangle1 = Component.weapon.collisionArea;
			Rectangle rectangle2 = bounds();
			if (rectangle1.intersects(rectangle2)) {
				//System.out.println("Collission Detected");
				isKnockingBack = true;
				knockBack();
			}
		}
		
		if(isKnockingBack == true) {
			x = x+(vX*knockBackSpeed);
			y = y+(vY*knockBackSpeed);
			
		}

		if(stepsToTake == 0) {
			//Calculate a random number of steps
			stepsToTake = rand.nextInt(50) + 20;
			//Calculate a random direction
			dir = rand2.nextInt(360) + 1;
			System.out.println(dir);
			
		}
		stepsToTake = stepsToTake - 1;
		//stepsToTake = 1;
		
		
		
		//dir = 270;
		
		
		
		if(isKnockingBack == false) {
			if(dir > 180 && dir < 360) {
				Mob.isMovingUp = true;
				Mob.isMovingDown = false;
				Mob.isFacingUp = true;
				Mob.isFacingDown = false;
			}
			if(dir > 0 && dir < 180) {
				Mob.isMovingUp = false;
				Mob.isMovingDown = true;
				Mob.isFacingUp = false;
				Mob.isFacingDown = true;
			}
			if(dir > 90 && dir < 270) {
				Mob.isMovingLeft = true;
				Mob.isMovingRight = false;
				Mob.isFacingLeft = true;
				Mob.isFacingRight = false;
			}
			if(dir > 270 || dir < 90) {
				Mob.isMovingLeft = false;
				Mob.isMovingRight = true;
				Mob.isFacingLeft = false;
				Mob.isFacingRight = true;
			}
			
			if(Inventory.isOpen == false) {
				boolean canMove = false;
				moveX = 0;
				moveY = 0;
				
				
				double newX = ((int) x) + 100 * Math.cos(Math.toRadians(dir));
				double newY = ((int) y) + 100 * Math.sin(Math.toRadians(dir));
				
				vX = newX - x;
				vY = newY - y;
				
				double length = Math.sqrt((vX*vX)+(vY*vY));
				
				vX = vX/length;
				vY = vY/length;
				
				moveX = (vX*maxSpeed);
				moveY = (vY*maxSpeed);
	
				
//				if(isMovingRight) {
//					moveX += maxSpeed;
//					if(Component.weapon.isInUse == false) {
//						Mob.isFacingUp = false;
//						Mob.isFacingDown = false;
//						Mob.isFacingLeft = false;
//						Mob.isFacingRight = true;
//					}
//				}
//				if(isMovingLeft) {
//					moveX += -maxSpeed;
//					if(Component.weapon.isInUse == false) {
//						Mob.isFacingUp = false;
//						Mob.isFacingDown = false;
//						Mob.isFacingLeft = true;
//						Mob.isFacingRight = false;
//					}
//				}
//				if(isMovingDown) {
//					moveY += maxSpeed;
//					if(Component.weapon.isInUse == false) {
//						Mob.isFacingUp = false;
//						Mob.isFacingDown = true;
//						Mob.isFacingLeft = false;
//						Mob.isFacingRight = false;
//					}
//				}
//				if(isMovingUp) {
//					moveY += -maxSpeed;
//					if(Component.weapon.isInUse == false) {
//						Mob.isFacingUp = true;
//						Mob.isFacingDown = false;
//						Mob.isFacingLeft = false;
//						Mob.isFacingRight = false;
//					}
//				}
				moveX = getCollisionX();
				moveY = getCollisionY();
				
	
				
				if(!canMove) {
					System.out.println(moveX);
					System.out.println(moveY);
					x += moveX;
					y += moveY;
					
					if(animationFrame >= animationTime) {
						
						
						if(step > 2) {
							step = 0;
						} else {
							step += 1;
							
						}
						animation = stepArr[step];
						animationFrame = 0;
					} else {
						animationFrame += 1;
					}
				}
				
			} else {
				animation = 0;
			}
			
		}
	}
	
	public boolean isCollidingWithBlock(Point pt1, Point pt2) {
		
		for(int x=(int) (this.x/Tile.tileSize);x<(int) (this.x/Tile.tileSize) +3;x++) {
			for(int y=(int) (this.y/Tile.tileSize);y<(int) (this.y/Tile.tileSize) +3;y++) {
				if(x >= 0 && y>= 0 && x < Component.level.block.length && y < Component.level.block[0].length) {
					if(Component.level.block[x][y].id != Tile.air) {
						if(Component.level.block[x][y].contains(pt1) || Component.level.block[x][y].contains(pt2)) {
							return true;
							
						}
					}
				}
			}
		}
		return false;
	}
	
	public void render(Graphics g) {
			g.drawImage(Tile.tileset_terrain,
					(int) x - (int) Component.sX,
					(int) y - (int) Component.sY,
					(int) (x + width) - (int) Component.sX,
					(int) (y + height) - (int) Component.sY,
					(id[0] * Tile.tileSize) + (Tile.tileSize*animation),
					id[1] * Tile.tileSize,
					id[0] * Tile.tileSize +(Tile.tileSize * animation) + (int) width,
					id[1] * Tile.tileSize +(int) height,
					null);
//			g.setColor(new Color(255,0,0, 255));
//			g.drawRect ((int)x - (int) Component.sX +(int)frameOffsetLeft,
//					(int)y+13- (int)Component.sY + (int)frameOffsetTop,
//					(int) width+ (int)frameOffsetRight,
//					(int)height-13+ (int)frameOffsetBottom);
			
//			g.setColor(new Color(0,255,0, 255));
//			g.drawRect (
//					(int) x + (int)frameOffsetLeft,
//					(int) y + (int)frameOffsetTop,
//					(int) width,
//					(int) height);
			
			g.setColor(new Color(0,0,0, 255));
			g.drawLine((int)x+8 -(int) Component.sX, (int)y+24- (int) Component.sY, (int) Component.character.x+8 -(int) Component.sX, (int) Component.character.y+20- (int) Component.sY);

		
	}
}
