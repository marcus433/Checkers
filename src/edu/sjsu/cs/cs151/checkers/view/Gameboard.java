package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.model.Size;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import edu.sjsu.cs.cs151.checkers.model.Checker;
import edu.sjsu.cs.cs151.checkers.model.Location;
/*
this.setLayout(null);
setBackground(Color.BLUE);
Piece blackPawn = new Piece(Piece.Type.PAWN, Piece.Color.BLACK);
Piece redPawn = new Piece(Piece.Type.PAWN, Piece.Color.RED);
Animate gridAnimation = new Animate(blackPawn);
Animate gridAnimation2 = new Animate(redPawn);
//gridAnimation.animateTo(500, new Location(300, 500));
add(blackPawn);
add(redPawn);
add(new Toolbar());
for (int row = 0; row < 8; row++) {
	for (int col = 0; col < 8; col++) {
		int columnRotated = row % 2 == 0 ? col : 7 - col;
		gridAnimation.animateTo(500, new Location(columnRotated * 100, row * 100));
		gridAnimation2.animateTo(500, new Location((7 - columnRotated) * 100, (7 - row) * 100));
	}
}
*/

/**
 * Gameboard tracks the positions of each Piece currently in play.
 */
public class Gameboard extends JPanel {
	public Gameboard() {
		this.setLayout(new GridLayout(8, 8));
		this.setOpaque(false);
		for (int i = 0; i < 64; i++) {
			this.add(new Tile((i + i / 8 % 2) % 2 == 1));
		}
	}
	
	// Getters and Setters
	
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
	
	private Size gridSize;
	private Piece[] pieces;
	private Piece currentPiece;
   
}