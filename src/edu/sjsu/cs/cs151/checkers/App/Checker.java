package edu.sjsu.cs.cs151.checkers.App;

/**
 * Checker is a representation of a given tile on the checkerboard.
 */
public class Checker {

	Checker(boolean canHoldPiece, Piece piece) {
		this.canHoldPiece = canHoldPiece;
		this.isSelected = false;
		this.piece = piece;
	}
	
	/**
	 * hasPiece determines whether a piece is currently occupying the current Checker.
	 * @return - 1 if the piece element exists; 0 if the piece element is null
	 */
	public boolean hasPiece() {
		return this.piece != null;
	}

	/**
	 * clearPiece removes a piece that is occupying the current Checker
	 * by setting it to null.
	 */
	public void clearPiece() {
		this.piece = null;
	}

	// Getters and Setters
	
	/**
	 * getPiece returns the piece that is occupying the current Checker, if any.
	 * @return: a Piece object, or null if there is no such Piece
	 */
	public Piece getPiece() {
		return this.piece;
	}

	/**
	 * setPiece sets the Piece occupying the current Checker to a different object.
	 * @param piece: the new piece to be placed on the Checker
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * isSelected determines whether this particular Checker on the board has been selected.
	 * @return: 1 if selected, 0 otherwise
	 */
	public boolean isSelected() {
		return this.isSelected;
	}

	/**
	 * select highlights this particular Checker object to be used in other Board methods
	 * by setting the isSelected flag to true.
	 */
	public void select() {
		this.isSelected = true;
	}

	/**
    * deselect turns the isSelected flag to false, allowing Board methods that would previously
    * affect this Checker to ignore it instead.
    */
	public void deselect() {
		this.isSelected = false;
	}
	
	// Private fields
	
	private boolean canHoldPiece;
	private Piece piece;
	private boolean isSelected;
}