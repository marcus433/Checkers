package edu.sjsu.cs.cs151.checkers.model;

import edu.sjsu.cs.cs151.checkers.model.Piece.Color;

public class Gameboard {
   
   public Gameboard() {
      reset();
   }

   /**
    * reset() restores the game board to its initial state.
    * By default, it constructs an 8x8 array of Checkers,
    * and populates them with three rows of Pieces for each side.
    */
   public void reset() {
      this.gridSize = DEFAULT_GRID_SIZE;
      this.remainingBlackPieces = DEFAULT_NUM_PIECES_PER_PLAYER;
      this.remainingRedPieces = DEFAULT_NUM_PIECES_PER_PLAYER;
      
      // Checker tile generation loop
      for (int row = 0; row < gridSize.getHeight(); row++) {
         for (int col = 0; col < gridSize.getWidth(); col++) {
            if (row % 2 == 0) {
               if (col % 2 == 0) {
                  board[row][col] = new Checker(true, null);
               }
               else { // if (col % 2 == 1)
                  board[row][col] = new Checker(false, null);
               }
            }
            else { // if (row % 2 == 1)
               if (col % 2 == 0) {
                  board[row][col] = new Checker(false, null);
               }
               else { // if (col % 2 == 1)
                  board[row][col] = new Checker(true, null);
               }
            }
         }
      }
      
      int numPopulatedRows = DEFAULT_NUM_PIECES_PER_PLAYER / (gridSize.getHeight() / 2);
      
      // Black piece generation loop
      for (int row = 0; row < numPopulatedRows; row++) {
         for (int col = 0; col < gridSize.getWidth(); col++) {
            if (board[row][col].isValid()) {
               board[row][col].setPiece(new Piece(Color.BLACK));
            }
         }
      }
      
      // Red piece generation loop
      for (int row = gridSize.getHeight() - 1; row >= gridSize.getHeight() - numPopulatedRows; row--) {
         for (int col = gridSize.getWidth() - 1; col >= 0; col--) {
            if (board[row][col].isValid() && !board[row][col].hasPiece()) {
               board[row][col].setPiece(new Piece(Color.RED));
            }
         }
      }
   }

   /**
    * selectChecker sets the origin and currentPiece private fields.
    * origin currentPiece are used in other methods like movePiece.
    * @param pos - a user selected Position
    */
   public void selectChecker(Position pos) {
      this.origin = pos;
      this.currentPiece = board[pos.getRow()][pos.getColumn()].getPiece();
   }
   
   public void movePiece(Position dest) {
      // Do nothing if the destination isn't valid, or if it's already occupied
      if (!board[dest.getRow()][dest.getColumn()].isValid() 
            || board[dest.getRow()][dest.getColumn()].hasPiece())
         return;
      
      // Valid King moves
      if (currentPiece.isKing()) {
         
         // Move to an adjacent checker. No piece removal
         if (Math.abs(dest.getRow() - origin.getRow()) == 1
               && Math.abs(dest.getColumn() - origin.getColumn()) == 1) {
            board[origin.getRow()][origin.getColumn()].clearPiece();
            board[dest.getRow()][dest.getColumn()].setPiece(currentPiece);
            return;
         }
         // Jump - toward upper left
         else if (dest.getRow() - origin.getRow() == -2
               && dest.getColumn() - origin.getColumn() == -2
               && board[dest.getRow() + 1][dest.getColumn() + 1].hasPiece()
               && board[dest.getRow() + 1][dest.getColumn() + 1].getPiece().getColor() != currentPiece.getColor()) {
            board[origin.getRow()][origin.getColumn()].clearPiece();
            board[dest.getRow()][dest.getColumn()].setPiece(currentPiece);
            board[dest.getRow() + 1][dest.getColumn() + 1].clearPiece();
            if (currentPiece.getColor() == Color.BLACK)
               remainingRedPieces--;
            else // if (currentPiece.getColor() == Color.RED)
               remainingBlackPieces--;
         }
         // Jump - toward upper right
         else if (dest.getRow() - origin.getRow() == -2
               && dest.getColumn() - origin.getColumn() == 2
               && board[dest.getRow() + 1][dest.getColumn() - 1].hasPiece()
               && board[dest.getRow() + 1][dest.getColumn() - 1].getPiece().getColor() != currentPiece.getColor()) {
            board[origin.getRow()][origin.getColumn()].clearPiece();
            board[dest.getRow()][dest.getColumn()].setPiece(currentPiece);
            board[dest.getRow() + 1][dest.getColumn() - 1].clearPiece();
         }
         // Jump - toward lower left
         else if (dest.getRow() - origin.getRow() == 2
               && dest.getColumn() - origin.getColumn() == -2
               && board[dest.getRow() - 1][dest.getColumn() + 1].hasPiece()
               && board[dest.getRow() - 1][dest.getColumn() + 1].getPiece().getColor() != currentPiece.getColor()) {
            board[origin.getRow()][origin.getColumn()].clearPiece();
            board[dest.getRow()][dest.getColumn()].setPiece(currentPiece);
            board[dest.getRow() - 1][dest.getColumn() + 1].clearPiece();
         }
         // Jump - toward lower right
         else if (dest.getRow() - origin.getRow() == 2
               && dest.getColumn() - origin.getColumn() == 2
               && board[dest.getRow() - 1][dest.getColumn() - 1].hasPiece()
               && board[dest.getRow() - 1][dest.getColumn() - 1].getPiece().getColor() != currentPiece.getColor()) {
            board[origin.getRow()][origin.getColumn()].clearPiece();
            board[dest.getRow()][dest.getColumn()].setPiece(currentPiece);
            board[dest.getRow() - 1][dest.getColumn() - 1].clearPiece();
         }
         
         // All Jump actions remove an opponent piece.
         if (currentPiece.getColor() == Color.BLACK)
            remainingRedPieces--;
         else // if (currentPiece.getColor() == Color.RED)
            remainingBlackPieces--;
      }
      
      // Valid Black pawn moves
      else if (currentPiece.getColor() == Color.BLACK) {
         
      }
      
      // Valid Red pawn moves
      else if (currentPiece.getColor() == Color.RED) {
         
      }
   }
   
// Private fields
   
   private static final Size DEFAULT_GRID_SIZE = new Size(8, 8);
   private static final int DEFAULT_NUM_PIECES_PER_PLAYER = 12;
   private int remainingRedPieces;
   private int remainingBlackPieces;
   private Size gridSize;
   private Piece[] pieces;
   private Position origin;
   private Piece currentPiece;
   private Checker[][] board;
}
