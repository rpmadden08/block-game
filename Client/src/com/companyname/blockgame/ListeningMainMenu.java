package com.companyname.blockgame;

//import java.awt.Dimension;
import java.awt.event.*;


public class ListeningMainMenu extends Listening{
	public void keyPressed(KeyEvent e) {
	
	}
	
	public void keyReleased(KeyEvent e) {

	}
	
	public void keyTyped(KeyEvent e) {

	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {

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
		//Inventory.click(e);
	}	
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			Component.isMouseLeft = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			Component.isMouseRight = false;
		}
	}	
	
}
