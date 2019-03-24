package edu.sjsu.cs.cs151.checkers.App;

/**
 * Pawn is a subclass of Piece. Pawns can only move forward.
 */
public class Pawn extends Piece {
	Pawn() {
		this.unidirectional = true;
	}
}