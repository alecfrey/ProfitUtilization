package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Public OutageTest class with JUnit test cases.
 * 
 * @author Alec Frey
 *
 */
public class OutageTest {

	/**
	 * Tests last condition in Outage next method.
	 */
	@Test
	public void test1() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Outage(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Outage(t, 1, 1);
		assertEquals(State.EMPTY, t.grid[0][0].next(t).who());
	}

	// Test getProfit
	@Test
	public void test3() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Outage(t, 0, 0);
		t.grid[0][1] = new Outage(t, 0, 1);
		t.grid[1][0] = new Outage(t, 1, 0);
		t.grid[1][1] = new Outage(t, 1, 1);
		assertEquals(0, ISPBusiness.getProfit(t));
		System.out.println(t);
	}
}