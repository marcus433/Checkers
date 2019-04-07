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
		// TODO: the main logic happens here
	}

	public void removePiece(Position position) {
		Checker checker = checkerForPosition(position);
		checker.clearPiece();
	}

	public Checker checkerForPosition(Position position) {
		return this.checker[position.getRow()][position.getColumn()];
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