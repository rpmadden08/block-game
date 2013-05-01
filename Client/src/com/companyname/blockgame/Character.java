package com.companyname.blockgame;

import java.awt.*;
import java.awt.image.*;

public class Character {
	public static int width = 32;
	public static int height = 36;
	private int x = (int) (Component.initialGameWidth / 2) - (int) (width / 2);
	private int y = (int) (Component.initialGameHeight/ 2) - (int) (height / 2);

	/* Movement */
	public double maxSpeed = 1;
	
	public static boolean isMovingUp = false;
	public static boolean isMovingDown = false;
	public static boolean isMovingRight = false;
	public static boolean isMovingLeft = false;
	
	private double moveX = 0;
	private double moveY = 0;
	
	/* Collision Detection */
	//TODO: Adjust these values to match actual offset.
	public static double frameOffsetLeft = 10;
	public static double frameOffsetRight = 20;
	public static double frameOffsetBottom = 35;
	public static double frameOffsetTop = 24;

	/* Animation */
	public int step = 1;
	public int[] stepArr = {0,1,2,1};
	public int animation = 0;  
	public int animationFrame = 0, animationTime = 11;
	
	private int framePos[] = {0, 2};
	
	/* Render variables */
	public static BufferedImage img;
	public static BufferedImage base;
	public static BufferedImage eyes;
	public static BufferedImage hair;
	public static BufferedImage outfit;
	
	private int dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2;
	
	public Character() {
		dx1 = x - (int) Component.sX;
		dy1 = y - (int) Component.sY;
		dx2 = x + width - (int) Component.sX;
		dy2 = y + height - (int) Component.sY;
		
		sx1 = framePos[0] * width + width * animation;
		sy1 = framePos[1] * height;
		sx2 = sx1 + width;
		sy2 = sy1 + height;
	}
	
	public void tick() {
		if(isMovingLeft || isMovingRight || isMovingUp || isMovingDown) {
			moveX = 0;
			moveY = 0;
			
			if(isMovingRight) {
				moveX += maxSpeed;
				framePos[1] = 1;
			}
			if(isMovingLeft) {
				moveX += -maxSpeed;
				framePos[1] = 3;
			}
			if(isMovingDown) {
				moveY += maxSpeed;
				framePos[1] = 2;
			}
			if(isMovingUp) {
				moveY += -maxSpeed;
				framePos[1] = 0;
			}
			moveX = getCollisionX();
			moveY = getCollisionY();
			
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
		} else {
			animation = 1;
			step = 1;
		}
		
		dx1 = x - (int) Component.sX;
		dy1 = y - (int) Component.sY;
		dx2 = x + width - (int) Component.sX;
		dy2 = y + height - (int) Component.sY;
		
		sx1 = framePos[0] * width + width * animation;
		sy1 = framePos[1] * height;
		sx2 = sx1 + width;
		sy2 = sy1 + height;
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
		return (new Rectangle((int) x + (int) frameOffsetLeft,(int) y + 9,(int) frameOffsetRight,(int)frameOffsetBottom - 8));
	}
	
	public void render(Graphics g) {
		g.drawImage(base, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		g.drawImage(eyes, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		g.drawImage(outfit, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		g.drawImage(hair, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
	}
	
	public void renderBig(Graphics g) {
		//g.drawImage(base, dx1, dy1, width, height, null);
		int xx = 290;
		int yy = 50;
		int xx2 = xx + width*4;
		int yy2 = yy + height*4;
		g.drawImage(base, xx, yy, xx2, yy2, sx1, sy1, sx2, sy2, null);
		g.drawImage(eyes, xx, yy, xx2, yy2, sx1, sy1, sx2, sy2, null);
		g.drawImage(outfit, xx, yy, xx2, yy2, sx1, sy1, sx2, sy2, null);
		g.drawImage(hair, xx, yy, xx2, yy2, sx1, sy1, sx2, sy2, null);
	}
}
