package edu.sjsu.cs.cs151.checkers.App;

/**
 * Game models the game flow of a round of Checkers.
 */
public class Game {

	Game() {
		reset();
	}

	/**
	 * switchPlayerTurn switches the active player color from red to black, and vice versa.
	 */
	public void switchPlayerTurn() {
		currentPlayerColor = currentPlayerColor == Piece.Color.RED ? Piece.Color.BLACK : Piece.Color.RED;
	}

	/**
	 * reset returns the game to its initial state by:
	 * setting the active player color to RED, instantiating two new players, and instantiating a new board.
	 */
	public void reset() {
		this.currentPlayerColor = Piece.Color.RED;
		this.players = new Player[2];
		this.gameboard = new Gameboard();
	}
	
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
	
	private Piece.Color currentPlayerColor;
   private Player[] players;
   private Gameboard gameboard;
}