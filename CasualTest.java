package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Alec Frey
 * 
 * Public CasualTest class with JUnit test cases.
 *
 */
public class CasualTest {

	/**
	 * Tests for last condition in census.
	 */
	@Test
	public void test1() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Outage(t, 1, 1);
		assertEquals(State.CASUAL, t.grid[0][0].next(t).who());
	}

	/**
	 * Tests to create Streamer cell since there already is one in neighborhood.
	 */
	@Test
	public void test2() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Streamer(t, 0, 1);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Outage(t, 1, 1);
		assertEquals(State.STREAMER, t.grid[0][0].next(t).who());
	}

	/**
	 * Tests to create Outage cell since there is a Reseller in neighborhood.
	 */
	@Test
	public void test3() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Outage(t, 0, 1);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Reseller(t, 1, 1);
		assertEquals(State.OUTAGE, t.grid[0][0].next(t).who());
	}

	/**
	 * Tests to create a Reseller cell since there is less than or equal to 1 Outage
	 * & Empty cell.
	 */
	@Test
	public void test4() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Casual(t, 1, 1);
		assertEquals(State.RESELLER, t.grid[0][0].next(t).who());
	}

	/**
	 * Tests to create a Streamer cell since there are over 4 casual cells in
	 * neighborhood.
	 */
	@Test
	public void test5() {
		Town t = new Town(3, 3);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Outage(t, 0, 1);
		t.grid[0][2] = new Casual(t, 0, 2);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Casual(t, 1, 1);
		t.grid[1][2] = new Casual(t, 1, 2);
		t.grid[2][0] = new Casual(t, 2, 0);
		t.grid[2][1] = new Empty(t, 2, 1);
		t.grid[2][2] = new Casual(t, 2, 2);
		assertEquals(State.STREAMER, t.grid[1][1].next(t).who());
	}

	/**
	 * Tests to create a Casual cell since there are over 4 casual cells in
	 * neighborhood.
	 */
	@Test
	public void test6() {
		Town t = new Town(4, 4);
		t.grid[0][0] = new Reseller(t, 0, 0);
		t.grid[0][1] = new Outage(t, 0, 1);
		t.grid[0][2] = new Empty(t, 0, 2);
		t.grid[0][3] = new Casual(t, 0, 3);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Casual(t, 1, 1);
		t.grid[1][2] = new Casual(t, 1, 2);
		t.grid[1][3] = new Casual(t, 1, 3);
		t.grid[2][0] = new Casual(t, 2, 0);
		t.grid[2][1] = new Casual(t, 2, 1);
		t.grid[2][2] = new Casual(t, 2, 2);
		t.grid[2][3] = new Outage(t, 2, 3);
		t.grid[3][0] = new Casual(t, 3, 0);
		t.grid[3][1] = new Casual(t, 3, 1);
		t.grid[3][2] = new Casual(t, 3, 2);
		t.grid[3][3] = new Casual(t, 3, 3);
		assertEquals(State.CASUAL, t.grid[1][3].next(t).who());
		System.out.println(t);
	}

	/**
	 * Tests to create a Outage cell since there is a Reseller in the neighborhood.
	 */
	@Test
	public void test7() {
		Town t = new Town(4, 4);
		t.grid[0][0] = new Reseller(t, 0, 0);
		t.grid[0][1] = new Outage(t, 0, 1);
		t.grid[0][2] = new Empty(t, 0, 2);
		t.grid[0][3] = new Reseller(t, 0, 3);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Reseller(t, 1, 1);
		t.grid[1][2] = new Reseller(t, 1, 2);
		t.grid[1][3] = new Casual(t, 1, 3);
		t.grid[2][0] = new Empty(t, 2, 0);
		t.grid[2][1] = new Empty(t, 2, 1);
		t.grid[2][2] = new Casual(t, 2, 2);
		t.grid[2][3] = new Outage(t, 2, 3);
		t.grid[3][0] = new Casual(t, 3, 0);
		t.grid[3][1] = new Casual(t, 3, 1);
		t.grid[3][2] = new Casual(t, 3, 2);
		t.grid[3][3] = new Casual(t, 3, 3);
		assertEquals(State.OUTAGE, t.grid[1][3].next(t).who());
		System.out.println(t);
	}

	// Test getProfit
	@Test
	public void test8() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Casual(t, 1, 1);
		assertEquals(4, ISPBusiness.getProfit(t));
		System.out.println(t);
	}
}