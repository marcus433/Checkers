package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Interface that must be conformed to
 * in order to layout any view
 * */
public interface Layout {
	/**
	 * Calculates Child Size and Position
	 * based on Alignment, parent size
	 * and child size
	 * @param size - Size of parent
	 * @param location - X,Y layout position of parent
	 * */
	public void renderWithSize(Dimension size, Point location);
	/**
	 * Gets width of layout
	 * @return width - Width of layout
	 * */
	public int getWidth();
	/**
	 * Gets height of layout
	 * @return height - Height of layout
	 * */
	public int getHeight();

	/**
	 * Gets Size of layout
	 * @return size - Dimensions of layout
	 * */
	public Dimension getSize();
	/**
	 * Conforms to Layout Interface.
	 * Doesn't have a fixed layout size.
	 * @param size - Size of parent
	 * @param location - X,Y layout position of parent
	 * @return size - Fixed size of layout, otherwise null if not fixed.
	 * */
	public Dimension getDisplaySize(Dimension size, Point location);
	/**
	 * Set size of layout
	 * @param width - Width of layout
	 * @param height - Height of layout
	 * */
	public void setSize(int width, int height);
	/**
	 * Set position of layout
	 * @param x - X position of layout
	 * @param y - Y position of layout
	 * */
	public void setLocation(int x, int y);
	/**
	 * Getter for Layout position
	 * @return position - Point of x,y coordinates
	 * */
	public Point getLocation();
}