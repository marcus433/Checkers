package edu.sjsu.cs.cs151.checkers.controller;

/**
 * Game determines the game logic and flow of the current game session.
 */
public class Game {
	public Game() {
		reset();
	}

	/**
	 * switchPlayerTurn allows the other player to interact with their corresponding pieces 
	 * by flipping currentColor from RED to BLACK, or vice versa.
	 */
	public void switchPlayerTurn() {
		currentColor = currentColor == Piece.Color.RED ? Piece.Color.BLACK : Piece.Color.RED;
	}

	/**
	 * reset alters the game and returns it to its initial state
	 * by resetting the number of pieces, their positions, and setting BLACK to go first.
	 */
	public void reset() {
		currentColor = Piece.Color.BLACK;
		board = new Board(this);
		redCount = Board.PIECES_PER_SIDE;
		blackCount = Board.PIECES_PER_SIDE;
	}

	/**
	 * checkForWinner checks if either player has reached an end game state
	 * by checking the number of remaining pieces of each color; if one player is out of
	 * pieces, then the other player wins.
	 * 
	 * This iteration of checkForWinner simply prints a win message.
	 */
	public void checkForWinner() {
		if (this.redCount == 0)
			System.out.println("Black player won");
		else if (this.blackCount == 0)
			System.out.println("Red player won");
	}
	
	// Getters and Setters	

	/**
	 * getCurrentColor returns which color is currently active and interactable.
	 * 
	 * @return - enum RED or BLACK, depending on which turn it is
	 */
	public Piece.Color getCurrentColor() {
	   return currentColor;
	}

	/**
	 * decrementPiecesByColor reduces the total number of pieces of a particular color.
	 * @param color: the color of the group of Pieces to decrement
	 */
	public void decrementPiecesByColor(Piece.Color color) {
		if (color == Piece.Color.RED)
			redCount--;
		else if (color == Piece.Color.BLACK)
			blackCount--;
	}
	
	// Private fields
	
	private Piece.Color currentColor;
	private int redCount;
	private int blackCount;
	private Board board;
}