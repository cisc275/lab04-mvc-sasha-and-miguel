/*Miguel Zavala, Sasha Jones
 * Lecture: CISC275-011
 * Lab: CISC275-031L
 * https://github.com/cisc275/lab04-mvc-sasha-and-miguel
 */

package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

public class View extends JFrame{
	//Frame properties
    final private int frameWidth = 500;
    final private int frameHeight = 300;
    final private int imgWidth = 165;
    final private int imgHeight = 165;
    
    //Orc data (gets updated by update(int, int, Direction))
    int orcx, orcy;
    Direction orc_direct;
    
	
	/*Constructor*/
    public View(){
    	this.getContentPane().add(new Animation());
    	this.setBackground(Color.gray);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setSize(this.frameWidth, this.frameHeight);
    	this.setVisible(true);
    }
    
    /*GETTERS*/
    public int getWidth() {
    	return this.frameWidth;
    }
    
    public int getHeight() {
    	return this.frameHeight;
    }
    
    public int getImageHeight() {
    	return this.imgHeight;
    }
    
    public int getImageWidth() {
    	return this.imgWidth;
    }
    
    /*Extra Getter*/
    public Direction getOrcDirect() {
    	return this.orc_direct;
    }
    
    public int getOrcX() {
    	return this.orcx;
    }
    
    public int getOrcY() {
    	return this.orcy;
    }
    
    public void update(int x, int y, Direction direct) {
    	System.out.println("CALLED");
    	
    	//updating the orc data
    	this.orcx = x;
    	this.orcy = y;
    	this.orc_direct = direct;
    	
    	
    	//Redrawing onto JFrame
    	this.repaint();
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    }
 
    
   /*-----------------------------------------------*/
    /*JPanel that draws Orc, gets added to JFrame*/
    class Animation extends JPanel{
    	final private int frameCount = 10;
        private int picNum = 0;
        private BufferedImage[] pics;
    	private BufferedImage[][][]spritesheets;//2D array
        final private int imgWidth = 165;
        final private int imgHeight = 165;
        
    	/*Constructor*/
        public Animation() {
    		//Allocates a 3x3 BufferedImage array('spritesheets') and 10 BufferedImages in each index
    		spritesheets = new BufferedImage[3][3][10];
    		for(int row=0;row<3;row++){
    			for(int column=0;column<3;column++){
    				BufferedImage img = createImage(row,column);
    				BufferedImage new_spritesheet[] = new BufferedImage[10];
    				for(int i = 0; i < frameCount; i++) {
    					System.out.println(i);
    					new_spritesheet[i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    				}

    				System.out.println(new_spritesheet);
    				System.out.println(row+","+column);
    				System.out.println(new_spritesheet);

    				spritesheets[row][column] = new_spritesheet;

    			}}

    	}
        
        public BufferedImage createImage(int row, int column){
    		BufferedImage bufferedImage;
    		Direction row_position = null;
    		Direction column_position = null;

    		//Determines which image to load and where to place it
    		//within the (3*3) BufferedImage 2d array
    		switch(row){
    			case 0:
    				row_position = Direction.NORTH;
    				break;

    			case 1:
    				row_position = Direction.NORTH; //center (currently is north temporarily)
    				break;
    			case 2:
    				row_position = Direction.SOUTH;
    		}

    		switch(column){
    			case 0:
    				column_position = Direction.WEST;
    				break;
    			case 1:
    				column_position = Direction.EAST; //center (currently is north temporarily)
    				break;
    			case 2:
    				column_position = Direction.EAST;
    				break;
    		}

    		//Loads the "orc_forward_northeast.png" in the center for now
    		if(row==1 && column==1){
    			row_position = Direction.NORTH;
    			column_position = Direction.EAST;
    		}
    		try {
    			System.out.println(row_position.getName());
    			String spritesheet_name = "src/mainpackage/images/orc_forward_"+row_position.getName()+column_position.getName()+".png";
    			System.out.println(spritesheet_name);
    			bufferedImage = ImageIO.read(new File(spritesheet_name));
    			System.out.println(spritesheet_name);
    			return bufferedImage;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return null;
    }

       
	    public void paint(Graphics g) {
	    	picNum = (picNum + 1) % frameCount; //updates to the next frame
	    	
	    	//Inner Variables
	    	int x = getOrcX();
	    	int y = getOrcY();
	    
	    	
	    	//Drawing based on orc's direction
	    	switch(getOrcDirect()) {
	    	case NORTHWEST:
	    		g.drawImage(spritesheets[0][0][picNum], x, y, Color.gray, (ImageObserver) this);
	    		break;
	    	
	        case NORTH:
	    		g.drawImage(spritesheets[0][1][picNum], x, y, Color.gray, (ImageObserver) this);
	    		break;
	    		
	        case NORTHEAST:
	    		g.drawImage(spritesheets[0][2][picNum], x, y, Color.gray, (ImageObserver) this);
	    		break;
	    		
	        case WEST:
	    		g.drawImage(spritesheets[1][0][picNum], x, y, Color.gray, (ImageObserver) this);
	    		break;
	    		
	        case EAST:
	    		g.drawImage(spritesheets[1][2][picNum], x, y, Color.gray, (ImageObserver) this);
	    		break;
	    		
	        case SOUTHWEST:
	    		g.drawImage(spritesheets[2][0][picNum], x, y, Color.gray, (ImageObserver) this);
	    		break;
	    		
	        case SOUTH:
	    		g.drawImage(spritesheets[2][1][picNum], x, y, Color.gray, (ImageObserver) this);
	    		break;
	    		
	        case SOUTHEAST:
	    		g.drawImage(spritesheets[2][2][picNum], x, y, Color.gray, (ImageObserver) this);
	    		break;
	    	}
	    }
    }
}
    
 