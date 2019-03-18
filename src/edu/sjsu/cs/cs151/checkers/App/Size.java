package edu.sjsu.cs.cs151.checkers.App;

/**
 * Size is a representation of size used by the game logic. It will be used by both core and GUI classes. 
 */
public class Size {

	public int width = 0;
	public int height = 0;

	// Since layout is grid, don't really need float level precision
	Size(int width, int height) {
		this.width = width;
		this.height = height;
	}
}