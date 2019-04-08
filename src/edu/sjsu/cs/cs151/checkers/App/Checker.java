package edu.sjsu.cs.cs151.checkers.App;

public class Checker {

	Checker(boolean canHoldPiece, Piece piece) {
		this.canHoldPiece = canHoldPiece;
		this.isSelected = false;
		this.piece = piece;
	}
	
	public boolean hasPiece() {
		return this.piece != null;
	}

	public void clearPiece() {
		this.piece = null;
	}

	// Getters and Setters
	
	public Piece getPiece() {
		return this.piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public boolean isSelected() {
		return this.isSelected;
	}

	public void select() {
		this.isSelected = true;
	}

	public void deselect() {
		this.isSelected = false;
	}
	
	// Private fields
	
	private boolean canHoldPiece;
	private Piece piece;
	private boolean isSelected;
}