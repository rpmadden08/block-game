package net.ulixava.adventurecraft;

import java.awt.*;

public class Character extends DoubleRectangle {
	public double fallingSpeed = 1;
	public double movementSpeed = 1;
	public double maxSpeed = 3;
	public double jumpingSpeed = 8;
	
	public static boolean isMovingUp = false;
	public static boolean isMovingDown = false;
	public static boolean isMovingRight = false;
	public static boolean isMovingLeft = false;
	public int xVelocity = 0;
	public int yVelocity = 0;
	public int offsetX = 0;
	public int offsetY = 0;
	public int xDistance = 0;
	public int yDistance = 0;
	
	public double moveX = 0;
	public double moveY = 0;
	
	public double frameOffsetLeft = 0;
	public double frameOffsetRight = 16;
	public double frameOffsetBottom = 31;
	public double frameOffsetTop = 16;
	
	public int jumpingHeight = 8, jumpingCount = 0;
	public int animation = 0;  
	public int animationFrame = 0, animationTime = 25;
	
	public Character(int width, int height) {
		setBounds((Component.pixel.width / 2) - (width / 2), (Component.pixel.height / 2) - (height / 2), width, height);
	}
	
	public void tick() {		
		if(Component.isMoving && !Inventory.isOpen) {
			boolean canMove = false;
			moveX = 0;
			moveY = 0;

			
			if(isMovingRight) {
				moveX += maxSpeed;
			}
			if(isMovingLeft) {
				moveX += -maxSpeed;
			}
			if(isMovingDown) {
				moveY += maxSpeed;
			}
			if(isMovingUp) {
				moveY += -maxSpeed;
			}
			moveX = getCollisionX();
			moveY = getCollisionY();
			
			if(!canMove) {
				x += moveX;
				y += moveY;
				Component.sX += moveX;
				Component.sY += moveY;
			}
			
//			if(isMovingUp == true) {
//				canMove = isCollidingWithBlock(new Point((int) x, 
//						(int) (y+16)), 
//						new Point((int) x, 
//						(int) (y +(height-2))));
//			}
//			
//			if(isMovingRight == true) {
//				canMove = isCollidingWithBlock(new Point((int) (x+width), 
//						(int) y), 
//						new Point((int) (x+width-2), 
//						(int) (y +(height-2))));
//			}
//			
//			if(isMovingDown == true) {
//				canMove = isCollidingWithBlock(new Point((int) x, 
//						(int) (y+height)), 
//						new Point((int) x, 
//						(int) (y +(height-2))));				
//			}
//			
//			if(isMovingLeft == true) {
//				canMove = isCollidingWithBlock(new Point((int) x, 
//						(int) y), 
//						new Point((int) (x+13), 
//						(int) (y+ (height))));
//			}
			
			
		
			
			if(animationFrame >= animationTime) {
				if(animation > 1) {
					animation = 1;
				} else {
					animation += 1;
					
				}
				animationFrame = 0;
			} else {
				animationFrame += 1;
			}
			
//			if(!canMove) {	
//				if(isMovingUp == true) {
//					y-=maxSpeed;
//					Component.sY -= maxSpeed;
//				}
//				if(isMovingRight == true) {
//					x+=maxSpeed;
//					Component.sX += maxSpeed;
//				} 
//				if(isMovingDown == true) {
//					y+=maxSpeed;
//					Component.sY += maxSpeed;
//				}
//				if(isMovingLeft == true) {
//					x-=maxSpeed;
//					Component.sX -= maxSpeed;
//				}
//			}
			
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
			if(Component.level.block[(int)y1][(int)x1].id == Tile.bedrock || Component.level.block[(int)y2][(int)x1].id == Tile.bedrock) {
				horizontalExtra = (x + frameOffsetLeft) - x1 * Tile.tileSize - Tile.tileSize;
				return Math.min(0, -(horizontalExtra));
			}
		}
		if(isMovingRight) {
			if(Component.level.block[(int)y1][(int)x2].id == Tile.bedrock || Component.level.block[(int)y2][(int)x2].id == Tile.bedrock) {
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
			if(Component.level.block[(int)y2][(int)x2].id == Tile.bedrock || Component.level.block[(int)y2][(int)x1].id == Tile.bedrock) {
				verticalExtra = y2 * Tile.tileSize - (y + frameOffsetBottom) - 1;
				return Math.max(0, verticalExtra);
				
			}
		}
		if(isMovingUp) {
			if(Component.level.block[(int)y1][(int)x1].id == Tile.bedrock || Component.level.block[(int)y1][(int)x2].id == Tile.bedrock) {
				verticalExtra = (y + frameOffsetTop) - y1 * Tile.tileSize - Tile.tileSize;
				return Math.min(0, -(verticalExtra));
			}
		}
		return moveY;	
	}
	
	public boolean isCollidingWithBlock(Point pt1, Point pt2) {
		
		for(int x=(int) (this.x/Tile.tileSize);x<(int) (this.x/Tile.tileSize) +3;x++) {
			for(int y=(int) (this.y/Tile.tileSize);y<(int) (this.y/Tile.tileSize) +3;y++) {
				
				if(x >= 0 && y>= 0 && x < Component.level.block.length && y < Component.level.block[0].length) {
					if(Component.level.block[x][y].id != Tile.earth) {
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
		if (Component.dir == movementSpeed) {
			g.drawImage(Tile.tileset_terrain, (int) x - (int) Component.sX, (int) y - (int) Component.sY, (int) (x + width) - (int) Component.sX, (int) (y + height) - (int) Component.sY, /*Where it's cut out*/(Tile.character[0] * Tile.tileSize) + (Tile.tileSize*animation), Tile.character[1] * Tile.tileSize, Tile.character[0] * Tile.tileSize +(Tile.tileSize * animation) + (int) width, Tile.character[1] * Tile.tileSize +(int) height, null);
		} else {
			g.drawImage(Tile.tileset_terrain, (int) x - (int) Component.sX, (int) y - (int) Component.sY, (int) (x + width) - (int) Component.sX, (int) (y + height) - (int) Component.sY, /*Where it's cut out*/Tile.character[0] * Tile.tileSize +(Tile.tileSize * animation) + (int) width, Tile.character[1] * Tile.tileSize, (Tile.character[0] * Tile.tileSize) + (Tile.tileSize*animation), Tile.character[1] * Tile.tileSize +(int) height, null);
		}
	}
}
