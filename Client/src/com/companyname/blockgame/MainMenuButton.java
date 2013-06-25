package com.companyname.blockgame;

import static com.companyname.blockgame.Constants.CHUNK_SIZE;
import static com.companyname.blockgame.Constants.TILE_SIZE;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MainMenuButton extends Rectangle{
	private static final long serialVersionUID = 1L;
	public String name;
	public int id;
	public int x;
	public int y;
	
	public int fWidth;
	public int fHeight;

	public MainMenuButton(int id, String n, int y) {
		name = n;
		this.id = id;
		Font font = new Font("Helvetica", Font.PLAIN, 12);
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		FontMetrics fm = img.getGraphics().getFontMetrics(font);
		fWidth = fm.stringWidth(name);
		fHeight = 12;
		this.y = y;
		this.x = Component.initialGameWidth /2/Component.pixelSize -(fWidth/2);
		setBounds(bounds());
	
	}
	
	public Rectangle bounds() {
		return (new Rectangle(x ,y-fHeight ,fWidth,fHeight));
	}
	
	public void tick() {
		if(Component.isMouseLeft) {
			if(this.contains(new Point((Component.mse.x / Component.pixelSize) + (int) Component.sX, (Component.mse.y / Component.pixelSize) + (int) Component.sY))) {
				Component.mainMenu.buttonClicked(id);
			}
		}
	}
	
	public void render(Graphics g) {
		
		
		g.setColor(Color.WHITE);
		g.drawString(name,x, y);
		g.setColor(new Color(0,0,255, 255));
		g.drawRect(bounds().x, bounds().y, bounds().width, bounds().height);
	}

}
