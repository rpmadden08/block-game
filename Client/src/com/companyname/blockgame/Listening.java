package com.companyname.blockgame;

//import java.awt.Dimension;
import java.awt.Dimension;
import java.awt.event.*;

import static com.companyname.blockgame.Constants.*;


public class Listening implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		
		case KeyEvent.VK_1:
			Component.pixelSize = 1;
			Component.pixel = new Dimension(Component.size.width / Component.pixelSize, Component.size.height / Component.pixelSize);
			System.out.println("3");
			break;
		case KeyEvent.VK_2:
			Component.pixelSize = 2;
			Component.pixel = new Dimension(Component.size.width / Component.pixelSize, Component.size.height / Component.pixelSize);
			System.out.println("3");
			break;
		case KeyEvent.VK_3:
			Component.pixelSize = 3;
			Component.pixel = new Dimension(Component.size.width / Component.pixelSize, Component.size.height / Component.pixelSize);
			System.out.println("3");
			break;
		case KeyEvent.VK_4:
			Component.pixelSize = 4;
			Component.pixel = new Dimension(Component.size.width / Component.pixelSize, Component.size.height / Component.pixelSize);
			System.out.println("3");
			break;
		
		case KeyEvent.VK_W:
				Character.isMovingUp = true;
				Character.isFacingUp = true;
				Character.isFacingDown = false;
				Character.isFacingLeft = false;
				Character.isFacingRight = false;
				
			break;
			
		case KeyEvent.VK_D:
			Character.isMovingRight = true;
			Character.isFacingUp = false;
			Character.isFacingDown = false;
			Character.isFacingLeft = false;
			Character.isFacingRight = true;
			break;
			
		case KeyEvent.VK_S:
			Character.isMovingDown = true;
			Character.isFacingUp = false;
			Character.isFacingDown = true;
			Character.isFacingLeft = false;
			Character.isFacingRight = false;
			break;
		
		case KeyEvent.VK_A:
			Character.isMovingLeft = true;
			Character.isFacingUp = false;
			Character.isFacingDown = false;
			Character.isFacingLeft = true;
			Character.isFacingRight = false;
			break;
		case KeyEvent.VK_M:
			Debugger.toggle();
			break;
		case KeyEvent.VK_I:
			Component.test.saveSave();
			break;
		case KeyEvent.VK_O:
			//Component.level.loadChunk();
			Component.test.loadSave();
			break;
			
		/*
		case KeyEvent.VK_Y:
			Component.pixelSize = 4;
			Component.pixel = new Dimension(Component.size.width / Component.pixelSize, Component.size.height / Component.pixelSize);
			break; */
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case KeyEvent.VK_W:
			Character.isMovingUp = false;
			break;
			
		case KeyEvent.VK_D:
			Character.isMovingRight = false;
			break;
			
		case KeyEvent.VK_S:
			Character.isMovingDown = false;
			break;
		
		case KeyEvent.VK_A:
			Character.isMovingLeft = false;
			break;
			
		case KeyEvent.VK_E:
			Inventory.toggleInventoryState();
			break;
		}
	}
	
	public void keyTyped(KeyEvent e) {

	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation()> 0) { //Down
			if(Inventory.selected < INVENTORY_LENGTH-1) {
				Inventory.selected += 1;
			} else {
				Inventory.selected = 0;
			}
		} else if(e.getWheelRotation() < 0) { //Up
			if(Inventory.selected > 0) {
				Inventory.selected -= 1;
			} else {
				Inventory.selected = INVENTORY_LENGTH-1;
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
