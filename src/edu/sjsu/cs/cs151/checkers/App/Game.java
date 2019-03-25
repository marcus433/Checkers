package edu.sjsu.cs.cs151.checkers.App;

import javax.swing.JPanel;

/**
 * Game models the game flow of a round of Checkers.
 */
public class Game extends JPanel {
	Game() {
		super();
		reset();

		// FOR ANIMATION TESTING PURPOSES
		this.setLayout(null);
		Piece blackKing = new Piece(Piece.Type.KING, Piece.Color.RED);
		Animate gridAnimation = new Animate(100);
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				//int columnRotated = row % 2 == 0 ? 8 - col : col;
				gridAnimation.animateTo(blackKing, new Location(row * 100, col * 100));
			}
		}
		add(blackKing);
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
		currentPlayerColor = Piece.Color.RED;
		players = new Player[2];
		gameboard = new Gameboard();
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