package edu.sjsu.cs.cs151.checkers.App;

/**
 * Player is a representation of data tied to invididual players in a given game of Checkers.
 */
public class Player {

	Player() {
		
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
	
// Private fields
	
	private Gameboard gameboard; // gameboard reference. We may implement this with a protocol delegate later.

   private int numWonGames = 0;
   private Piece.Color color;
   private int numPieces = 0; // TODO: adjust based on determined board size.

   
}