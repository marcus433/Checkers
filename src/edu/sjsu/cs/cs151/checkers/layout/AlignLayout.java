package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;

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

	public AlignLayout(Direction direction, Alignment alignment, Layout child) {
		this.direction = direction;
		this.alignment = alignment;
		this.child = child;
	}

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

	private Alignment alignment;
	private Direction direction;
	private Layout child;

	private int x = 0;
	private int y = 0;
	private int width = 0;
	private int height = 0;
}