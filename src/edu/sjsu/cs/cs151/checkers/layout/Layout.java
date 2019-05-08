package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;

public interface Layout {
	public void renderWithSize(Dimension size, Point location);
	public int getWidth();
	public int getHeight();

	public Dimension getSize();
	public Dimension getDisplaySize(Dimension size, Point location);
	public void setSize(int width, int height);
	public void setLocation(int x, int y);
	public Point getLocation();
}