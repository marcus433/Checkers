package edu.sjsu.cs.cs151.checkers.App;

public class Position {
   
  Position(int row, int column) throws Error {
  	if (row >= this.MIN_POSITION && column >= this.MIN_POSITION) {
			this.row = row;
			this.column = column;
		} else {
			throw new Error("Invalid position"); // TODO: make custom error type
		}
	}

  // Getters and Setters	

	public int getRow() {
	   return this.row;
	}
	
	public int getColumn() {
	   return this.column;
	}
	
	// Private fields
	
	private static final int MIN_POSITION = 0;
	private int row;
	private int column;
}