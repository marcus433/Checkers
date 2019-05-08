package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.layout.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Toolbar for game
 */
public class Toolbar extends View {
	public Toolbar() {
		super();
		this.setLayout(null);
		this.setOpaque(false);
		currentTurn = new CurrentTurn();
		add(currentTurn);
		//setBorder(new EmptyBorder(20, 20, 20, 20));
	}

	/*public void updateCurrentPlayer(Player player) {
		if (player.getColor() == Piece.Color.RED) {
			// TODO: update toolbar
		} else {
			// TODO: update toolbar
		}
	}*/

	private void undoMove() {
		// TODO: from button listener
	}

	private void resetGame() {
		// TODO: from button listener
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRoundRect(0, 0, g.getClipBounds().width, g.getClipBounds().height, 50, 50); // x y width height arcWidth arcHeight
		g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height - 26);
	}
	
	@Override
	public Dimension getDisplaySize(Dimension size, Point location) {
		return new Dimension(size.width, DEFAULT_HEIGHT); // temporary hack until layoutManager allows to fill on only 1 dimension
	}
	
	@Override
	public
	Layout layoutThatFits() {
		return new InsetLayout(new EdgeInsets(PADDING, PADDING, PADDING, PADDING), currentTurn);
	}

	public static final int DEFAULT_HEIGHT = 50;

	private CurrentTurn currentTurn;
	private int PADDING = 5;
}