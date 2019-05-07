package edu.sjsu.cs.cs151.checkers.controller;

import edu.sjsu.cs.cs151.checkers.model.Checker;
import edu.sjsu.cs.cs151.checkers.model.Piece;
import edu.sjsu.cs.cs151.checkers.model.Position;
import edu.sjsu.cs.cs151.checkers.singleton.Game;

/**
 * Board is a representation of the checkerboard used to play the game.
 * While Game handles the flow of a game session, Board handles the available actions a player can take.
 */
public class BoardController {
	public BoardController(Game game) {
		this.game = game;
	  this.checkers = new Checker[DEFAULT_SIZE][DEFAULT_SIZE];
	  this.generate();
	}

	/**
	 * selectChecker selects a given Checker object with a valid colored Piece on it.
	 */
	public void selectChecker(Position position) {
		Checker currentChecker = checkerForPosition(position);
		if (currentChecker.getPiece().getColor() == this.game.getCurrentColor()) {
			if (this.lastSelected != null) {
				Checker lastChecker = checkerForPosition(this.lastSelected);
				lastChecker.deselect();
			}
			this.lastSelected = position;
			currentChecker.select();
		}
	}

	/**
	 * movePiece removes a Piece from its current Checker and Position, and rewrites it in a new valid pair.
	 * 
	 * This current iteration of movePiece moves automatically, rather than allowing a player to select a location.
	 * 
	 * @param Position position: the current Position that the Piece occupies
	 */
	public void movePiece(Position position) throws Error {
		if (lastSelected == null) {
			throw new Error("No piece selected"); // TODO: add custom error
		}
		if (!moveFrom(this.lastSelected, position)) {
			throw new Error("Invalid destination position"); // TODO: add custom error
		}
		this.lastSelected = null;
		Position finalPos = position;
		Checker newChecker = checkerForPosition(position);
		Piece piece = newChecker.getPiece();

		// NOTE: this implementation automatically jumps for the player. We will make this optional in future

		finalPos = positionAfterJump(finalPos);

		if (piece.getColor() == TOP_COLOR) {
			if (finalPos.getRow() == DEFAULT_SIZE - 1)
				piece.makeKing();
		} else if (piece.getColor() == BOTTOM_COLOR) {
			if (finalPos.getRow() == 0)
				piece.makeKing();
		}
		this.game.checkForWinner();
	}

	
	/**
	 * moveFrom removes a Piece from its current Checker and Position, and rewrites it in a new valid pair.
	 * 
	 * This version requires an origin and a destination, and does not move automatically.
	 * 
	 * @param Position from: the origin Position of the moving Piece
	 * @param Position position: the destination of the moving piece
	 * @return: 1 if the move was successful; 0 otherwise
	 */
	private boolean moveFrom(Position from, Position position) {
		if (isPositionValid(position)) {
			Checker currentChecker = checkerForPosition(from);
			Checker newChecker = checkerForPosition(position);
			newChecker.setPiece(currentChecker.getPiece());
			currentChecker.clearPiece();
			currentChecker.deselect();
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * removePiece removes a Piece from play and frees up the Checker and Position it occupied.
	 * 
	 * @param Position position: the position to remove the Piece from
	 */
	public void removePiece(Position position) {
		Checker checker = checkerForPosition(position);
		this.game.decrementPiecesByColor(checker.getPiece().getColor());
		checker.clearPiece();
	}

	/**
	 * checkerForPosition translates a Position object into a Checker object
	 * by mapping a Position's coordinates to a single element in the checkers array element.
	 * 
	 * @param Position position: the position to convert into a Checker
	 * @return: the Checker object located at the given Position
	 */
	public Checker checkerForPosition(Position position) {
		return this.checkers[position.getRow()][position.getColumn()];
	}

	/**
	 * isPositionValid determines whether a given position is a valid location the Piece at the lastSelected Checker to move to.
	 * 
	 * @param Position position: the position to analyze
	 * @return: 1 if the position is a valid location to move to; 0 otherwise
	 */
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

	/**
	 * positionAfterJump checks the positions surrounding a given position, and automatically repeatedly
	 * jumps the given position's piece over the first available opponent piece.
	 * 
	 * @param Position finalPos: the current position after a move
	 * @return: recursively, the final position the Piece lands at after successive jumps
	 */
	private Position positionAfterJump(Position finalPos) {
		// TODO: clean code up
		Position position = finalPos;
		Checker checker = checkerForPosition(position);
		Piece currentPiece = checker.getPiece();
		Position pos = jumpToPosition(1, -1, position, currentPiece, checker);
		if (pos != null) {
			return positionAfterJump(pos);
		}
		pos = jumpToPosition(-1, -1, position, currentPiece, checker);
		if (pos != null) {
			return positionAfterJump(pos);
		}
		pos = jumpToPosition(-1, 1, position, currentPiece, checker);
		if (pos != null) {
			return positionAfterJump(pos);
		}
		pos = jumpToPosition(1, 1, position, currentPiece, checker);
		if (pos != null) {
			return positionAfterJump(pos);
		}
		// -1, -1
		// -1, 1
		// 1, -1
		// 1, 1
		return position;
	}

	/**
	 * jumpToPosition moves a Piece by jumping it over an opponent Piece and removing it from play.
	 * 
	 * @param int dX: the distance (in columns) from the currentPiece's position
	 * @param int dY: the distance (in rows) from the currentPiece's position
	 * @param Position position: the destination position to analyze
	 * @param Piece currentPiece: the piece to move
	 * @param Checker checker: the checker tile that holds the current piece
	 * @return: the position that has been moved to
	 */
	private Position jumpToPosition(int dX, int dY, Position position, Piece currentPiece, Checker checker) {
		try {
			// TODO: switch to using moveFrom
			// moveFrom(Position from, Position position)
			// top-left, top-right, bottom-left, bottom-right
			// considered valid if has opposing piece & has blank space after in the diagonal
			// removePiece after jumping
			Position pos = new Position(position.getRow() + dY, position.getColumn() + dX);
			Checker nextChecker = checkerForPosition(pos);
			Piece piece = nextChecker.getPiece();
			if (piece != null && piece.getColor() != currentPiece.getColor()) {
				Position nextPos = new Position(position.getRow() + dY + 1, position.getColumn() + dX + 1);    // direction and boundary checks needed
				Checker jumpTarget = checkerForPosition(nextPos);
				if (!jumpTarget.hasPiece()) {
					nextChecker.clearPiece();
					jumpTarget.setPiece(checker.getPiece());
					checker.clearPiece();
					return nextPos;
					// remove piece and jump piece
				}
			}
		} catch (Error e) {
			System.out.println("Position was out of bounds");
		}
		return null;
	}

	/**
	 * genCheckerForPosition produces a new Checker object at a given Position, and places a Piece on it if it is a valid space.
	 * This method is called exclusively by generate(), and is meant to be called at the beginning of the game.
	 * 
	 * @param Position position: the position to generate a Checker at
	 * @return: a Checker, with or without a particularly colored Piece element in it, given the Position
	 */
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

	/**
	 * generate populates new Checkers objects for each entry in the checkers array element
	 * by calling genCheckerForPosition at each position.
	 */
	private void generate() {
		for (int row = 0; row < this.checkers.length; row++) {
			for (int col = 0; col < this.checkers[row].length; col++) {
				this.checkers[row][col] = this.genCheckerForPosition(new Position(row, col));
			}
		}
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