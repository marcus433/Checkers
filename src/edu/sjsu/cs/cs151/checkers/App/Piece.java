package edu.sjsu.cs.cs151.checkers.App;

/**
 * Piece represents a single Checkers playing piece.
 */
public class Piece {

	public enum Color {
		RED, BLACK
	}

	Piece(Color color, Location location) {
		this.color = color;
		this.location = location;
		unidirectional = true;
		size = new Size(1, 1);
	}

	/**
	 * getRect returns a Rect object that other classes can interact with.
	 * 
	 * @return a Rect object with the Piece's size and location
	 */
	public Rect getRect() {
		return new Rect(size, location);
	}

	/**
	 * makeKing allows the Piece object to be moved both forward and backward.
	 */
	public void makeKing() {
		unidirectional = false;
	}
	
// Getters and Setters
	
	public Color getColor() {
	   return color;
	}
	
	public Location getLocation() {
	   return location;
	}
	
	public boolean isUnidirectional() {
	   return unidirectional;
	}
	
	public Size getSize() {
	   return size;
	}
	
// Private fields
	
	private Color color;
   private Location location;
   private boolean unidirectional;
   private Size size;
   
}