package main;

import java.awt.Color;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model{
	int frameWidth,frameHeight,imageWidth,imageHeight; //the JFrame window properties
	int y,x; //the orc's x and y positions
    final int xIncr = 8; //orc's x speed
    final int yIncr = 2; //orc's y speed
    int xacc =1;
    int yacc =1;
	Direction direction; //the current direction of the orc

	public Model(int width, int height, int imageWidth, int imageHeight) {
		this.frameWidth  = width;
		this.frameHeight = height;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		
		
		//The model's starting position (x,y)
		this.y = 0;
		this.x = 0; 
		
		this.direction = Direction.SOUTHEAST; //temporarily have it set to SOUTHEAST
	}

	public void updateLocationAndDirection() {	
		System.out.println("X:"+this.xacc);
		System.out.println("Y:"+this.yacc);
		
		//Changes the orc's direction when reaching the JFrame window sides
		if(this.x+this.imageWidth>this.frameWidth){
			this.xacc = -1;
		}
		else if(this.x<0){
			this.xacc = 1;
		}

		if(this.y<0){
			this.yacc = 1;
		}
		else if(this.y+this.imageHeight>this.frameHeight){
			System.out.println("TRUE");
			this.yacc = -1;
		}
		
		//Updating the location (adds the x and y accelerations to position)
		x+=(xIncr*xacc);
		y+=(yIncr*yacc);
		
		
		//Determining the Direction of the model
				//North
				if(yacc==1 && xacc==0){
					this.direction = Direction.NORTH;
				}
				//South
				else if((yacc==-1) && (xacc==0)){
					this.direction = Direction.SOUTH;
				}

				//Facing East
				else if(xacc==1){
					//North East
					if(yacc==-1){
						this.direction = Direction.NORTHEAST;
					}
					//South East
					else if(yacc==1){
						this.direction = Direction.SOUTHEAST;
					}
					//East
					else if(yacc==0){
						this.direction = Direction.EAST;
					}
				}
				//Facing West
				else if(xacc==-1){
					//North West
					if(yacc==-1){
						this.direction = Direction.NORTHWEST;
					}
					//South West
					else if(yacc==1){
						this.direction = Direction.SOUTHWEST;
					}
					//West
					else if(yacc==0){
						this.direction = Direction.WEST;
					}
				}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public void setDirect(Direction new_direct) {
		this.direction = new_direct;
	}

	public Direction getDirect() {
		return this.direction;
	}
	
}
