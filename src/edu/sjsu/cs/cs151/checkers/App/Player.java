package edu.sjsu.cs.cs151.checkers.App;

/**
 * Player is a representation of data tied to invididual players in a given game of Checkers.
 */
public class Player {

	private Gameboard gameboard; // gameboard reference. We may implement this with a protocol delegate later.

	public int numWonGames = 0;
	public Piece.Color color;
	public int numPieces = 0; // TODO: adjust based on determined board size.

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
}