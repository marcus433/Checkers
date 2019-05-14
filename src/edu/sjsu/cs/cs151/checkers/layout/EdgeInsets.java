package edu.sjsu.cs.cs151.checkers.layout;

/**
 * Wrapper for a padding inset
 * */
public class EdgeInsets {
	/**
	 * Creates a new Inset
	 * @param top - top padding
	 * @param left - left padding
	 * @param bottom - bottom padding
	 * @param right - right padding
	 * */
	public EdgeInsets(int top, int left, int bottom, int right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}

	// Getters
	
	/**
	 * Getter for top inset
	 * @return inset - top inset
	 * */
	public int getTop() {
		return this.top;
	}
	
	/**
	 * Getter for left inset
	 * @return inset - left inset
	 * */
	public int getLeft() {
		return this.left;
	}
	
	/**
	 * Getter for bottom inset
	 * @return inset - bottom inset
	 * */
	public int getBottom() {
		return this.bottom;
	}

	/**
	 * Getter for right inset
	 * @return inset - right inset
	 * */
	public int getRight() {
		return this.right;
	}

	// Setters

	/**
	 * Setter for top inset
	 * @param inset - top inset
	 * */
	public void setTop(int inset) {
		this.top = inset;
	}
	
	/**
	 * Setter for left inset
	 * @param inset - left inset
	 * */
	public void setLeft(int inset) {
		this.left = inset;
	}
	
	/**
	 * Setter for bottom inset
	 * @param inset - bottom inset
	 * */
	public void setBottom(int inset) {
		this.bottom = inset;
	}
	
	/**
	 * Setter for right inset
	 * @param inset - right inset
	 * */
	public void setRight(int inset) {
		this.right = inset;
	}

	// Private Fields

	private int top = 0;
	private int left = 0;
	private int bottom = 0;
	private int right = 0;
}