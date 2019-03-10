package edu.sjsu.cs.cs151.checkers.App;

public class Game {

	Piece.Color currentPlayerColor;
	Player[] players;
	Gameboard gameboard;

	Game() {
		reset();
	}

	public void switchPlayerTurn() {
		currentPlayerColor = currentPlayerColor == Piece.Color.RED ? 
													Piece.Color.BLACK : Piece.Color.RED;
	}

	public void reset() {
		this.currentPlayerColor = Piece.Color.RED;
		this.players = new Player[2];
		this.gameboard = new Gameboard();
	}
}