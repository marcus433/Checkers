package edu.sjsu.cs.cs151.checkers.controller;

/**
 * Piece represents an individual piece used by a player in a game of checkers.
 */
public class Piece {

	public enum Color {
		RED, BLACK
	}

	public Piece(Color color) {
		this.color = color;
		this.isKing = false;
	}
	
	// Getters and Setters

	/**
	 * getColor returns the color element of the current piece.
	 * @return - the color of the current piece, either RED or BLACK.
	 */
	public Color getColor() {
	   return this.color;
	}

	/**
	 * isKing returns the king status of the current piece.
	 * @return - 1 if king, 0 otherwise
	 */
	public boolean isKing() {
		return this.isKing;
	}

	/**
	 * makeKing converts the current piece from a pawn into a king.
	 * Kinged pieces have more movement options, expanded upon in the Board class.
	 */
	public void makeKing() {
		this.isKing = true;
	}
	
	// Private fields

	private Color color;
	private boolean isKing;
}