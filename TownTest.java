package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Alec Frey
 * 
 * Public TownTest class with JUnit test cases.
 *
 */
public class TownTest {
	Town t = new Town(2, 2);

	/**
	 * Tests getting length of town.
	 */
	@Test
	void test1() {
		assertEquals(2, t.getLength());
	}

	/**
	 * Tests getting width of town.
	 */
	@Test
	void test2() {
		assertEquals(2, t.getWidth());
	}

	/**
	 * Tests randomly initializing grid with cells.
	 */
	@Test
	void test3() {
		t.randomInit(7);
		System.out.println(t.toString());

		assertEquals(State.STREAMER, t.grid[1][1].who());
	}
}
