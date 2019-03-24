package edu.sjsu.cs.cs151.checkers.App;

/**
 * Gameboard tracks the positions of each Piece currently in play.
 */
public class Gameboard {

	Gameboard() {
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
		this.currentPiece.location = destination; // TODO: use setter & getter so we can add layout hooks.
	}
	
// Getters and Setters
	/**
	 * getSize returns the Size of the gameboard.
	 * @return: the Size object of the board
	 */
	public Size getSize() {
	   return gridSize;
	}
	
	/**
	 * getPieces returns the array of all pieces in play.
	 * @return: the Piece[] array, pieces
	 */
	public Piece[] getPieces() {
	   return pieces;
	}
	
	/**
	 * getCurrentPiece returns the current piece selected by the player.
	 * @return: the selected Piece object by the current player
	 */
	public Piece getCurrentPiece() {
	   return currentPiece;
	}
	
// Private fields
	
	private Size gridSize = Size(8, 8); // Unit is blocks
   private Piece[] pieces;
   private Piece currentPiece;

   
}