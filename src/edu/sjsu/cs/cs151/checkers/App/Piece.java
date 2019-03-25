package edu.sjsu.cs.cs151.checkers.App;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Piece represents a single Checkers playing piece.
 */
public class Piece extends JPanel {

	public enum Color {
		RED, BLACK
	}
	
	public enum Type {
		PAWN, KING
	}

	Piece(Type type, Color color) {
		super();
		this.type = type;
		this.color = color;
		setSize(DEFAULT_SIZE, DEFAULT_SIZE);
		this.setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(color == Color.RED ? 
					java.awt.Color.red :
					java.awt.Color.black);
    		g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
	}
	
	// Getters and Setters
	
	public void setType(Type type) {
		this.type = type;
	}

	public Color getColor() {
	   return color;
	}
	
	// Private fields
	
	private static final int DEFAULT_SIZE = 100;
	private Color color;
	private Type type;
}