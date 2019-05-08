package edu.sjsu.cs.cs151.checkers.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Toolbar for game
 */
public class Toolbar extends JPanel {
	public Toolbar() {
		super();
		this.setLayout(null);
		this.setOpaque(false);
		setSize(200, 50);
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

	public static final int DEFAULT_HEIGHT = 50;
}