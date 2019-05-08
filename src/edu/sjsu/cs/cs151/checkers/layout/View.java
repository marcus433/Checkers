package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JPanel;

public class View extends JPanel implements Layout {
	public View() {
		super();
		setLayout(null);
	}

	public void layout() {
		renderWithSize(getSize(), getLocation());
	}

	public void renderWithSize(Dimension size, Point location) {
		setSize((int)size.getWidth(), (int)size.getHeight());
		setLocation(location);
		Layout layout = layoutThatFits();
		if (layout != null)
			layout.renderWithSize(size, location);
	}

	public Layout layoutThatFits() {
		return null;
	}

	public Dimension getDisplaySize(Dimension size, Point location) {
		return null;
	}

	public void setDisplaySize(Dimension size) {
		this.displaySize = size;
	}

	// Private fields

	private Dimension displaySize = null;
}