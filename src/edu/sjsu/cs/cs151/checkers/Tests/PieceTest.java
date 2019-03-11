package edu.sjsu.cs.cs151.checkers.Tests;

import static org.junit.Assert.*;
import org.junit.Test;

public class PieceTest {

	@Test
	public void testPiece() {
		Piece piece = new Piece(Piece.Color.RED, new Location(1, 2));
		assertEquals(piece.color, Piece.Color.RED);
		assertEquals(piece.location.x, 1);
		assertEquals(piece.location.y, 2);
	}

	@Test
	public void testGetRect() {
		Piece piece = new Piece(Piece.Color.RED, new Location(1, 2));
		Rect rect = piece.getRect();
		assertEquals(rect.size.width, 1);
		assertEquals(rect.size.height, 1);
		assertEquals(rect.location.x, 1);
		assertEquals(rect.location.y, 2);
	}

	@Test
	public void testMakeKing() {
		Piece piece = new Piece(Piece.Color.RED, new Location(1, 2));
		assertEquals(piece.isUnidirectional, true);
		piece.makeKing()
		assertEquals(piece.isUnidirectional, false);
		piece.makeKing()
		assertEquals(piece.isUnidirectional, false);
	}
}