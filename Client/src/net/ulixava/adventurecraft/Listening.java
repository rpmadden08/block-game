package net.ulixava.adventurecraft;

import java.awt.event.*;

public class Listening implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			Component.isMoving = true;
			Character.isMovingUp = true;
			Character.isFacingUp = true;
			Character.isFacingDown = false;
			Character.isFacingLeft = false;
			Character.isFacingRight = false;
			break;
			
		case KeyEvent.VK_D:
			Component.isMoving = true;
			Character.isMovingRight = true;
			Character.isFacingUp = false;
			Character.isFacingDown = false;
			Character.isFacingLeft = false;
			Character.isFacingRight = true;
			break;
			
		case KeyEvent.VK_S:
			Component.isMoving = true;
			Character.isMovingDown = true;
			Character.isFacingUp = false;
			Character.isFacingDown = true;
			Character.isFacingLeft = false;
			Character.isFacingRight = false;
			break;
		
		case KeyEvent.VK_A:
			Component.isMoving = true;
			Character.isMovingLeft = true;
			Character.isFacingUp = false;
			Character.isFacingDown = false;
			Character.isFacingLeft = true;
			Character.isFacingRight = false;
			break;
		
		
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			//Component.isMoving = false;
			Character.isMovingUp = false;
			Component.dir = 0;
			break;
			
		case KeyEvent.VK_D:
			//Component.isMoving = false;
			Character.isMovingRight = false;
			Component.dir = 90;
			break;
			
		case KeyEvent.VK_S:
			//Component.isMoving = false;
			Character.isMovingDown = false;
			Component.dir = 180;
			break;
		
		case KeyEvent.VK_A:
			//Component.isMoving = false;
			Character.isMovingLeft = false;
			Component.dir = 270;
			break;
			
		case KeyEvent.VK_E:
			if(Inventory.isOpen) {
				Inventory.isOpen = false;
			} else {
				Inventory.isOpen = true;
			}
			break;
		}
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation()> 0) { //Down
			if(Inventory.selected < Tile.invLength-1) {
				Inventory.selected += 1;
			} else {
				Inventory.selected = 0;
			}
		} else if(e.getWheelRotation() < 0) { //Up
			if(Inventory.selected > 0) {
				Inventory.selected -= 1;
			} else {
				Inventory.selected = Tile.invLength-1;
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		Component.mse.setLocation(e.getX(),e.getY());
	}
	
	public void mouseMoved(MouseEvent e) {
		Component.mse.setLocation(e.getX(),e.getY());
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}	
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}	
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			Component.isMouseLeft = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			Component.isMouseRight = true;
		}
		Inventory.click(e);
	}	
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			Component.isMouseLeft = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			Component.isMouseRight = false;
		}
	}	
	
}
