package edu.sjsu.cs.cs151.checkers.App;

public class Game {
	Game() {
		reset();
	}

	public void switchPlayerTurn() {
		currentColor = currentColor == Piece.Color.RED ? Piece.Color.BLACK : Piece.Color.RED;
	}

	public void reset() {
		currentColor = Piece.Color.BLACK;
		board = new Board(this);
		redCount = Board.PIECES_PER_SIDE;
		blackCount = Board.PIECES_PER_SIDE;
	}

	public void checkForWinner() {
		if (this.redCount == 0)
			System.out.println("Black player won");
		else if (this.blackCount == 0)
			System.out.println("Red player won");
	}
	
	// Getters and Setters	

	public Piece.Color getCurrentColor() {
	   return currentColor;
	}

	public void decrementPiecesByColor(Piece.Color color) {
		if (color == Piece.Color.RED)
			redCount--;
		else if (color == Piece.Color.BLACK)
			blackCount--;
	}
	
	// Private fields
	
	private Piece.Color currentColor;
	private int redCount;
	private int blackCount;
	private Board board;
}