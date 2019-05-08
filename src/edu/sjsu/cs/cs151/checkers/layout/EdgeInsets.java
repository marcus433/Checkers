package edu.sjsu.cs.cs151.checkers.layout;

public class EdgeInsets {
	public EdgeInsets(int top, int left, int bottom, int right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}

	// Getters

	public int getTop() {
		return this.top;
	}

	public int getLeft() {
		return this.left;
	}

	public int getBottom() {
		return this.bottom;
	}

	public int getRight() {
		return this.right;
	}

	// Setters

	public void setTop(int inset) {
		this.top = inset;
	}

	public void setLeft(int inset) {
		this.left = inset;
	}

	public void setBottom(int inset) {
		this.bottom = inset;
	}

	public void setRight(int inset) {
		this.right = inset;
	}

	// Private Fields

	private int top = 0;
	private int left = 0;
	private int bottom = 0;
	private int right = 0;
}