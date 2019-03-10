package edu.sjsu.cs.cs151.checkers.App;

public class Size {

	public int width = 0;
	public int height = 0;

	// Since layout is grid, don't really need float level precision
	Size(int width, int height) {
		this.width = width;
		this.height = height;
	}
}