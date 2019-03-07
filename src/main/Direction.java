/*Miguel Zavala, Sasha Jones
 * Lecture: CISC275-011
 * Lab: CISC275-031L
 * https://github.com/cisc275/lab04-mvc-sasha-and-miguel
 */

package main;
public enum Direction {

	NORTH("north"),
	NORTHEAST("northeast"),
	EAST("east"),
	SOUTHEAST("southeast"),
	SOUTH("south"),
	SOUTHWEST("southwest"),
	WEST("west"),
	NORTHWEST("northwest");
	
	private String name = null;
	
	private Direction(String s){
		this.name = s;
	}
	public String getName() {
		return this.name;
	}


}
