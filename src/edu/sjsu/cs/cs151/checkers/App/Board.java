package edu.sjsu.cs.cs151.checkers.App;

public class Board {
	Board(Game game) {
		this.game = game;
	  this.checkers = new Checker[DEFAULT_SIZE][DEFAULT_SIZE];
	  this.generate();
	}

	public void selectChecker(Position position) {
		Checker currentChecker = checkerForPosition(position);
		if (currentChecker.getColor() == this.game.getCurrentColor()) {
			if (this.lastSelected != null) {
				Checker lastChecker = checkerForPosition(this.lastSelected);
				lastChecker.deselect();
			}
			this.lastSelected = position;
			currentChecker.select();
		}
	}

	public void movePiece(Position position) throws Error {
		if (lastSelected == null) {
			throw new Error("No piece selected"); // TODO: add custom error
		}
		Position finalPos = position;
		if (isPositionValid(position)) {
			Checker currentChecker = checkerForPosition(this.lastSelected);
			Checker newChecker = checkerForPosition(position);
			newChecker.setPiece(currentChecker.getPiece());
			currentChecker.clearPiece();
			Piece piece = newChecker.getPiece();
			// NOTE: this implementation automatically jumps for the player. We will make this optional in future
			// if any corner has piece of other player then attempt to move to it's diagonal recursively
			// Jump logic here. set finalPos 

			// TODO: determine whether player colors switch or if there is a winner, make players kings
			if (piece.getColor() == TOP_COLOR) {
				if (finalPos.getRow() == DEFAULT_SIZE - 1)
					piece.makeKing();
					
			} else if (piece.getColor() == BOTTOM_COLOR) {
				if (finalPos.getRow() == 0)
					piece.makeKing();
			}
			this.game.checkForWinner();
		} else {
			throw new Error("Invalid destination position"); // TODO: add custom error
		}
	}

	public void removePiece(Position position) {
		Checker checker = checkerForPosition(position);
		this.game.decrementPiecesByColor(checker.getPiece().getColor());
		checker.clearPiece();
	}

	public Checker checkerForPosition(Position position) {
		return this.checker[position.getRow()][position.getColumn()];
	}

	private boolean isPositionValid(Position position) {
		Checker currentChecker = checkerForPosition(this.lastSelected);
		Piece piece = currentChecker.getPiece();
		int row = this.lastSelected.getRow();
		int column = this.lastSelected.getColumn();
		int destRow = position.getRow();
		int destCol = position.getColumn();
		if (destRow > DEFAULT_SIZE - 1 || destRow < 0) // destination position out of bounds
			return false;
		if (destCol > DEFAULT_SIZE - 1 || destCol < 0) // destination position out of bounds
			return false;
		// TODO: cleanup implementation, lots of repetition
		if (piece.isKing()) {
				if (row - 1 == destRow && column - 1 == destCol) {
					// top left
					return !checkerForPosition(new Position(destRow, destCol)).hasPiece();
				} else if (row - 1 == destRow && column + 1 == destCol) {
					// top right
					return !checkerForPosition(new Position(destRow, destCol)).hasPiece();
				} else if (row + 1 == destRow && column - 1 == destCol) {
					// bottom left
					return !checkerForPosition(new Position(destRow, destCol)).hasPiece();
				} else if (row + 1 == destRow && column + 1 == destCol) {
					// bottom right
					return !checkerForPosition(new Position(destRow, destCol)).hasPiece();
				}
		} else {
			if (piece.getColor() == TOP_COLOR) {
				if (row + 1 == destRow && column - 1 == destCol) {
					// bottom left
					return !checkerForPosition(new Position(destRow, destCol)).hasPiece();
				} else if (row + 1 == destRow && column + 1 == destCol) {
					// bottom right
					return !checkerForPosition(new Position(destRow, destCol)).hasPiece();
				}
			} else if (piece.getColor() == BOTTOM_COLOR) {
				if (row - 1 == destRow && column - 1 == destCol) {
					// top left
					return !checkerForPosition(new Position(destRow, destCol)).hasPiece();
				} else if (row - 1 == destRow && column + 1 == destCol) {
					// top right
					return !checkerForPosition(new Position(destRow, destCol)).hasPiece();
				}
			}
		}
		return false;
	}

	private Position positionForJump(Checker checker) {
		//
	}

	private Checker genCheckerForPosition(Position position) {
		int rowMod = position.getRow() % 2;
		int positionMod = (position.getColumn() + rowMod) % 2;
		Piece piece = null;
		if (positionMod == 1) {
			int checkerIdx = (position.getRow() * DEFAULT_SIZE) + position.getColumn() + 1;
			if (checkerIdx <= PIECES_PER_SIDE * 2)
				piece = new Piece(TOP_COLOR);
			else if (checkerIdx >= ((DEFAULT_SIZE * DEFAULT_SIZE) - (PIECES_PER_SIDE * 2)) + 1)
				piece = new Piece(BOTTOM_COLOR);
		}
		return new Checker(positionMod == 1, piece);
	}

	private void generate() {
		for (int row = 0; row < this.checkers.length; row++) {
			for (int col = 0; col < this.checkers[row].length; col++) {
				this.checkers[row][col] = this.genCheckerForPosition(new Position(row, col));
			}
		}
	}

	// Getters and Setters
	
	public Piece[] getPieces() {
		return pieces;
	}
	
	public Piece getCurrentPiece() {
		return currentPiece;
	}
	
	// Public constants

	public static final int DEFAULT_SIZE = 8;
	public static final int PIECES_PER_SIDE = 12;
	public static final Piece.Color TOP_COLOR = Piece.Color.RED;
	public static final Piece.Color BOTTOM_COLOR = Piece.Color.BLACK;

	// Private fields

	private Checker[][] checkers;
	private Position lastSelected;
	private Game game;
}