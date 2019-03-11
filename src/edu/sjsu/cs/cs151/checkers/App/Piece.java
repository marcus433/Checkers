package edu.sjsu.cs.cs151.checkers.App;

/**
 * Piece represents a single Checkers playing piece.
 */
public class Piece {

	public enum Color {
		RED, BLACK
	}

	Color color;
	Location location;
	boolean isUnidirectional;
	Size size;

	Piece(Color color, Location location) {
		this.color = color;
		this.location = location;
		isUnidirectional = true;
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
		isUnidirectional = false;
	}
}