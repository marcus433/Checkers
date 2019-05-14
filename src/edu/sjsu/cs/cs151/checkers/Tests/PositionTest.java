package edu.sjsu.cs.cs151.checkers.Tests;

import edu.sjsu.cs.cs151.checkers.model.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests Position
 * */
public class PositionTest {
	/**
	 * Tests position row, column, 
	 * and equality
	 * */
	@Test
	public void testPosition() {
		Position pos = new Position(1, 2);
		assertEquals(pos.getRow(), 1);
		assertEquals(pos.getColumn(), 2);
		Position pos2 = new Position(1, 2);
		assertEquals(pos, pos2);
	}
}
