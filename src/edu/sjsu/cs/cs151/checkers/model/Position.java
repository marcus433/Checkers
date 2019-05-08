package edu.sjsu.cs.cs151.checkers.model;

/**
 * Position is a coordinate representation of location on the board.
 */
public class Position {
  
   public Position(int row, int column) throws Error {
  	if (row >= this.MIN_POSITION && column >= this.MIN_POSITION) {
			this.row = row;
			this.column = column;
		} else {
			this.row = -1;
			this.column = -1;
			// TODO: throw an actual error? Or leave it like this?
		}
	}

  // Getters and Setters	

   /**
    * getRow returns the row element of the current position.
    * @return - which row the current position is located, as an int
    */
	public int getRow() {
	   return this.row;
	}
	
	/**
    * getColumn returns the column element of the current position.
    * @return - which column the current position is located, as an int
    */
	public int getColumn() {
	   return this.column;
	}
	
	public boolean equals(Position pos) {
	   return (this.row == pos.row && this.column == pos.column);
	}
	
	// Private fields
	
	private static final int MIN_POSITION = 0;
	private int row;
	private int column;
}