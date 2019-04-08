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
		// This will occur in GameBoard once it is built out
		this.setLayout(null);
		Piece blackPawn = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
		Piece redPawn = new Piece(Piece.Type.PAWN, Piece.Color.RED);
		Animate gridAnimation = new Animate(blackPawn);
		Animate gridAnimation2 = new Animate(redPawn);
		//gridAnimation.animateTo(500, new Location(300, 500));
		add(blackPawn);
		add(redPawn);
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				int columnRotated = row % 2 == 0 ? col : 7 - col;
				gridAnimation.animateTo(500, new Location(columnRotated * 100, row * 100));
				gridAnimation2.animateTo(500, new Location((7 - columnRotated) * 100, (7 - row) * 100));
			}
		}
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