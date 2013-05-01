package com.companyname.blockgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.HashMap;

public class CharacterSelectMenu {
	private final int START_POS = 15;
	private final int BACK_ROW_NUM = 19;
	private final int BACK_COL_NUM = 14;
	private final int BACK_WIDTH = 32;
	private final int BACK_HEIGHT = 32;
	
	private final int SLOT_HEIGHT = 50;
	private final int SLOT_WIDTH = 50;
	private final int SELECT_WIDTH = 54;
	private final int SELECT_HEIGHT = 54;
	
	private final int SPACE_SIZE = 5;
	private final int SLOT_SPACE = 70;

	private final int MARGIN_X = (SLOT_WIDTH - Character.width) / 2;
	private final int MARGIN_Y = (SLOT_HEIGHT - Character.height) / 2;
	
	public static ArrayList<BufferedImage> baseIms = new ArrayList<BufferedImage>();
	public static ArrayList<BufferedImage> hairIms = new ArrayList<BufferedImage>();
	public static ArrayList<BufferedImage> outfitIms = new ArrayList<BufferedImage>();
	public static ArrayList<BufferedImage> eyeIms = new ArrayList<BufferedImage>();
	public static ArrayList<BufferedImage> hairColorIms = new ArrayList<BufferedImage>();
	
	public static BufferedImage upperLeftCornerIm;
	public static BufferedImage upperRightCornerIm;
	public static BufferedImage bottomLeftCornerIm;
	public static BufferedImage bottomRightCornerIm;
	
	public static BufferedImage upperIm;
	public static BufferedImage bottomIm;
	public static BufferedImage leftIm;
	public static BufferedImage rightIm;
	public static BufferedImage middleIm;
	
	public static BufferedImage slotIm;
	public static BufferedImage selectIm;
	public static BufferedImage maleIm;
	public static BufferedImage femaleIm;
	
	public static int genderSelection = 0;
	public static int baseSelection = 0;
	public static int eyeSelection = 0;
	public static int outfitSelection = 0;
	public static int hairSelection = 0;
	public static int hairColorSelection = 0;
	
	public void render(Graphics g) {
		g.drawImage(upperLeftCornerIm, START_POS, START_POS, BACK_WIDTH, BACK_HEIGHT, null);
		placeRow(g, upperIm, BACK_WIDTH + START_POS, START_POS, BACK_WIDTH, BACK_HEIGHT, 0, BACK_ROW_NUM);
		g.drawImage(upperRightCornerIm, BACK_WIDTH * (BACK_ROW_NUM + 1) + START_POS, START_POS, BACK_WIDTH, BACK_HEIGHT, null);
		
		for(int i = 0; i < BACK_COL_NUM; i++) {
			g.drawImage(leftIm, START_POS, BACK_HEIGHT * (i+1) + START_POS, BACK_WIDTH, BACK_HEIGHT, null);
			placeRow(g, middleIm, START_POS + BACK_WIDTH, START_POS + (BACK_HEIGHT * (i + 1)), BACK_WIDTH, BACK_HEIGHT, 0, BACK_ROW_NUM);
			g.drawImage(rightIm, BACK_WIDTH * (BACK_ROW_NUM + 1) + START_POS, BACK_HEIGHT * (i+1) + START_POS, BACK_WIDTH, BACK_HEIGHT, null);
		}
		
		g.drawImage(bottomLeftCornerIm, START_POS, BACK_HEIGHT * (BACK_COL_NUM + 1) + START_POS, BACK_WIDTH, BACK_HEIGHT, null);
		placeRow(g, bottomIm, BACK_WIDTH + START_POS, BACK_HEIGHT * (BACK_COL_NUM + 1) + START_POS, BACK_WIDTH, BACK_HEIGHT, 0, BACK_ROW_NUM);
		g.drawImage(bottomRightCornerIm, BACK_WIDTH * (BACK_ROW_NUM + 1) + START_POS, BACK_HEIGHT * (BACK_COL_NUM + 1) + START_POS, BACK_WIDTH, BACK_HEIGHT, null);
		
		int x = 50;
		int y = 40;
		
		placeRow(g, slotIm, x, y, SLOT_WIDTH, SLOT_HEIGHT, SPACE_SIZE, 2);
		g.drawImage(maleIm, x, y, SLOT_WIDTH, SLOT_HEIGHT, null);
		g.drawImage(femaleIm, x + SLOT_WIDTH, y, SLOT_WIDTH, SLOT_HEIGHT, null);
		g.drawImage(selectIm, x-1+genderSelection + genderSelection * SELECT_WIDTH, y-1, SELECT_WIDTH, SELECT_HEIGHT, null);
		
		y += SLOT_SPACE;
		drawSlotRow(g, baseIms, x, y, baseSelection);
		
		y += SLOT_SPACE;
		drawSlotRow(g, hairIms, x, y, hairSelection);
		
		y += SLOT_SPACE;
		drawSlotRow(g, eyeIms, x, y, eyeSelection);
		
		y += SLOT_SPACE;
		drawSlotRow(g, hairColorIms, x, y, hairColorSelection);
		
		y += SLOT_SPACE;
		drawSlotRow(g, outfitIms, x, y, outfitSelection);
	}
	
	private void drawSlotRow(Graphics g, ArrayList<BufferedImage> ims, int x, int y, int selection) {
		placeRow(g, slotIm, x, y, SLOT_WIDTH, SLOT_HEIGHT, SPACE_SIZE, ims.size());
		for(int i = 0; i < ims.size(); i++) g.drawImage(ims.get(i), x + i*SLOT_WIDTH + MARGIN_X + SPACE_SIZE * i, y + MARGIN_Y, Character.width, Character.height, null);
		g.drawImage(selectIm, x-1+selection + selection * SELECT_WIDTH, y-1, SELECT_WIDTH, SELECT_HEIGHT, null);
	}
	
	private void placeRow(Graphics g, BufferedImage im, int x, int y, int w, int h, int space, int numberOfRows) {
		for(int i = 0; i < numberOfRows; i++) {
			g.drawImage(im, x + (w*i) + (space*i), y, w, h, null);
		}
	}
	
	public static void setImages() {
		int mS = 32;
		upperLeftCornerIm = ImageAssets.menu_components.getSubimage(0, 0, mS, mS);
		upperRightCornerIm = ImageAssets.menu_components.getSubimage(64, 0, mS, mS);
		bottomLeftCornerIm = ImageAssets.menu_components.getSubimage(0, 64, mS, mS);
		bottomRightCornerIm = ImageAssets.menu_components.getSubimage(64, 64, mS, mS);
		
		upperIm = ImageAssets.menu_components.getSubimage(32, 0, mS, mS);
		bottomIm = ImageAssets.menu_components.getSubimage(32, 64, mS, mS);
		leftIm = ImageAssets.menu_components.getSubimage(0, 32, mS, mS);
		rightIm = ImageAssets.menu_components.getSubimage(64, 32, mS, mS);
		middleIm = ImageAssets.menu_components.getSubimage(32, 32, mS, mS);
		
		slotIm = ImageAssets.menu_components.getSubimage(96, 0, 50, 50);
		selectIm = ImageAssets.menu_components.getSubimage(146, 0, 54, 54);
		
		maleIm = ImageAssets.menu_components.getSubimage(96, 50, 50, 50);
		femaleIm = ImageAssets.menu_components.getSubimage(146, 54, 50, 50);
		
		for(int i = 0; i < ImageAssets.baseColors.size(); i++) baseIms.add(swapColors(ImageAssets.character_base, ImageAssets.baseColors.get(i)).getSubimage(32, 72, 32, 36));

		for(int i = 0; i < ImageAssets.character_hair.getWidth()/96; i++) hairIms.add(swapColors(ImageAssets.character_hair, ImageAssets.red_hair).getSubimage(32 + i*96, 72, 32, 36));
		
		for(int i = 0; i < ImageAssets.character_eyes.getWidth()/96; i++) eyeIms.add(ImageAssets.character_eyes.getSubimage(32 + i*96, 72, 32, 36));
		
		for(int i = 0; i < ImageAssets.hairColors.size(); i++) hairColorIms.add(swapColors(ImageAssets.character_hair, ImageAssets.hairColors.get(i)).getSubimage(32, 72, 32, 36));
		
		for(int i = 0; i < ImageAssets.character_outfit.getHeight()/144; i++) outfitIms.add(ImageAssets.character_outfit.getSubimage(32, 72 + i*144, 32, 36));		
	}
	
	public static void changeGender() {
		//change genderSelection
		//alter character base to corresponding gender
		//update all images to be corresponding gender
	}
	
	public static void changeBase() {
		
	}
	
	public static void changeEyes() {
		
	}
	
	public static void changeOutfit() {
		
	}
	
	public static void changeHair() {
		
	}
	
    public static BufferedImage swapColors( BufferedImage img, Color[] mapping ){
        int[] pixels = img.getRGB( 0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth() );
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); 
        for( int i = 0; i < mapping.length/2; i++ ){
            map.put( mapping[2*i].getRGB(), mapping[2*i+1].getRGB() ); 
        }


        for( int i = 0; i < pixels.length; i++ ){
            if( map.containsKey( pixels[i] ) )
                pixels[i] = map.get( pixels[i] ); 
        }
        
        img = deepCopy(img);
        img.setRGB( 0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth() );  
        return img;
    }
    
    static BufferedImage deepCopy(BufferedImage bi) {
    	 ColorModel cm = bi.getColorModel();
    	 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
    	 WritableRaster raster = bi.copyData(null);
    	 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
