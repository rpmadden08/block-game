package com.companyname.blockgame;

//import java.awt.Dimension;
import java.awt.event.*;

public class Listening implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(Component.currentState.type == Component.MAIN_STATE) {
			switch(key) {
			case KeyEvent.VK_W:
				Character.isMovingUp = true;
				break;
				
			case KeyEvent.VK_D:
				Character.isMovingRight = true;
				break;
				
			case KeyEvent.VK_S:
				Character.isMovingDown = true;
				break;
			
			case KeyEvent.VK_A:
				Character.isMovingLeft = true;
				break;
			case KeyEvent.VK_M:
				Debugger.toggle();
				break;
			}
		} else if(Component.currentState.type == Component.INVENTORY_STATE) {
			switch(key) {

			}
		} else if(Component.currentState.type == Component.CHARACTER_SELECT_STATE) {
			switch(key) {
			
			}
		}
	}
	/*
	case KeyEvent.VK_Y:
		Component.pixelSize = 4;
		Component.pixel = new Dimension(Component.size.width / Component.pixelSize, Component.size.height / Component.pixelSize);
		break; */
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(Component.currentState.type == Component.MAIN_STATE) {
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
				Character.isMovingUp = false;
				Character.isMovingRight = false;
				Character.isMovingDown = false;
				Character.isMovingLeft = false;
				break;
			}
		} else if(Component.currentState.type == Component.INVENTORY_STATE) {
			switch(key) {
			case KeyEvent.VK_E:
				Inventory.toggleInventoryState();
				break;
			}
		} else if(Component.currentState.type == Component.CHARACTER_SELECT_STATE) {

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
		if(Component.currentState.type == Component.CHARACTER_SELECT_STATE) {
			
		}
	}	
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			Component.isMouseLeft = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			Component.isMouseRight = false;
		}
	}	
	
}
