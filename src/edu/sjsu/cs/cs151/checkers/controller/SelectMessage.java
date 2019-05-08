package edu.sjsu.cs.cs151.checkers.controller;

/**
 * SelectMessage is a Message generated when a player attempts to select a piece by clicking it.
 * It communicates the coordinates of the mouse when clicking from View to Controller.
 * @author seanz
 *
 */
public class SelectMessage extends Message {
   
   public SelectMessage(int row, int col) {
      this.row = row;
      this.col = col;
   }
   
   public int getRow() {
      return row;
   }
   
   public int getColumn() {
      return col;
   }

   // Private fields
   
   int row;
   int col;
}
