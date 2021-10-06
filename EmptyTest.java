package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Alec Frey
 * 
 * Public EmptyTest class with JUnit test cases.
 *
 */
public class EmptyTest {

	/**
	 * Tests last condition in Empty cell next method.
	 */
	@Test
	public void test1() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Empty(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Outage(t, 1, 1);
		assertEquals(State.CASUAL, t.grid[0][0].next(t).who());
	}

	/**
	 * Tests to create Reseller cell since there is less than oe equal to one Outage
	 * & Empty cell in neighborhood.
	 */
	@Test
	public void test2() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Empty(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Reseller(t, 1, 0);
		t.grid[1][1] = new Outage(t, 1, 1);
		assertEquals(State.RESELLER, t.grid[0][0].next(t).who());
	}

	// Test getProfit
	@Test
	public void test3() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Empty(t, 0, 0);
		t.grid[0][1] = new Empty(t, 0, 1);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Casual(t, 1, 1);
		assertEquals(1, ISPBusiness.getProfit(t));
		System.out.println(t);
	}
}