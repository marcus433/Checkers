package edu.sjsu.cs.cs151.checkers.controller;

/**
 * SelectMessage is a Message generated when a player attempts to select a piece by clicking it.
 * It communicates the coordinates of the mouse when clicking from View to Controller.
 * @author seanz
 *
 */
public class SelectMessage extends Message {
   
	/**
	 * Selects a piece
	 * @param row - row index
	 * @param col - column index
	 * */
   public SelectMessage(int row, int col) {
      this.row = row;
      this.col = col;
   }
   
   /**
    * Gets row to select
    * @return row
    * */
   public int getRow() {
      return row;
   }
   
   /**
    * Gets column to select
    * @return col
    * */
   public int getColumn() {
      return col;
   }

   // Private fields
   
   int row;
   int col;
}
