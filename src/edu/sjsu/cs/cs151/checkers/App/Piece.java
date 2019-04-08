package edu.sjsu.cs.cs151.checkers.App;

public class Piece {

	public enum Color {
		RED, BLACK
	}

	Piece(Color color) {
		this.color = color;
		this.isKing = false;
	}
	
	// Getters and Setters

	public Color getColor() {
	   return this.color;
	}

	public boolean isKing() {
		return this.isKing;
	}

	public void makeKing() {
		this.isKing = true;
	}
	
	// Private fields

	private Color color;
	private boolean isKing;
}