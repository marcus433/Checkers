package edu.sjsu.cs.cs151.checkers.layout;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * JPanel wrapper that
 * conforms to layout
 * */
public class View extends JPanel implements Layout {
	/**
	 * Creates new view
	 * */
	public View() {
		super();
		setLayout(null);
	}
	
	/**
	 * Lays out view
	 * based on fixed
	 * dimensions
	 * */
	public void layout() {
		renderWithSize(getSize(), getLocation());
	}
	
	/**
	 * Calculates Child Size and Position
	 * based on Alignment, parent size
	 * and child size
	 * @param size - Size of parent
	 * @param location - X,Y layout position of parent
	 * */
	public void renderWithSize(Dimension size, Point location) {
		setSize((int)size.getWidth(), (int)size.getHeight());
		setLocation(location);
		Layout layout = layoutThatFits();
		if (layout != null)
			layout.renderWithSize(size, location);
	}
	
	/**
	 * Subclasses can override this
	 * method and return their
	 * Layouts
	 * @return layout - Nested layouts
	 * */
	public Layout layoutThatFits() {
		return null;
	}

	/**
	 * If fixed size then return it
	 * @param size - Parent size
	 * @param location - Parent location
	 * @return size - Fixed dimension size
	 * */
	public Dimension getDisplaySize(Dimension size, Point location) {
		return displaySize;
	}

	/**
	 * Set a new fixed size
	 * @param size - New fixed size to set
	 * */
	public void setDisplaySize(Dimension size) {
		this.displaySize = size;
	}

	// Private fields

	private Dimension displaySize = null;
}