package edu.sjsu.cs.cs151.checkers.view;

/**
 * Player is a representation of data tied to invididual players in a given game of Checkers.
 */
public class Player {

	public Player() {
		this.numWonGames = DEFAULT_NUM_WINS;
		this.color = DEFAULT_COLOR;
		this.numPieces = DEFAULT_NUM_PIECES;
	}

	/**
	 * movePiece moves a Piece object from one Location to another.
	 * 
	 * @param piece: the selected piece to move
	 * @param location: the destination to move the selected piece
	 */
	public void movePiece(Piece piece, Location location) {
		//
	}

	/**
	 * removePiece removes a Piece object from play, decreasing the total number of active pieces.
	 * 
	 * @param piece: the selected piece for removal
	 */
	public void removePiece(Piece piece) {
		//
	}
	
	// Getters and Setters
	
	/**
	 * getNumWonGames returns the number of games a particular player has won.
	 * @return: an integer number of games won by this player
	 */
	public int getNumWonGames() {
	   return numWonGames;
	}
	
	/**
	 * getColor returns the color that represents this particular player's pieces
	 * @return: a Piece.Color object that matches this player's pieces
	 */
	public Piece.Color getColor() {
	   return color;
	}
	
	/**
	 * getNumPieces returns the number of pieces that are still available to this player
	 * @return: an integer number of still active pieces
	 */
	public int getNumPieces() {
	   return numPieces;
	}
	
	/**
	 * setColor sets the player's color to either red or black, allowing them to interact with the same color pieces.
	 */
	public void setColor(Piece.Color color) {
	   this.color = color;
	}
	
	// Private fields
	
	private static final int DEFAULT_NUM_WINS = 0;
	private static final Piece.Color DEFAULT_COLOR = Piece.Color.RED;
	private static final int DEFAULT_NUM_PIECES = 12;
	private Gameboard gameboard; // TODO: gameboard reference. We may implement this with a protocol delegate later.
	private int numWonGames;
	private Piece.Color color;
	private int numPieces; // TODO: adjust based on determined board size.

   
}