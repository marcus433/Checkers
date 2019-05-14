package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Aligns a set of Views in a grid-pattern
 * */
public class GridLayout implements Layout {
	/**
	 * Creates a new layout
	 * @param children - Child layouts
	 * */
	public GridLayout(ArrayList<Layout> children) {
		this.children = children;
	}

	/**
	 * Calculates Child Size and Position
	 * based on Alignment, parent size
	 * and child size
	 * @param size - Size of parent
	 * @param location - X,Y layout position of parent
	 * */
	public void renderWithSize(Dimension size, Point location) {
		int numItemsPerSide = (int) Math.floor(Math.sqrt(children.size()));
		int dimension = (int)Math.floor(size.getWidth() > size.getHeight() ? size.getHeight() : size.getWidth() / (double)numItemsPerSide);
		int originY = 0;
		int originX = 0;
		for (int i = 0; i < children.size(); i++) {
			Layout child = children.get(i);
			child.setSize(dimension, dimension);
			child.setLocation(originX, originY);
			child.renderWithSize(child.getSize(), child.getLocation());
			originX += dimension;
			if ((i + 1) % numItemsPerSide == 0) {
				originX = 0;
				originY += dimension;
			}
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

	private ArrayList<Layout> children;

	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
}