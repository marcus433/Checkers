package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Aligns a View with respect to an
 * alignment
 * */
public class AlignLayout implements Layout {
	
	public enum Direction {
		VERTICAL,
		HORIZONTAL
	}

	public enum Alignment {
		START,
		CENTER,
		END
	}
	
	/**
	 * Creates an instance of an alignment layout
	 * @param direction - Direction to align in
	 * @param alignment - Whether to align at start, end, or center
	 * @param child - Child view or layout
	 * */
	public AlignLayout(Direction direction, Alignment alignment, Layout child) {
		this.direction = direction;
		this.alignment = alignment;
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
		Dimension displaySize = child.getDisplaySize(size, location);
		if (displaySize != null) {
			child.setSize(displaySize.width, displaySize.height);
			if (direction == Direction.VERTICAL) {
				if (alignment == Alignment.CENTER) {
					child.setLocation((int)location.getX(), (int)location.getY() + (int)(size.getHeight() / 2.0 - displaySize.getHeight() / 2.0));
				} else if (alignment == Alignment.END) {
					child.setLocation((int)location.getX(), (int)location.getY() + (int)size.getHeight() - (int)displaySize.getHeight());
				}
			} else {
				if (alignment == Alignment.CENTER) {
					child.setLocation((int)location.getX() + (int)(size.getWidth() / 2.0 - displaySize.getWidth() / 2.0), (int)location.getY());
				} else if (alignment == Alignment.END) {
					child.setLocation((int)location.getX() + (int)size.getWidth() - (int)displaySize.getWidth(), (int)location.getY());
				}
			}
			child.renderWithSize(child.getSize(), child.getLocation());
		}
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

	private Alignment alignment;
	private Direction direction;
	private Layout child;

	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
}