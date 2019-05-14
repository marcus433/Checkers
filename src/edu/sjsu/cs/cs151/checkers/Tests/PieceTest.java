package edu.sjsu.cs.cs151.checkers.Tests;

import edu.sjsu.cs.cs151.checkers.model.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests a Piece
 * */
public class PieceTest {
	/**
	 * Tests creating a piece
	 * */
	@Test
	public void testPiece() {
		Piece piece = new Piece(Piece.Color.RED);
		assertEquals(piece.getColor(), Piece.Color.RED);
	}
	
	/**
	 * Tests making a piece
	 * king
	 * */
	@Test
	public void testMakeKing() {
		Piece piece = new Piece(Piece.Color.RED);
		piece.makeKing();
		assertEquals(piece.isKing(), true);
	}
}