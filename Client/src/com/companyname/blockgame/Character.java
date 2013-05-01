package com.companyname.blockgame;

import java.awt.*;
import java.util.Random;

public class Character extends DoubleRectangle {
	public double fallingSpeed = 1;
	public double movementSpeed = 1;
	public double maxSpeed = 1;
	public double speed = maxSpeed;
	public double knockBackSpeed = 3;
	public double jumpingSpeed = 8;
	public int maxHP = 20;
	public int HP = maxHP;
	public int Attack = 5;
	public Rectangle collisionArea = bounds();
	
	public static boolean isMovingUp = false;
	public static boolean isMovingDown = false;
	public static boolean isMovingRight = false;
	public static boolean isMovingLeft = false;
	
	public static boolean isFacingUp = false;
	public static boolean isFacingDown = true;
	public static boolean isFacingRight = false;
	public static boolean isFacingLeft = false;
	
	public static boolean isRunning = false;
	
	public boolean isKnockingBack = false;
	public int stepsToTake = 0;
	public int dir;
	public Random rand = new Random();
	public Random rand2 = new Random();
	public double vX;
	public double vY;
	
	public int xVelocity = 0;
	public int yVelocity = 0;
	public int offsetX = 0;
	public int offsetY = 0;
	public int xDistance = 0;
	public int yDistance = 0;
	public int step = 0;
	public int[] stepArr = {0,1,0,2};
	
	public double moveX = 0;
	public double moveY = 0;
	
	//1,14,31,16
	public static double frameOffsetLeft = 1;
	public static double frameOffsetRight = 14;
	public static double frameOffsetBottom = 31;
	public static double frameOffsetTop = 16;
	
	public int jumpingHeight = 8, jumpingCount = 0;
	public int animation = 0;  
	public int animationFrame = 0, animationTime = 10;
	
	public Character(int width, int height) {
		setBounds((Component.pixel.width / 2) - (width / 2), (Component.pixel.height / 2) - (height / 2), width, height);
	}
	
	public void knockBack(int mobX, int mobY) {
//		x = x+(vX*knockBackSpeed);
//		y = y+(vY*knockBackSpeed);
		//getKnockBackDir
//		float angle = (float) Math.atan2(((y+24- (int) Component.sY) - (Component.character.y+20-(int) Component.sY)), 
//				((x+8 -(int) Component.sX) - (Component.character.x+8 - (int) Component.sX)));
		float angle = (float) Math.atan2(((y+20-(int) Component.sY)-(mobY+24- (int) Component.sY)), 
				((x+8 - (int) Component.sX)-(mobX+8 -(int) Component.sX)));
		//Convert the Radian to degrees
		angle = angle * 180 / (int) Math.PI;
		if(angle < 0){
	        angle += 360;
	    } 
		dir = (int) angle;
		speed = knockBackSpeed;
		stepsToTake = 10;
		
	}
	
	public void tick() {
		if(isKnockingBack == true) {
			if(stepsToTake == 0) {
				if(isKnockingBack == true) {
					isKnockingBack = false;
				}
				speed = maxSpeed;
				//Calculate a random number of steps
				stepsToTake = rand.nextInt(50) + 20;
				//Calculate a random direction
				dir = rand2.nextInt(360) + 1;
				
				
			}
		
		stepsToTake = stepsToTake - 1;
		//stepsToTake = 1;
		
		
		
		//dir = 270;
		
		
		
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
				
				moveX = (vX*speed);
				moveY = (vY*speed);
	
				

				moveX = getCollisionX();
				moveY = getCollisionY();
				
	
				
				if(!canMove) {
					
					x += moveX;
					y += moveY;
					Component.sX += moveX;
					Component.sY += moveY;
					
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
		} else if(isMovingLeft || isMovingRight || isMovingUp || isMovingDown && Inventory.isOpen == false) {
				boolean canMove = false;
				moveX = 0;
				moveY = 0;
	
				
				if(isMovingRight) {
					moveX += maxSpeed;
					if(Component.weapon.isInUse == false) {
						Character.isFacingUp = false;
						Character.isFacingDown = false;
						Character.isFacingLeft = false;
						Character.isFacingRight = true;
					}
				}
				if(isMovingLeft) {
					moveX += -maxSpeed;
					if(Component.weapon.isInUse == false) {
						Character.isFacingUp = false;
						Character.isFacingDown = false;
						Character.isFacingLeft = true;
						Character.isFacingRight = false;
					}
				}
				if(isMovingDown) {
					moveY += maxSpeed;
					if(Component.weapon.isInUse == false) {
						Character.isFacingUp = false;
						Character.isFacingDown = true;
						Character.isFacingLeft = false;
						Character.isFacingRight = false;
					}
				}
				if(isMovingUp) {
					moveY += -maxSpeed;
					if(Component.weapon.isInUse == false) {
						Character.isFacingUp = true;
						Character.isFacingDown = false;
						Character.isFacingLeft = false;
						Character.isFacingRight = false;
					}
				}
				moveX = getCollisionX();
				moveY = getCollisionY();
				
	
				
				if(!canMove) {
					
					x += moveX;
					y += moveY;
					Component.sX += moveX;
					Component.sY += moveY;
					
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
	
	public Rectangle bounds() {
		return (new Rectangle((int) x + (int) frameOffsetLeft -(int)Component.sX,(int) y + 9- (int)Component.sY,(int) frameOffsetRight,(int)frameOffsetBottom - 8));
	  //return (new Rectangle(x + frameOffsetLeft,y + frameOffsetTop,frameWidth,frameHeight));
	}
	
	public void render(Graphics g) {
		if (isFacingDown) {
			g.drawImage(Tile.tileset_terrain, 
					(int) x - (int) Component.sX, 
					(int) y - (int) Component.sY, 
					(int) (x + width) - (int) Component.sX, 
					(int) (y + height) - (int) Component.sY, 
					/*Where it's cut out*/
					(Tile.character[0] * Tile.tileSize) + (Tile.tileSize*animation), 
					Tile.character[1] * Tile.tileSize, 
					Tile.character[0] * Tile.tileSize +(Tile.tileSize * animation) + (int) width, 
					Tile.character[1] * Tile.tileSize +(int) height, 
					null);
		} else if(isFacingRight) {
			int a = animation + 3;
			g.drawImage(Tile.tileset_terrain, 
					(int) x - (int) Component.sX, 
					(int) y - (int) Component.sY, 
					(int) (x + width) - (int) Component.sX, 
					(int) (y + height) - (int) Component.sY, 
					/*Where it's cut out*/
					(Tile.character[0] * Tile.tileSize) + (Tile.tileSize*a), 
					Tile.character[1] * Tile.tileSize, 
					Tile.character[0] * Tile.tileSize +(Tile.tileSize * a) + (int) width, 
					Tile.character[1] * Tile.tileSize +(int) height, 
					null);
		} else if(isFacingUp) {
			int a = animation + 6;
			g.drawImage(Tile.tileset_terrain, 
					(int) x - (int) Component.sX, 
					(int) y - (int) Component.sY, 
					(int) (x + width) - (int) Component.sX, 
					(int) (y + height) - (int) Component.sY, 
					/*Where it's cut out*/
					(Tile.character[0] * Tile.tileSize) + (Tile.tileSize*a), 
					Tile.character[1] * Tile.tileSize, 
					Tile.character[0] * Tile.tileSize +(Tile.tileSize * a) + (int) width, 
					Tile.character[1] * Tile.tileSize +(int) height, 
					null);
		} else if (isFacingLeft || isMovingLeft) {
			int a = animation + 9;
			g.drawImage(Tile.tileset_terrain, 
					(int) x - (int) Component.sX, 
					(int) y - (int) Component.sY, 
					(int) (x + width) - (int) Component.sX, 
					(int) (y + height) - (int) Component.sY, 
					/*Where it's cut out*/
					(Tile.character[0] * Tile.tileSize) + (Tile.tileSize*a), 
					Tile.character[1] * Tile.tileSize, 
					Tile.character[0] * Tile.tileSize +(Tile.tileSize * a) + (int) width, 
					Tile.character[1] * Tile.tileSize +(int) height, 
					null);
		} else {
			g.drawImage(Tile.tileset_terrain, 
					//Where to place the character
					(int) x - (int) Component.sX, 
					(int) y - (int) Component.sY, 
					(int) (x + width) - (int) Component.sX, 
					(int) (y + height) - (int) Component.sY, 
					/*Where it's cut out*/
					(Tile.character[0] * Tile.tileSize) + (Tile.tileSize*animation), 
					Tile.character[1] * Tile.tileSize, 
					Tile.character[0] * Tile.tileSize +(Tile.tileSize * animation) + (int) width, 
					Tile.character[1] * Tile.tileSize +(int) height, 
					null);
			
		}
	}
}
