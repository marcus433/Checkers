package edu.sjsu.cs.cs151.checkers.App;

public class Game {
	Game() {
		reset();
	}

	public void switchPlayerTurn() {
		currentColor = currentColor == Piece.Color.RED ? Piece.Color.BLACK : Piece.Color.RED;
	}

	public void reset() {
		currentColor = Piece.Color.BLACK;
		board = new Board(this);
		redCount = Board.PIECES_PER_SIDE;
		blackCount = Board.PIECES_PER_SIDE;
	}
	
	// Getters and Setters	

	public Piece.Color getCurrentColor() {
	   return currentColor;
	}
	
	// Private fields
	
	private Piece.Color currentColor;
	private int redCount;
	private int blackCount;
	private Board board;
}