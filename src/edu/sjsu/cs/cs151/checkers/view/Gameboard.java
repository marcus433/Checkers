package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.controller.AnimationController;
import edu.sjsu.cs.cs151.checkers.layout.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

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
public class Gameboard extends View {
	/**
	 * Creates new gameboard
	 * */
	public Gameboard() {
		//this.setLayout(new GridLayout(8, 8));
		setLayout(null);
		tiles = new ArrayList<>();
		this.setOpaque(false);
		for (int i = 0; i < 64; i++) {
			Tile tile = new Tile((i + i / 8 % 2) % 2 == 1);
			tile.setRow((int)Math.floor(i / 8));
			tile.setColumn(i % 8);
			tiles.add(tile);
			add(tile);
		}
	}
	
	// Getters and Setters
	
	/**
	 * getCurrentPiece returns the current piece selected by the player.
	 * @return - the selected Piece object by the current player
	 */
	public Piece getCurrentPiece() {
	   return currentPiece;
	}
	
	/**
	 * Gets tiles for view
	 * @return tiles
	 * */
	public ArrayList<Tile> getTiles() {
	   return tiles;
	}
	
	/**
	 * Creates new layout nested structure
	 * @return layout - nested layouts
	 * */
	@Override
	public
	Layout layoutThatFits() {
		return new GridLayout(new ArrayList<Layout>(tiles));
	}
	
	// Private fields
	
	private ArrayList<Tile> tiles;
	private Piece currentPiece;
   
}