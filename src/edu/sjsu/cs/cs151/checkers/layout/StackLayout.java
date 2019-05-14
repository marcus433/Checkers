package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Layout to present children in a
 * ordered vertical or horizontal
 * flex-view
 * */
public class StackLayout implements Layout {

	public enum Direction {
		VERTICAL,
		HORIZONTAL
	}
	
	/**
	 * Creates a new StackLayout
	 * @param direction - direction to stack children in
	 * @param spacimg - pixel spacing between children
	 * @param children - child layouts
	 * */
	public StackLayout(Direction direction, int spacing, ArrayList<Layout> children) {
		this.direction = direction;
		this.spacing = spacing;
		this.children = new ArrayList<>(children);
	}

	/**
	 * Calculates Child Size and Position
	 * based on Alignment, parent size
	 * and child size
	 * @param size - Size of parent
	 * @param location - X,Y layout position of parent
	 * */
	public void renderWithSize(Dimension size, Point location) {
		int lastOrigin = 0;
		int staticSpace = 0;
		int sizedChildren = 0;

		for (int i = 0; i < children.size(); i++) {
			Layout child = children.get(i);
			if (child.getDisplaySize(size, location) != null) {
				staticSpace += this.direction == Direction.VERTICAL ? child.getDisplaySize(size, location).getHeight() : child.getDisplaySize(size, location).getWidth();
				sizedChildren++;
			}
		}

		if (this.direction == Direction.VERTICAL) {
			int rowHeight = children.size() == sizedChildren ? 0 : Math.max(((int)size.getHeight() - staticSpace - (this.spacing * (children.size() - 1))) / (children.size() - sizedChildren), 0);
			for (int i = 0; i < children.size(); i++) {
				Layout child = children.get(i);
				if (child.getDisplaySize(size, location) != null) {
					child.setSize((int)child.getDisplaySize(size, location).getWidth(), (int)child.getDisplaySize(size, location).getHeight());
					child.setLocation((int)location.getX(), lastOrigin + (int)location.getY());
					lastOrigin += child.getDisplaySize(size, location).getHeight();
				} else {
					child.setSize((int)size.getWidth(), rowHeight);
					child.setLocation((int)location.getX(), lastOrigin + (int)location.getY());
					lastOrigin += rowHeight;
				}
				lastOrigin += spacing;
				child.renderWithSize(child.getSize(), child.getLocation());
			}
		} else {
			int columnWidth = children.size() == sizedChildren ? 0 : Math.max(((int)size.getWidth() - staticSpace - (this.spacing * (children.size() - 1))) / (children.size() - sizedChildren), 0);
			for (int i = 0; i < children.size(); i++) {
				Layout child = children.get(i);
				if (child.getDisplaySize(size, location) != null) {
					child.setSize((int)child.getDisplaySize(size, location).getWidth(), (int)child.getDisplaySize(size, location).getHeight());
					child.setLocation(lastOrigin + (int)location.getX(), (int)location.getY());
					lastOrigin += (int)child.getDisplaySize(size, location).getWidth();
				} else {
					child.setSize((int)columnWidth, (int)size.getHeight());
					child.setLocation(lastOrigin + (int)location.getX(), (int)location.getY());
					lastOrigin += columnWidth;
				}
				lastOrigin += spacing;
				child.renderWithSize(child.getSize(), child.getLocation());
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

	private Direction direction;
	private int spacing = 0;
	private ArrayList<Layout> children;

	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
}