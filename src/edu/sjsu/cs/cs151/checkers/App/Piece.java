package edu.sjsu.cs.cs151.checkers.App;

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

	public Rect getRect() {
		return new Rect(size, location);
	}

	public void makeKing() {
		isUnidirectional = false;
	}
}