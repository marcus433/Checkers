package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

public class GridLayout implements Layout {
	public GridLayout(ArrayList<Layout> children) {
		this.children = children;
	}

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

	private ArrayList<Layout> children;

	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
}