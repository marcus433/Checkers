package edu.sjsu.cs.cs151.checkers.App;

public class Player {

	private Gameboard gameboard; // gameboard reference. We may implement this with a protocol delegate later.

	public int numWonGames = 0;
	public Piece.Color color;
	public int numPieces = 0; // TODO: adjust based on determined board size.

	Player() {
		
	}

	public void movePiece(Piece piece, Location location) {
		//
	}

	public void removePiece(Piece piece) {
		//
	}
}