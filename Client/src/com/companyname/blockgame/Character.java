package com.companyname.blockgame;

import java.awt.*;
import java.util.Random;

public class Character extends DoubleRectangle {
	public double fallingSpeed = 1;
	public double movementSpeed = 1;
	public double maxSpeed = 5; //Should be 1
	public double speed = maxSpeed;
	public double knockBackSpeed = 3;
	public double jumpingSpeed = 8;
	public int maxHP = 20;
	public int HP = maxHP;
	public int Attack = 5;
	public Rectangle collisionArea = bounds();
	public int jumpingHeight = 8, jumpingCount = 0;
	public int animation = 0;  
	public int animationFrame = 0, animationTime = 8;
	
	public static boolean isMovingUp = false;
	public static boolean isMovingDown = false;
	public static boolean isMovingRight = false;
	public static boolean isMovingLeft = false;
	
	public static boolean isKnockingUp = false;
	public static boolean isKnockingDown = false;
	public static boolean isKnockingRight = false;
	public static boolean isKnockingLeft = false;
	
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
	public static double frameOffsetLeft = 4;
	public static double frameOffsetRight = 20;
	public static double frameOffsetBottom = 31;
	public static double frameOffsetTop = 16;
	
	
	
	public Character(int width, int height) {
		setBounds((Component.pixel.width / 2) - (width / 2), (Component.pixel.height / 2) - (height / 2), width, height);
		x = 1* Tile.tileSize;
		y = 1* Tile.tileSize;

	}
	public Rectangle bounds() {
		return (new Rectangle((int) x + (int) frameOffsetLeft -(int)Component.sX,(int) y + 9- (int)Component.sY,(int) frameOffsetRight - (int) frameOffsetLeft,(int)frameOffsetBottom - 8));
	  //return (new Rectangle(x + frameOffsetLeft,y + frameOffsetTop,frameWidth,frameHeight));
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
	public double getCollisionX() {
		double horizontalExtra = 0;
		double x1 = Math.floor((x+frameOffsetLeft + moveX) /Tile.tileSize);
		double x2 = Math.floor((x+frameOffsetRight + moveX) /Tile.tileSize);
		double y1 = Math.floor((y+frameOffsetTop) /Tile.tileSize);
		double y2 = Math.floor((y+frameOffsetBottom) /Tile.tileSize);
		
		
		if(isMovingLeft) {
			if(Component.level.block[(int)x1][(int)y1].isPassable == false || Component.level.block[(int)x1][(int)y2].isPassable == false
					||Component.level.block2[(int)x1][(int)y1].isPassable == false || Component.level.block2[(int)x1][(int)y2].isPassable == false) {
				horizontalExtra = (x + frameOffsetLeft) - x1 * Tile.tileSize - Tile.tileSize;
				return Math.min(0, -(horizontalExtra));
			}
		}
		if(isMovingRight) {
			if(Component.level.block[(int)x2][(int)y1].isPassable == false || Component.level.block[(int)x2][(int)y2].isPassable == false
					|| Component.level.block2[(int)x2][(int)y1].isPassable == false || Component.level.block2[(int)x2][(int)y2].isPassable == false) {
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
			if(Component.level.block[(int)x2][(int)y2].isPassable == false || Component.level.block[(int)x1][(int)y2].isPassable == false
					|| Component.level.block2[(int)x2][(int)y2].isPassable == false || Component.level.block2[(int)x1][(int)y2].isPassable == false) {
				verticalExtra = y2 * Tile.tileSize - (y + frameOffsetBottom) - 1;
				return Math.max(0, verticalExtra);
				
			}
		}
		if(isMovingUp) {
			if(Component.level.block[(int)x1][(int)y1].isPassable == false || Component.level.block[(int)x2][(int)y1].isPassable == false
					|| Component.level.block2[(int)x1][(int)y1].isPassable == false || Component.level.block2[(int)x2][(int)y1].isPassable == false) {
				verticalExtra = (y + frameOffsetTop) - y1 * Tile.tileSize - Tile.tileSize;
				return Math.min(0, -(verticalExtra));
			}
		}
		return moveY;	
	}
	public double getCollisionXKnock() {
		double horizontalExtra = 0;
		double x1 = Math.floor((x+frameOffsetLeft + moveX) /Tile.tileSize);
		double x2 = Math.floor((x+frameOffsetRight + moveX) /Tile.tileSize);
		double y1 = Math.floor((y+frameOffsetTop) /Tile.tileSize);
		double y2 = Math.floor((y+frameOffsetBottom) /Tile.tileSize);
		
		
		if(isKnockingLeft) {
			if(Component.level.block[(int)x1][(int)y1].isPassable == false || Component.level.block[(int)x1][(int)y2].isPassable == false
					||Component.level.block2[(int)x1][(int)y1].isPassable == false || Component.level.block2[(int)x1][(int)y2].isPassable == false) {
				horizontalExtra = (x + frameOffsetLeft) - x1 * Tile.tileSize - Tile.tileSize;
				return Math.min(0, -(horizontalExtra));
			}
		}
		if(isKnockingRight) {
			if(Component.level.block[(int)x2][(int)y1].isPassable == false || Component.level.block[(int)x2][(int)y2].isPassable == false
					|| Component.level.block2[(int)x2][(int)y1].isPassable == false || Component.level.block2[(int)x2][(int)y2].isPassable == false) {
				horizontalExtra = x2 * Tile.tileSize - (x + frameOffsetRight) - 1;
				return Math.max(0, horizontalExtra);
			}
		}
		return moveX;
	}
	public double getCollisionYKnock() {
		double verticalExtra = 0;
		double x1 = Math.floor((x+frameOffsetLeft) /Tile.tileSize);
		double x2 = Math.floor((x+frameOffsetRight) /Tile.tileSize);
		double y1 = Math.floor((y+frameOffsetTop + moveY) /Tile.tileSize);
		double y2 = Math.floor((y+frameOffsetBottom + moveY) /Tile.tileSize);
		
		if(isKnockingDown) {
			if(Component.level.block[(int)x2][(int)y2].isPassable == false || Component.level.block[(int)x1][(int)y2].isPassable == false
					|| Component.level.block2[(int)x2][(int)y2].isPassable == false || Component.level.block2[(int)x1][(int)y2].isPassable == false) {
				verticalExtra = y2 * Tile.tileSize - (y + frameOffsetBottom) - 1;
				return Math.max(0, verticalExtra);
				
			}
		}
		if(isKnockingUp) {
			if(Component.level.block[(int)x1][(int)y1].isPassable == false || Component.level.block[(int)x2][(int)y1].isPassable == false
					|| Component.level.block2[(int)x1][(int)y1].isPassable == false || Component.level.block2[(int)x2][(int)y1].isPassable == false) {
				verticalExtra = (y + frameOffsetTop) - y1 * Tile.tileSize - Tile.tileSize;
				return Math.min(0, -(verticalExtra));
			}
		}
		return moveY;	
	}
	public void tick() {
		
		if(isKnockingBack == true) {
			if(stepsToTake == 0) {
				if(isKnockingBack == true) {
					isKnockingBack = false;
					speed = maxSpeed;
					
				}
				speed = maxSpeed;
				
			}
		
		stepsToTake = stepsToTake - 1;
		//stepsToTake = 1;
		
		
		
		//dir = 270;
	if(isKnockingBack == false ) {
	} else {
				if(dir > 180 && dir < 360) {
					isKnockingUp = true;
					isKnockingDown = false;
			
				}
				if(dir > 0 && dir < 180) {
					isKnockingUp = false;
					isKnockingDown = true;
			
				}
				if(dir > 90 && dir < 270) {
					isKnockingLeft = true;
					isKnockingRight = false;
			
				}
				if(dir > 270 || dir < 90) {
					isKnockingLeft = false;
					isKnockingRight = true;
			
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
		
					
	
					moveX = getCollisionXKnock();
					moveY = getCollisionYKnock();
					
		
					
					if(!canMove) {
						
						x += moveX;
						y += moveY;
				
						
						if(x > Component.initialGameWidth /2 /Component.pixelSize && x < Level.worldW * Tile.tileSize - (Component.initialGameWidth /2/Component.pixelSize)) {
							Component.sX += moveX;
						} else if(Component.sX < 16){
							Component.sX = 0;
						} else if(Component.sX > Level.worldW * Tile.tileSize - (Component.initialGameWidth /2/Component.pixelSize)-(Component.initialGameWidth/4)-16) {
							Component.sX = Level.worldW * Tile.tileSize - (Component.initialGameWidth /2/Component.pixelSize)-(Component.initialGameWidth/4);
						}
						if(y > Component.initialGameHeight /2 /Component.pixelSize && y < Level.worldH * Tile.tileSize - (Component.initialGameHeight /2/Component.pixelSize)) {
							Component.sY += moveY;
						} else if(Component.sY < 16){
							Component.sY = 0;
						} else if(Component.sY > Level.worldH * Tile.tileSize - (Component.initialGameHeight/2/Component.pixelSize)-(Component.initialGameHeight/4)-16) {
							Component.sY = Level.worldH * Tile.tileSize - (Component.initialGameHeight /2/Component.pixelSize)-(Component.initialGameHeight/4);
						}
						
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
		} else if(isMovingLeft || isMovingRight || isMovingUp || isMovingDown && Inventory.isOpen == false) {
			if(Component.weapon.isInUse == false) {
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
						
						
						if(x > Component.initialGameWidth /2 /Component.pixelSize && x < Level.worldW * Tile.tileSize - (Component.initialGameWidth /2/Component.pixelSize)) {
							Component.sX += moveX;
						} else if(Component.sX < 16){
							Component.sX = 0;
						} else if(Component.sX > Level.worldW * Tile.tileSize - (Component.initialGameWidth /2/Component.pixelSize)-(Component.initialGameWidth/4)-16) {
							Component.sX = Level.worldW * Tile.tileSize - (Component.initialGameWidth /2/Component.pixelSize)-(Component.initialGameWidth/4);
						}
						if(y > Component.initialGameHeight /2 /Component.pixelSize && y < Level.worldH * Tile.tileSize - (Component.initialGameHeight /2/Component.pixelSize)) {
							Component.sY += moveY;
						} else if(Component.sY < 16){
							Component.sY = 0;
						} else if(Component.sY > Level.worldH * Tile.tileSize - (Component.initialGameHeight/2/Component.pixelSize)-(Component.initialGameHeight/4)-16) {
							Component.sY = Level.worldH * Tile.tileSize - (Component.initialGameHeight /2/Component.pixelSize)-(Component.initialGameHeight/4);
						}
						
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
	
	

	
	public void render(Graphics g) {

		
		if (Component.weapon.isInUse) {
			
			if (isFacingDown) {
				g.drawImage(Tile.tileset_terrain, 
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(Tile.character[0] * 24) + (24 * Component.weapon.animation) +288, 
						Tile.character[1] * Tile.tileSize, 
						Tile.character[0] * 24 +(24 * Component.weapon.animation) + (int) width +288, 
						Tile.character[1] * Tile.tileSize +(int) height, 
						null);
			} else if(isFacingRight) {
				int a = Component.weapon.animation + 3;
				g.drawImage(Tile.tileset_terrain, 
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(Tile.character[0] * 24) + (24*a) +288, 
						Tile.character[1] * Tile.tileSize, 
						Tile.character[0] * 24 +(24 * a) + (int) width+288, 
						Tile.character[1] * Tile.tileSize +(int) height, 
						null);
			} else if(isFacingUp) {
				int a = Component.weapon.animation + 6;
				g.drawImage(Tile.tileset_terrain, 
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(Tile.character[0] * 24) + (24*a)+288, 
						Tile.character[1] * Tile.tileSize, 
						Tile.character[0] * 24 +(24 * a) + (int) width+288, 
						Tile.character[1] * Tile.tileSize +(int) height, 
						null);
			} else if (isFacingLeft || isMovingLeft) {
				int a = Component.weapon.animation + 9;
				g.drawImage(Tile.tileset_terrain, 
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(Tile.character[0] * 24) + (24*a)+288, 
						Tile.character[1] * Tile.tileSize, 
						Tile.character[0] * 24 +(24 * a) + (int) width+288, 
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
						(Tile.character[0] * 24) + (24*Component.weapon.animation), 
						Tile.character[1] * Tile.tileSize, 
						Tile.character[0] * 24 +(24 * Component.weapon.animation) + (int) width, 
						Tile.character[1] * Tile.tileSize +(int) height, 
						null);
				
			}
		} else {
			if (isFacingDown) {
				g.drawImage(Tile.tileset_terrain, 
						(int) x - (int) Component.sX, 
						(int) y - (int) Component.sY, 
						(int) (x + width) - (int) Component.sX, 
						(int) (y + height) - (int) Component.sY, 
						/*Where it's cut out*/
						(Tile.character[0] * 24) + (24 *animation), 
						Tile.character[1] * Tile.tileSize, 
						Tile.character[0] * 24 +(24 * animation) + (int) width, 
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
						(Tile.character[0] * 24) + (24*a), 
						Tile.character[1] * Tile.tileSize, 
						Tile.character[0] * 24 +(24 * a) + (int) width, 
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
						(Tile.character[0] * 24) + (24*a), 
						Tile.character[1] * Tile.tileSize, 
						Tile.character[0] * 24 +(24 * a) + (int) width, 
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
						(Tile.character[0] * 24) + (24*a), 
						Tile.character[1] * Tile.tileSize, 
						Tile.character[0] * 24 +(24 * a) + (int) width, 
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
						(Tile.character[0] * 24) + (24*animation), 
						Tile.character[1] * Tile.tileSize, 
						Tile.character[0] * 24 +(24 * animation) + (int) width, 
						Tile.character[1] * Tile.tileSize +(int) height, 
						null);
				
			}
		}
//		g.setColor(new Color(0,0,255, 255));
//		g.drawRect (
//				(int)x+(int)frameOffsetLeft,
//				(int)y+ (int)frameOffsetTop,
//				(int)frameOffsetRight-(int)frameOffsetLeft,
//				(int)frameOffsetBottom-(int)frameOffsetTop);
	}
}
