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
      this.canJumpAgain = false;
      
      // Checker tile generation loop
      for (int row = 0; row < DEFAULT_SIZE; row++) {
         for (int col = 0; col < DEFAULT_SIZE; col++) {
        	 	board[row][col] = new Checker((((row % 2) + col) % 2) == 1, null);
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
      // If canJumpAgain is true, then lock selectChecker - no other Pieces can be selected other than the current one.
	  if (canJumpAgain)
         return true;
      else if (board[pos.getRow()][pos.getColumn()].hasPiece()
            && board[pos.getRow()][pos.getColumn()].getPiece().getColor() == currentColor) {
         this.origin = pos;
         if (this.currentChecker != null)
        	 	this.currentChecker.deselect();
         this.currentPiece = board[pos.getRow()][pos.getColumn()].getPiece();
         this.currentChecker = board[pos.getRow()][pos.getColumn()];
         board[pos.getRow()][pos.getColumn()].select();
         return true;
      }
      deselect();
      return false;
   }
   
   /**
    * determineValidMoves decides which Positions around the origin and currentPiece contain
    * Checkers that can be safely moved to.
    * @return Position[] validMoves - an array of Positions that correspond to valid Checkers.
    *    0, 2, 4, and 6 correspond to single-space moves.
    *    1, 3, 5, and 7 are jump moves, going from lower left, to lower right, to upper left, to upper right.
    * @precondition selectChecker has been called previously
    */
   public Position[] determineValidMoves() {
      if (origin == null)
         return null;
      Position[] validMoves = new Position[8];
      int down = origin.getRow() + 1;
      int up = origin.getRow() - 1;
      int left = origin.getColumn() - 1;
      int right = origin.getColumn() + 1;
      int jumpDown = down + 1;
      int jumpUp = up - 1;
      int jumpLeft = left - 1;
      int jumpRight = right + 1;
      if (currentPiece.getColor() == Color.BLACK || currentPiece.isKing()) {
         // Adjacent space; lower left
         if (down < DEFAULT_SIZE && left >= 0 && !board[down][left].hasPiece())
            validMoves[0] = new Position(down, left);
         // Jump; lower left
         else if (jumpDown < DEFAULT_SIZE && jumpLeft >= 0 && !board[jumpDown][jumpLeft].hasPiece() 
               && board[down][left].getPiece().getColor() != currentPiece.getColor())
            validMoves[1] = new Position(jumpDown, jumpLeft);
            
         // Adjacent space; lower right
         if (down < DEFAULT_SIZE && right < DEFAULT_SIZE && !board[down][right].hasPiece())
            validMoves[2] = new Position(down, right);
         // Jump; lower right
         else if (jumpDown < DEFAULT_SIZE && jumpRight < DEFAULT_SIZE && !board[jumpDown][jumpRight].hasPiece() 
               && board[down][right].getPiece().getColor() != currentPiece.getColor())
            validMoves[3] = new Position(jumpDown, jumpRight);
      }
      if (currentPiece.getColor() == Color.RED || currentPiece.isKing()) {
         // Adjacent space; upper left
         if (up >= 0 && left >= 0 && !board[up][left].hasPiece())
            validMoves[4] = new Position(up, left);
         // Jump; upper left
         else if (jumpUp >= 0 && jumpLeft >= 0 && !board[jumpUp][jumpLeft].hasPiece() 
               && board[up][left].getPiece().getColor() != currentPiece.getColor())
            validMoves[5] = new Position(jumpUp, jumpLeft);
            
         // Adjacent space; upper right
         if (up >= 0 && right < DEFAULT_SIZE && !board[up][right].hasPiece())
            validMoves[6] = new Position(up, right);
         // Jump; upper right
         else if (jumpUp >= 0 && jumpRight < DEFAULT_SIZE && !board[jumpUp][jumpRight].hasPiece() 
               && board[up][right].getPiece().getColor() != currentPiece.getColor())
            validMoves[7] = new Position(jumpUp, jumpRight);
      }
      
      // Set any invalid Positions to null.
      for (int i = 0; i < validMoves.length; i++) {
         if (validMoves[i] != null && (validMoves[i].getRow() < 0 || validMoves[i].getColumn() < 0))
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
      if (validMoves == null)
         return false;
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
      if (whichMove % 2 == 1)
         jump(whichMove, dest);
      
      // If the current piece made it all the way to the other side of the board, make it a king.
      if (dest.getRow() == 0 && currentPiece.getColor() == Color.RED
            || dest.getRow() == 7 && currentPiece.getColor() == Color.BLACK)
         currentPiece.makeKing();
      
      // Deselect the previous piece and checker if done moving.
      if (!canJumpAgain) {
         deselect();
         // Switch player control to the opposite color.
         switchTurn();
      }
      
      // See if a win condition was reached.
      checkWinCondition();
      
      // origin.getRow()][origin.getColumn()
      // destination [dest.getRow()][dest.getColumn()]
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
      break;
      // Jump, lower right
      case 3: board[dest.getRow() - 1][dest.getColumn() - 1].clearPiece();
      break;
      // Jump, upper left
      case 5: board[dest.getRow() + 1][dest.getColumn() + 1].clearPiece();
      break;
      // Jump, upper right
      case 7: board[dest.getRow() + 1][dest.getColumn() - 1].clearPiece();
      break;
      default: {
         System.out.println("No more jumps.");
      }
      }
      
      // All jump moves reduce the total number of pieces on the opponent side.
      if (currentColor == Color.BLACK)
         remainingRedPieces--;
      else // if (currentColor == Color.RED)
         remainingBlackPieces--;
      
      // Update the current piece's position/Checker, and see if it can jump again.
      origin = dest;
      Position[] validMoves = determineValidMoves();
      canJumpAgain = false;
      // validMoves contains jump moves on odd numbers; only probe these.
      for (int i = 1; i < validMoves.length; i+=2) {
         if (validMoves[i] != null)
            canJumpAgain = true;
      }
   }
   
   /**
    * deselect sets the origin and currentPiece fields to null, resetting them.
    */
   public void deselect() {
      origin = null;
      currentPiece = null;
      currentChecker = null;
   }
   
   /**
    * switchTurn alters the game state by switching the color currently in play.
    */
   public void switchTurn() {
      currentColor = currentColor == Color.RED ? Color.BLACK : Color.RED;
      deselect();
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
   public static Model getInstance() {
	  if (instance == null)
		  instance = new Model();
      return instance;
   }
   
   /**
    * getBoard returns board, a private field.
    * @return board - a 2d Checker array
    */
   public Checker[][] getBoard() {
      return board;
   }
   
   /**
    * canJumpAgain returns whether a piece that has just previously jumped has remaining jump moves.
    * @return canJumpAgain - whether the currentPiece has any valid jump moves
    * @precondition jump() has already been called
    */
   public boolean canJumpAgain() {
      return canJumpAgain;
   }
   
   public Piece.Color getCurrentColor() {
	   return currentColor;
   }
   
   public Piece getCurrentPiece() {
      return currentPiece;
   }
   
// Private fields
   
   private static Model instance;
   private static final int DEFAULT_SIZE = 8;
   private static final int DEFAULT_NUM_PIECES_PER_PLAYER = 12;
   private int remainingRedPieces;
   private int remainingBlackPieces;
   private Position origin;
   private Piece currentPiece;
   private Checker currentChecker;
   private Checker[][] board;
   private boolean blackWon;
   private boolean redWon;
   private Piece.Color currentColor;
   private boolean canJumpAgain;
}
