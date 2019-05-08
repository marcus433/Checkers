package edu.sjsu.cs.cs151.checkers.controller;

/**
 * ReleaseMessage is a Message generated when a user releases the mouse button after selecting a piece.
 * It communicates the coordinates of the mouse when released in View to Controller.
 * @author seanz
 *
 */
public class ReleaseMessage extends Message {

   public ReleaseMessage(int row, int col) {
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
