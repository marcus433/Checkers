package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.controller.AnimationController;
import edu.sjsu.cs.cs151.checkers.layout.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
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
		Point changeOrigin = new Point(0, 0);
		Point changeDestination = new Point(0, 0);
		Boolean isChange = false;
		JPanel view = new JPanel();
		Checker[][] checkers = model.getBoard();
		edu.sjsu.cs.cs151.checkers.view.Piece temp = null;
		
		/*for (int row = 0; row < checkers.length; row++) {
			for (int column = 0; column < checkers[row].length; column++) {
				Checker checker = checkers[row][column];
				edu.sjsu.cs.cs151.checkers.model.Piece piece = checker.getPiece();
				Tile tile = (Tile) tiles.get((8 * row) + column);
				edu.sjsu.cs.cs151.checkers.view.Piece pieceView = tile.getPiece();
				if (pieceView != null && !pieceView.isVisible() && checker.hasPiece()) {
					changeDestination = tile.getLocation();
					changeDestination = new Point((int)changeDestination.getX() + 5, (int)changeDestination.getY() + 5);
					isChange = true;
				} else if (pieceView != null && pieceView.isVisible() && !checker.hasPiece()) {
					changeOrigin = tile.getLocation();
					changeOrigin = new Point((int)changeOrigin.getX() + 5, (int)changeOrigin.getY() + 5);
					view = pieceView;
					temp = pieceView.copy();
					isChange = true;
				}
			}
		}
		if (isChange && temp != null && view != null) {
			view.setVisible(false);
			temp.setSize(view.getSize());
			temp.setLocation(changeOrigin);
			add(temp);
			temp.setVisible(true);
			System.out.println(changeOrigin);
			System.out.println(changeDestination);
			Gameboard that = this;
			new AnimationController(temp)
				.animateTo(1000, changeDestination)
				.onComplete(new AnimationController.Callback(){
		            @Override
		            public void onSuccess() {
		            		//that.remove(temp);
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

		            @Override
		            public void onError(String err) {
		                System.out.println(err);
		            }
		        });
		} else {*/
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