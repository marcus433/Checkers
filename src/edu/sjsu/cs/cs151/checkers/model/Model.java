package edu.sjsu.cs.cs151.checkers.model;

import edu.sjsu.cs.cs151.checkers.model.Piece.Color;

public class Model {
   
   public Model() {
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
   
   /**
    * determineValidMoves decides which Positions around the origin and currentPiece contain
    * Checkers that can be safely moved to.
    * @return Position[] validMoves - an array of Positions that correspond to valid Checkers.
    *    0 corresponds to single-space moves.
    *    1, 2, 3, and 4 are jump moves, going from lower left, to lower right, to upper left, to upper right.
    */
   public Position[] determineValidMoves() {
      Position[] validMoves = new Position[5];
      if (currentPiece.getColor() == Color.BLACK || currentPiece.isKing()) {
         // Adjacent space; lower left
         if (!board[origin.getRow() + 1][origin.getColumn() - 1].hasPiece())
            validMoves[0] = new Position(origin.getRow() + 1, origin.getColumn() - 1);
         // Jump; lower left
         else if (!board[origin.getRow() + 2][origin.getColumn() - 2].hasPiece() 
               && board[origin.getRow() + 1][origin.getColumn() - 1].getPiece().getColor() != currentPiece.getColor())
            validMoves[1] = new Position(origin.getRow() + 2, origin.getColumn() - 2);
            
         // Adjacent space; lower right
         if (!board[origin.getRow() + 1][origin.getColumn() + 1].hasPiece())
            validMoves[0] = new Position(origin.getRow() + 1, origin.getColumn() + 1);
         // Jump; lower right
         else if (!board[origin.getRow() + 2][origin.getColumn() + 2].hasPiece() 
               && board[origin.getRow() + 1][origin.getColumn() + 1].getPiece().getColor() != currentPiece.getColor())
            validMoves[2] = new Position(origin.getRow() + 2, origin.getColumn() + 2);
      }
      else if (currentPiece.getColor() == Color.RED || currentPiece.isKing()) {
         // Adjacent space; upper left
         if (!board[origin.getRow() - 1][origin.getColumn() - 1].hasPiece())
            validMoves[0] = new Position(origin.getRow() - 1, origin.getColumn() - 1);
         // Jump; upper left
         else if (!board[origin.getRow() - 2][origin.getColumn() - 2].hasPiece() 
               && board[origin.getRow() - 1][origin.getColumn() - 1].getPiece().getColor() != currentPiece.getColor())
            validMoves[3] = new Position(origin.getRow() - 2, origin.getColumn() - 2);
            
         // Adjacent space; upper right
         if (!board[origin.getRow() - 1][origin.getColumn() + 1].hasPiece())
            validMoves[0] = new Position(origin.getRow() - 1, origin.getColumn() + 1);
         // Jump; upper right
         else if (!board[origin.getRow() - 2][origin.getColumn() + 2].hasPiece() 
               && board[origin.getRow() - 1][origin.getColumn() + 1].getPiece().getColor() != currentPiece.getColor())
            validMoves[4] = new Position(origin.getRow() - 2, origin.getColumn() + 2);
      }
      
      // Set any invalid Positions to null.
      for (int i = 0; i < validMoves.length; i++) {
         if (validMoves[i].getRow() < 0 || validMoves[i].getColumn() < 0)
            validMoves[i] = null;
      }
      return validMoves;
   }
   
   /**
    * movePiece moves a piece from one Position and Checker to another user-specified one.
    * If the selected destination is invalid, movePiece will simply return.
    * @param dest - the user-selected destination Position.
    */
   public void movePiece(Position dest) {
      // Do nothing if the destination isn't valid, or if it's already occupied
      if (!board[dest.getRow()][dest.getColumn()].isValid() 
            || board[dest.getRow()][dest.getColumn()].hasPiece())
         return;
      
      // Determine if the destination corresponds to a valid Position to move to
      int whichMove = -1;
      Position[] validMoves = determineValidMoves();
      for (int i = 0; i < validMoves.length; i++) {
         if (validMoves[i] != null && dest.equals(validMoves[i])) {
            whichMove = i;
            continue;
         }
      }
      if (whichMove == -1)
         return;
      
      // If whichMove corresponds to a jump move, determine where to remove the opponent piece.
      switch (whichMove) {
      // Jump, lower left
      case 1: board[dest.getRow() - 1][dest.getColumn() + 1].clearPiece();
      // Jump, lower right
      case 2: board[dest.getRow() - 1][dest.getColumn() - 1].clearPiece();
      // Jump, upper left
      case 3: board[dest.getRow() + 1][dest.getColumn() + 1].clearPiece();
      // Jump, upper right
      case 4: board[dest.getRow() + 1][dest.getColumn() - 1].clearPiece();
      default: {
         System.out.println("Something's wrong.");
      }
      }
      
      // Update the current piece's Position and Checker.
      board[origin.getRow()][origin.getColumn()].clearPiece();
      board[dest.getRow()][dest.getColumn()].setPiece(currentPiece);
      
      // All jump moves reduce the total number of pieces on the opponent side.
      if (whichMove > 0 || whichMove <= 4) {
         if (currentPiece.getColor() == Color.BLACK)
            remainingRedPieces--;
         else // if (currentPiece.getColor() == Color.RED)
            remainingBlackPieces--;
      }
      
   }
   
// Private fields
   
   private static final Size DEFAULT_GRID_SIZE = new Size(8, 8);
   private static final int DEFAULT_NUM_PIECES_PER_PLAYER = 12;
   private int remainingRedPieces;
   private int remainingBlackPieces;
   private Size gridSize;
   private Position origin;
   private Piece currentPiece;
   private Checker[][] board;
}
