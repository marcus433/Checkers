package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;

public class InsetLayout implements Layout {
	public InsetLayout(EdgeInsets insets, Layout child) {
		this.insets = insets;
		this.child = child;
	}

	public void renderWithSize(Dimension size, Point location) {
		if (child.getDisplaySize(size, location) == null) {
			child.setSize((int)size.getWidth() - (insets.getLeft() + insets.getRight()), (int)size.getHeight() - (insets.getTop() + insets.getBottom()));
		} else {
			Dimension displaySize = child.getDisplaySize(size, location);
			child.setSize((int)displaySize.getWidth(), (int)displaySize.getHeight());
		}
		child.setLocation((int)location.getX() + insets.getLeft(), (int)location.getY() + insets.getTop());
		child.renderWithSize(child.getSize(), child.getLocation());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Dimension getSize() {
		return new Dimension(getWidth(), getHeight());
	}

	public Dimension getDisplaySize(Dimension size, Point location) {
		return null;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point getLocation() {
		return new Point(x, y);
	}

	// Private fields

	private EdgeInsets insets;
	private Layout child;

	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
}