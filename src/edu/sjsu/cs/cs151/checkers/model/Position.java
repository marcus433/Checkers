package edu.sjsu.cs.cs151.checkers.model;

/**
 * Position is a coordinate representation of location on the board.
 */
public class Position {
  
	/**
	 * Creates a new position
	 * @param row - Row
	 * @param column - Column
	 * @throws Error
	 * */
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
	
	/**
	 * CHecks equality of 2 positions.
	 * @param pos - Position to compare to
	 * @return boolean - If equal
	 * */
	public boolean equals(Position pos) {
	   return (this.row == pos.row && this.column == pos.column);
	}
	
	/**
	 * Converts Position to a String
	 * */
	public String toString() {
		return getRow() + ", " + getColumn();
	}
	
	/**
	 * Checks equality of position & object.
	 * @param o - Object to compare to
	 * @return boolean - If equal
	 * */
	@Override
	public
	boolean equals(Object o) {
		Position pos = (Position)o;
		return this.row == pos.getRow() && this.column == pos.getColumn();
	}
 	
	// Private fields
	
	private static final int MIN_POSITION = 0;
	private int row;
	private int column;
}