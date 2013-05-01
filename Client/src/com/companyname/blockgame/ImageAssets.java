package com.companyname.blockgame;

import java.awt.Color;
import java.awt.image.*;

import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ImageAssets {

	public static BufferedImage terrain;
	public static BufferedImage character_sets;
	public static BufferedImage menu_components;
	public static BufferedImage character_base;
	public static BufferedImage character_eyes;
	public static BufferedImage character_hair;
	public static BufferedImage character_outfit;
	
	/* base colors */
	public static Color[] peach_base = new Color[] {
		new Color(197, 197, 197), new Color(243, 204, 147),
    	new Color(143, 143, 143), new Color(212, 129, 80),
    	new Color(36, 36, 36), new Color(57, 30, 19)
	};
	
	public static Color[] pale_base = new Color[] {
		new Color(197, 197, 197), new Color(253, 245, 185),
    	new Color(143, 143, 143), new Color(233, 165, 112),
    	new Color(36, 36, 36), new Color(57, 30, 19)
	};
	
	public static Color[] dark_base = new Color[] {
		new Color(197, 197, 197), new Color(232, 165, 104),
    	new Color(143, 143, 143), new Color(173, 103, 50),
    	new Color(36, 36, 36), new Color(57, 30, 19)
	};
	
	public static ArrayList<Color[]> baseColors = new ArrayList<Color[]>();
	
	/* hair colors */
	public static Color[] red_hair = new Color[] {
	    new Color(136, 136, 136), new Color(186, 107, 101),
	    new Color(87, 87, 87), new Color(167, 37, 30),
	    new Color(47, 47, 47), new Color(100, 17, 17),
	    new Color(36, 36, 36), new Color(77, 13, 13)
	};
	
	public static Color[] blue_hair = new Color[] {
	    new Color(136, 136, 136), new Color(89, 116, 224),
	    new Color(87, 87, 87), new Color(43, 61, 138),
	    new Color(47, 47, 47), new Color(37, 41, 79),
	    new Color(36, 36, 36), new Color(19, 24, 43)
	};
	
	public static Color[] green_hair = new Color[] {
	    new Color(136, 136, 136), new Color(157, 198, 65),
	    new Color(87, 87, 87), new Color(66, 98, 24),
	    new Color(47, 47, 47), new Color(30, 66, 22),
	    new Color(36, 36, 36), new Color(15, 32, 9)
	};
	
	public static Color[] blonde_hair = new Color[] {
		new Color(136, 136, 136), new Color(254,247, 104),
	    new Color(87, 87, 87), new Color(202, 157, 44),
	    new Color(47, 47, 47), new Color(137, 71, 31),
	    new Color(36, 36, 36), new Color(57, 30, 19)
	};
	
	public static Color[] brown_hair = new Color[] {
	    new Color(136, 136, 136), new Color(234, 199, 97),
	    new Color(87, 87, 87), new Color(212, 129, 80),
	    new Color(47, 47, 47), new Color(137, 71, 31),
	    new Color(36, 36, 36), new Color(57, 30, 19)
	};
	
	public static Color[] orange_hair = new Color[] {
		new Color(136, 136, 136), new Color(252, 217, 40),
	    new Color(87, 87, 87), new Color(201, 112, 34),
	    new Color(47, 47, 47), new Color(145, 71, 25),
	    new Color(36, 36, 36), new Color(57, 30, 19)
	};
	
	public static Color[] purple_hair = new Color[] {
	    new Color(136, 136, 136), new Color(222, 151, 223),
	    new Color(87, 87, 87), new Color(92, 65, 160),
	    new Color(47, 47, 47), new Color(14, 28, 87),
	    new Color(36, 36, 36), new Color(14, 18, 43)
	};
	
	public static Color[] pink_hair = new Color[] {
	    new Color(136, 136, 136), new Color(241, 186, 206),
	    new Color(87, 87, 87), new Color(214, 105, 143),
	    new Color(47, 47, 47), new Color(159, 42, 93),
	    new Color(36, 36, 36), new Color(124, 26, 78)
	};
	
	public static Color[] black_hair = new Color[] {
	    new Color(136, 136, 136), new Color(161, 161, 161),
	    new Color(87, 87, 87), new Color(77, 77, 77),
	    new Color(47, 47, 47), new Color(52, 52, 52),
	    new Color(36, 36, 36), new Color(31, 31, 31)
	};
	
	public static Color[] gray_hair = new Color[] {
	    new Color(136, 136, 136), new Color(254, 254, 254),
	    new Color(87, 87, 87), new Color(194, 194, 194),
	    new Color(47, 47, 47), new Color(109, 109, 109),
	    new Color(36, 36, 36), new Color(77, 77, 77)
	};

	public static ArrayList<Color[]> hairColors = new ArrayList<Color[]>();
	
	public ImageAssets() {
		baseColors.add(pale_base);
		baseColors.add(peach_base);
		baseColors.add(dark_base);
		hairColors.add(red_hair);
		hairColors.add(blue_hair);
		hairColors.add(green_hair);
		hairColors.add(blonde_hair);
		hairColors.add(brown_hair);
		hairColors.add(orange_hair);
		hairColors.add(purple_hair);
		hairColors.add(pink_hair);
		hairColors.add(black_hair);
		hairColors.add(gray_hair);
		
		try {
			ImageAssets.terrain = ImageIO.read(new File("res/terrain.png"));
			ImageAssets.character_base = ImageIO.read(new File("res/bases.png"));
			ImageAssets.character_eyes = ImageIO.read(new File("res/eyes.png"));
			ImageAssets.character_hair = ImageIO.read(new File("res/hairs.png"));
			ImageAssets.character_outfit = ImageIO.read(new File("res/outfits.png"));
			
			//ImageAssets.character_sets = ImageIO.read(new File("res/character_sets.png"));
			ImageAssets.menu_components = ImageIO.read(new File("res/menu_components.png"));
			
			CharacterSelectMenu.setImages();
			
			Character.base = swapColors(character_base, peach_base);
			Character.eyes = deepCopy(character_eyes);
			Character.hair = swapColors(character_hair, black_hair).getSubimage(2 * 96, 0, 96, 144);
			Character.outfit = deepCopy(character_outfit).getSubimage(0, 1 * 144, 96, 144);
			
			//Character.img = character_sets;
			
			//Character.img = new BufferedImage(createColorModel(), character_sets.getRaster(), false, null); //character_sets;
			
			
			//BufferedImage rgbImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		} catch(Exception e){
			System.out.println("Failure to load image assets. " + e);
		}
		
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
