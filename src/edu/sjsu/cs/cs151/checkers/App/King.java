package edu.sjsu.cs.cs151.checkers.App;

/**
 * King is a subclass of Piece. Kinged pieces may move both forward and backward.
 */
public class King extends Piece {

	King() {
		this.isUnidirectional = false;
	}
}