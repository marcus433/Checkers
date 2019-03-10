package edu.sjsu.cs.cs151.checkers.App;

public class Gameboard {

	public Size gridSize = Size(8, 8); // Unit is blocks
	public Piece[] pieces;
	public Piece currentPiece;

	Gameboard() {
		this.pieces = new Piece[gridSize.wiidth * gridSize.height];
	}

	public void selectPiece(Location location) {
		//
	}

	public Bool isValidMove(Location destination) {
		return true;
	}

	public void movePiece(Location location) {
		this.currentPiece.location = location; // TODO: use setter & getter so we can add layout hooks.
	}
}