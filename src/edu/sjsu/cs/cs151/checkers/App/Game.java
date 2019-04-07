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
		board = new Board();
		redCount = Board.PIECES_PER_SIDE;
		blackCount = Board.PIECES_PER_SIDE;
	}
	
	// TODO
	// Getters and Setters	
	
	/**
	 * getCurrentPlayerColor returns the current player's color.
	 * @return: the current player's color as a Piece.Color object
	 */
	public Piece.Color getCurrentPlayerColor() {
	   return currentPlayerColor;
	}
	
	/**
	 * getGameboard returns the current game's instance of the gameboard.
	 * @return: the gameboard currently in play
	 */
	public Gameboard getGameboard() {
	   return gameboard;
	}
	
	// Private fields
	
	private Piece.Color currentColor;
	private int redCount;
	private int blackCount;
	private Board board;
}