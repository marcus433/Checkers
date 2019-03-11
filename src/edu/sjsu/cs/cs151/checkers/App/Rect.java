package edu.sjsu.cs.cs151.checkers.App;

/**
 * Rect is a collection of Size and Location objects; together, they represent the space a Piece or a Board object occupies.
 */
public class Rect {

	public Size size;
	public Location location;

	Rect(Size size, Location location) {
		this.size = size;
		this.location = location;
	}
}