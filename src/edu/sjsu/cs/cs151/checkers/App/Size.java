package edu.sjsu.cs.cs151.checkers.App;

/**
 * Size is a representation of size used by the game logic. It will be used by both core and GUI classes.
 */
public class Size {

	// Since layout is grid, don't really need float level precision
	Size(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
// Getters and Setters
	
	/**
	 * getWidth returns the width of this object.
	 * @return: an integer representation of width
	 */
	public int getWidth() {
	   return width;
	}
	
	/**
	 * getHeight returns the height of this object.
	 * @return: an integer representation of height
	 */
	public int getHeight() {
	   return height;
	}
	
// Private fields
	
	private int width = 0;
   private int height = 0;
}