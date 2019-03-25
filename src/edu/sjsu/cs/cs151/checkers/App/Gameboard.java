package edu.sjsu.cs.cs151.checkers.App;

/**
 * Gameboard tracks the positions of each Piece currently in play.
 */
public class Gameboard {

	public Size gridSize;
	public Piece[] pieces;
	public Piece currentPiece;

	Gameboard() {
		this.gridSize = new Size(1, 1);
		this.currentPiece = new Piece(Piece.Type.KING, Piece.Color.BLACK);
		this.pieces = new Piece[gridSize.width * gridSize.height];
	}

	/**
	 * selectPiece enables players to choose an individual piece on the board to interact with.
	 * 
	 * @param location: the Location on the board that the desired piece occupies
	 */
	public void selectPiece(Location location) {
		//TODO: implement selectPiece
	}

	/**
	 * isValidMove determines whether an intended destination for the currentPiece is possible.
	 * 
	 * @param destination: the desired Location on the board to move currentPiece
	 * @return true if move is legal; false otherwise
	 */
	public boolean isValidMove(Location destination) {
	   //TODO: implement isValidMove
	   return false;
	}

	/**
	 * movePiece moves the currentPiece from its current Location to a valid destination Location.
	 * 
	 * @param destination: the desired Location on the board to move currentPiece
	 */
	public void movePiece(Location destination) {
		//this.currentPiece.setLocation(destination); // TODO: use setter & getter so we can add layout hooks.
	}
}