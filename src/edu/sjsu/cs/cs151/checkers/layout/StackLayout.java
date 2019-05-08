package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

public class StackLayout implements Layout {

	public enum Direction {
		VERTICAL,
		HORIZONTAL
	}

	public StackLayout(Direction direction, int spacing, ArrayList<Layout> children) {
		this.direction = direction;
		this.spacing = spacing;
		this.children = new ArrayList<>(children);
	}

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
			int rowHeight = Math.max(((int)size.getHeight() - staticSpace - (this.spacing * (children.size() - 1))) / (children.size() - sizedChildren), 0);
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
			int columnWidth = Math.max(((int)size.getWidth() - staticSpace - (this.spacing * (children.size() - 1))) / (children.size() - sizedChildren), 0);
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

	private Direction direction;
	private int spacing = 0;
	private ArrayList<Layout> children;

	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
}