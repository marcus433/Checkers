package edu.sjsu.cs.cs151.checkers.Tests;

import static org.junit.Assert.*;
import org.junit.Test;

public class RectTest {
	@Test
	public void testRect() {
		Size size = new Size(1, 2);
		Location location = new Location(3, 4);
		Rect rect = Rect(size, location);
		assertEquals(rect.size.width, 1);
		assertEquals(rect.size.height, 2);
		assertEquals(rect.location.x, 3);
		assertEquals(rect.location.y, 4);
	}
}
