package edu.sjsu.cs.cs151.checkers.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import edu.sjsu.cs.cs151.checkers.layout.View;

/**
 * Piece represents a single Checkers playing piece.
 */
public class Piece extends View {

	public enum Color {
		RED, BLACK, WHITE
	}
	
	public enum Type {
		PAWN, KING
	}

	public Piece(Type type, Color color) {
		super();
		this.type = type;
		this.color = color;
		this.setOpaque(false);
		try {
			if (kingIcon == null) {
				kingIcon = ImageIO.read(new File("assets/king.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		if (isSelected) {
			g2.setColor(java.awt.Color.WHITE);
			g2.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		}
		g2.setColor(color == Color.RED ? 
				new java.awt.Color(0xDC245E) :
				new java.awt.Color(0x393BF9));
		if (isSelected) {
			g2.fillOval(2, 2, g.getClipBounds().width - 4, g.getClipBounds().height - 4);
		} else {
			g2.fillOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		}
		
		double iconWidth = g2.getClipBounds().width * 0.50;
		int iconHeight = (int) (iconWidth * (kingIcon.getHeight() / kingIcon.getWidth()));
		
		int x = (int) (g2.getClipBounds().width / 2.0 - (iconWidth / 2.0));
		int y = (int) (g2.getClipBounds().height / 2.0 - (iconHeight / 2.0));
		
		g.drawImage(kingIcon, x, y, (int)iconWidth, iconHeight, g2.getColor(), null);
	}
	
	public void select() {
		isSelected = true;
		repaint();
	}
	
	public void deselect() {
		isSelected = false;
		repaint();
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
	private boolean isSelected = false;
	private static BufferedImage kingIcon;
}