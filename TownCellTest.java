package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * @author Alec Frey
 * 
 * Public CasualTest class with JUnit test cases.
 *
 */
public class TownCellTest {

	/**
	 * Tests census method in TownCell after randomly assigning grid with seed and
	 * using next method.
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testCensus() throws FileNotFoundException {
		Town t = new Town(2, 2);
		t.randomInit(10);
		assertEquals(State.EMPTY, t.grid[1][1].next(t).who());
	}

	/**
	 * Tests who method in TownCell class for returning type of current cell.
	 */
	@Test
	public void testWho() {
		Town t = new Town(2, 2);
		t.grid[1][1] = new Casual(t, 1, 1);
		assertEquals(State.CASUAL, t.grid[1][1].who());

	}
}
