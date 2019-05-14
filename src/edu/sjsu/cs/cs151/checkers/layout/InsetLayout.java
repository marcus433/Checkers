package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Insets a child layout
 * */
public class InsetLayout implements Layout {
	/**
	 * Creates a new inset layout
	 * @param insets - Edge insets
	 * @param child - Child layout
	 * */
	public InsetLayout(EdgeInsets insets, Layout child) {
		this.insets = insets;
		this.child = child;
	}
	
	/**
	 * Calculates Child Size and Position
	 * based on Alignment, parent size
	 * and child size
	 * @param size - Size of parent
	 * @param location - X,Y layout position of parent
	 * */
	public void renderWithSize(Dimension size, Point location) {
		if (child.getDisplaySize(size, location) == null) {
			child.setSize((int)size.getWidth() - (insets.getLeft() + insets.getRight()), (int)size.getHeight() - (insets.getTop() + insets.getBottom()));
		} else {
			Dimension displaySize = child.getDisplaySize(size, location);
			child.setSize((int)displaySize.getWidth(), (int)displaySize.getHeight());
		}
		child.setLocation(insets.getLeft(), insets.getTop());
		child.renderWithSize(child.getSize(), child.getLocation());
	}

	/**
	 * Gets width of layout
	 * @return width - Width of layout
	 * */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets height of layout
	 * @return height - Height of layout
	 * */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets Size of layout
	 * @return size - Dimensions of layout
	 * */
	public Dimension getSize() {
		return new Dimension(getWidth(), getHeight());
	}

	/**
	 * Conforms to Layout Interface.
	 * Doesn't have a fixed layout size.
	 * @param size - Size of parent
	 * @param location - X,Y layout position of parent
	 * @return size - Fixed size of layout, otherwise null if not fixed.
	 * */
	public Dimension getDisplaySize(Dimension size, Point location) {
		return null;
	}

	/**
	 * Set size of layout
	 * @param width - Width of layout
	 * @param height - Height of layout
	 * */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Set position of layout
	 * @param x - X position of layout
	 * @param y - Y position of layout
	 * */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter for Layout position
	 * @return position - Point of x,y coordinates
	 * */
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