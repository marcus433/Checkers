package edu.sjsu.cs.cs151.checkers.view;

import edu.sjsu.cs.cs151.checkers.layout.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Label extends JLabel implements Layout {
	public Label(String text) {
		super(text);
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			InputStream stream = new BufferedInputStream(new FileInputStream("assets/CircularStd-Black.otf"));
			setFont(Font.createFont(Font.TRUETYPE_FONT, stream));
		} catch (FontFormatException e) {
			System.out.println(e);
			System.out.println("Failed to load font");
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("Failed to load font");
		}
		setFont(getFont().deriveFont(20f)); 
		setForeground(new Color(0x0A245F));
		setVerticalAlignment(JLabel.CENTER);
	}
	
	public void renderWithSize(Dimension size, Point location) {
		setSize((int)size.getWidth(), (int)size.getHeight());
		setLocation(new Point((int)location.getX(), (int)location.getY() - FONT_OFFSET));
	}

	public Dimension getDisplaySize(Dimension size, Point location) {
		return null;
	}

	public void setDisplaySize(Dimension size) {
		this.displaySize = size;
	}

	// Private fields

	private Dimension displaySize = null;
	private static int FONT_OFFSET = 3;
}