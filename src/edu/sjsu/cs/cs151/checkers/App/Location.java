package edu.sjsu.cs.cs151.checkers.App;

/**
 * Location is a Cartesian representation of position used by the game logic.
 */
public class Location {

	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

// Getters and Setters	
	/**
	 * getX returns the x coordinate of this Location.
	 * @return: an int representing x
	 */
	public int getX() {
	   return x;
	}
	
	/**
	 * getY returns the y coordinate of this Location.
	 * @return: an int representing y
	 */
	public int getY() {
	   return y;
	}
	
// Private fields
	
	private int x = 0;
   private int y = 0;
}