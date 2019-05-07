package edu.sjsu.cs.cs151.checkers.model;

/**
 * Size is a representation of size used by the game logic. It will be used by both core and GUI classes.
 */
public class Size {
   
  public Size() {
		this.width = DEFAULT_SIZE;
		this.height = DEFAULT_SIZE;
	}

	// Since layout is grid, don't really need float level precision
	public Size(int width, int height) {
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
	
	private static final int DEFAULT_SIZE = 0;
	private int width;
	private int height;
}