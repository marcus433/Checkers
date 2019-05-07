package edu.sjsu.cs.cs151.checkers.model;

/**
 * Rect is a collection of Size and Location objects; together, they represent the space a Piece or a Board object occupies.
 */
public class Rect {

	public Rect(Size size, Location location) {
		this.size = size;
		this.location = location;
	}
	
	// Getters and Setters
	
	/**
	 * getSize returns the size of this rectangle object.
	 * @return: a Size object that represents this rectangle
	 */
	public Size getSize() {
	   return size;
	}
	
	/**
	 * getLocation returns the location of this rectangle on the board.
	 * @return: a Location object for the rectangle
	 */
	public Location getLocation() {
	   return location;
	}
	
	// Private fields
	
	private Size size;
	private Location location;
}