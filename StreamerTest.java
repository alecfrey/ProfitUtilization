package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Alec Frey
 * 
 * Public StreamerTest class with JUnit test cases.
 *
 */
public class StreamerTest {

	/**
	 * Tests to create Reseller cell since there is less than or equal to 1 Outage &
	 * Empty cell currently in neighborhood.
	 */
	@Test
	public void test1() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Streamer(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Outage(t, 1, 1);
		assertEquals(State.RESELLER, t.grid[0][0].next(t).who());
	}

	/**
	 * Tests to create an Outage cell since there is a Reseller in neighborhood.
	 */
	@Test
	public void test2() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Streamer(t, 0, 0);
		t.grid[0][1] = new Reseller(t, 0, 1);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Outage(t, 1, 1);
		assertEquals(State.OUTAGE, t.grid[0][0].next(t).who());
	}

	/**
	 * Tests to create an Empty cell since there is an Outage in neighborhood.
	 */
	@Test
	public void test3() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Streamer(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Outage(t, 1, 1);
		assertEquals(State.EMPTY, t.grid[0][0].next(t).who());

	}

	/**
	 * Tests to create a Streamer cell since there is over 4 Casual cells in
	 * neighborhood.
	 */
	@Test
	public void test4() {
		Town t = new Town(3, 3);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Empty(t, 0, 1);
		t.grid[0][2] = new Casual(t, 0, 2);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Streamer(t, 1, 1);
		t.grid[1][2] = new Casual(t, 1, 2);
		t.grid[2][0] = new Casual(t, 2, 0);
		t.grid[2][1] = new Empty(t, 2, 1);
		t.grid[2][2] = new Casual(t, 2, 2);
		assertEquals(State.STREAMER, t.grid[1][1].next(t).who());
		System.out.println(t);
	}

	// Test getProfit
	@Test
	public void test5() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Streamer(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Casual(t, 1, 1);
		assertEquals(2, ISPBusiness.getProfit(t));
		System.out.println(t);
	}
}
