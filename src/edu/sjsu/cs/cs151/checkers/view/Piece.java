package edu.sjsu.cs.cs151.checkers.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import edu.sjsu.cs.cs151.checkers.layout.View;

/**
 * Piece represents a single Checkers playing piece.
 */
public class Piece extends View {

	public enum Color {
		RED, BLACK
	}
	
	public enum Type {
		PAWN, KING
	}

	public Piece(Type type, Color color) {
		super();
		this.type = type;
		this.color = color;
		setSize(DEFAULT_SIZE, DEFAULT_SIZE);
		this.setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(color == Color.RED ? 
					new java.awt.Color(0xDC245E) :
					new java.awt.Color(0x393BF9));
		g.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
	}

	@Override
	public Dimension getDisplaySize(Dimension size, Point location) {
		return new Dimension(30, 30);
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