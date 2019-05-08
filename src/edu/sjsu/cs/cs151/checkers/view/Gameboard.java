package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.layout.*;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import edu.sjsu.cs.cs151.checkers.model.Checker;
import edu.sjsu.cs.cs151.checkers.model.Piece;
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
import edu.sjsu.cs.cs151.checkers.model.Model;

/**
 * Gameboard tracks the positions of each Piece currently in play.
 */
public class Gameboard extends View {
	public Gameboard() {
		//this.setLayout(new GridLayout(8, 8));
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
	
	public void updateState(Model model) {
		Checker[][] checkers = model.getBoard();
		for (int row = 0; row < checkers.length; row++) {
			for (int column = 0; column < checkers[row].length; column++) {
				Checker checker = checkers[row][column];
				edu.sjsu.cs.cs151.checkers.model.Piece piece = checker.getPiece();
				Tile tile = (Tile) tiles.get((8 * row) + column);
				edu.sjsu.cs.cs151.checkers.view.Piece pieceView = tile.getPiece();
				if (piece != null && pieceView != null) {
					pieceView.setType(piece.isKing() ? edu.sjsu.cs.cs151.checkers.view.Piece.Type.KING : edu.sjsu.cs.cs151.checkers.view.Piece.Type.PAWN);
					pieceView.setColor(piece.getColor() == edu.sjsu.cs.cs151.checkers.model.Piece.Color.RED ? edu.sjsu.cs.cs151.checkers.view.Piece.Color.RED : edu.sjsu.cs.cs151.checkers.view.Piece.Color.BLACK);
					pieceView.setVisible(true);
					if (checker.isSelected()) {
						pieceView.select();
					} else {
						pieceView.deselect();
					}
				} else if (pieceView != null) {
					pieceView.setVisible(false);
				}
			}
		}
		repaint();
	}
	
	// Getters and Setters
	
	/**
	 * getCurrentPiece returns the current piece selected by the player.
	 * @return: the selected Piece object by the current player
	 */
	public Piece getCurrentPiece() {
	   return currentPiece;
	}

	@Override
	public
	Layout layoutThatFits() {
		return new GridLayout(new ArrayList<Layout>(tiles));
	}
	
	// Private fields
	
	private ArrayList<Tile> tiles;
	private Piece currentPiece;
   
}