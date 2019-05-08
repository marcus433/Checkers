package edu.sjsu.cs.cs151.checkers.model;

import edu.sjsu.cs.cs151.checkers.model.Piece.Color;

public class Model {
   
   private Model() {
      board = new Checker[DEFAULT_SIZE][DEFAULT_SIZE];
      reset();
   }

   /**
    * reset() restores the game board to its initial state.
    * By default, it constructs an 8x8 array of Checkers,
    * and populates them with three rows of Pieces for each side.
    */
   public void reset() {
      this.remainingBlackPieces = DEFAULT_NUM_PIECES_PER_PLAYER;
      this.remainingRedPieces = DEFAULT_NUM_PIECES_PER_PLAYER;
      this.blackWon = false;
      this.redWon = false;
      this.currentColor = Color.RED;
      this.origin = null;
      this.currentPiece = null;
      
      // Checker tile generation loop
      for (int row = 0; row < DEFAULT_SIZE; row++) {
         for (int col = 0; col < DEFAULT_SIZE; col++) {
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
      
      int numPopulatedRows = DEFAULT_NUM_PIECES_PER_PLAYER / (DEFAULT_SIZE / 2);
      
      // Black piece generation loop
      for (int row = 0; row < numPopulatedRows; row++) {
         for (int col = 0; col < DEFAULT_SIZE; col++) {
            if (board[row][col].isValid()) {
               board[row][col].setPiece(new Piece(Color.BLACK));
            }
         }
      }
      
      // Red piece generation loop
      for (int row = DEFAULT_SIZE - 1; row >= DEFAULT_SIZE - numPopulatedRows; row--) {
         for (int col = DEFAULT_SIZE - 1; col >= 0; col--) {
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
    * @return true if a valid piece was selected, false otherwise
    */
   public boolean selectChecker(Position pos) {
      if (board[pos.getRow()][pos.getColumn()].hasPiece()
            && board[pos.getRow()][pos.getColumn()].getPiece().getColor() == currentColor) {
         this.origin = pos;
         this.currentPiece = board[pos.getRow()][pos.getColumn()].getPiece();
         return true;
      }
      return false;
   }
   
   /**
    * determineValidMoves decides which Positions around the origin and currentPiece contain
    * Checkers that can be safely moved to.
    * @return Position[] validMoves - an array of Positions that correspond to valid Checkers.
    *    0 corresponds to single-space moves.
    *    1, 2, 3, and 4 are jump moves, going from lower left, to lower right, to upper left, to upper right.
    * @precondition selectChecker has been called previously
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
    * @precondition selectChecker has been called previously
    */
   public boolean movePiece(Position dest) {
      // Do nothing if the destination isn't valid, or if it's already occupied
      if (!board[dest.getRow()][dest.getColumn()].isValid() 
            || board[dest.getRow()][dest.getColumn()].hasPiece())
         return false;
      
      // Determine if the destination corresponds to a valid Position to move to
      int whichMove = -1;
      Position[] validMoves = determineValidMoves();
      for (int i = 0; i < validMoves.length; i++) {
         if (validMoves[i] != null && dest.equals(validMoves[i])) {
            whichMove = i;
            break;
         }
      }
      if (whichMove == -1)
         return false;
      
      // Update the current piece's Position and Checker.
      board[origin.getRow()][origin.getColumn()].clearPiece();
      board[dest.getRow()][dest.getColumn()].setPiece(currentPiece);
      
      // Check if the move was a jump; if it was, remove the correct pieces.
      if (whichMove > 0 || whichMove <= 4)
         jump(whichMove, dest);
      
      // If the current piece made it all the way to the other side of the board, make it a king.
      if (dest.getRow() == 0 && currentPiece.getColor() == Color.RED
            || dest.getRow() == 7 && currentPiece.getColor() == Color.BLACK)
         currentPiece.makeKing();
      
      // Deselect the previous piece and checker.
      currentPiece = null;
      origin = null;
      
      // Switch player control to the opposite color.
      switchTurn();
      
      // See if a win condition was reached.
      checkWinCondition();
      
      return true;
   }
   
   /**
    * jump() moves a piece by two spaces and jumps over an opponent piece, removing it from play.
    * It is called exclusively by movePiece in the event that a destination Position requires a jump.
    * @param whichMove - an int, 1-4, that determines the direction that the piece will jump.
    *    1 = lower left, 2 = lower right, 3 = upper left, 4 = upper right
    * @param dest - the destination Position
    * @precondition 1 <= whichMove <= 4
    */
   public void jump(int whichMove, Position dest) {
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
      
      // All jump moves reduce the total number of pieces on the opponent side.
      if (currentColor == Color.BLACK)
         remainingRedPieces--;
      else // if (currentColor == Color.RED)
         remainingBlackPieces--;
   }
   
   /**
    * switchTurn alters the game state by switching the color currently in play.
    */
   public void switchTurn() {
      currentColor = currentColor == Color.RED ? Color.BLACK : Color.RED;
      origin = null;
      currentPiece = null;
   }
   
   /**
    * checkWinCondition checks to see if either color has won by eliminating all opponent pieces.
    * It sets either blackWon or redWon to true if the opposing remaining__Pieces == 0.
    */
   public void checkWinCondition() {
      if (remainingRedPieces == 0)
         blackWon = true;
      else if (remainingBlackPieces == 0)
         redWon = true;
   }
   
   /**
    * getInstance returns the static instance of Model. (Singleton Pattern)
    * @return instance - the instance of Model
    */
   public Model getInstance() {
      return instance;
   }
   
   /**
    * getBoard returns board, a private field.
    * @return board - a 2d Checker array
    */
   public Checker[][] getBoard() {
      return board;
   }
   
// Private fields
   
   private static Model instance;
   private static final int DEFAULT_SIZE = 8;
   private static final int DEFAULT_NUM_PIECES_PER_PLAYER = 12;
   private int remainingRedPieces;
   private int remainingBlackPieces;
   private Position origin;
   private Piece currentPiece;
   private Checker[][] board;
   private boolean blackWon;
   private boolean redWon;
   private Piece.Color currentColor;
}
