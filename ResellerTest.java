package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Alec Frey
 * 
 * Public ResellerTest class with JUnit test cases.
 * 
 */
public class ResellerTest {

	/**
	 * Tests to create Empty cell since there is less than 4 Casual cells in
	 * neighborhood.
	 */
	@Test
	public void test1() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Reseller(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Empty(t, 1, 0);
		t.grid[1][1] = new Outage(t, 1, 1);
		assertEquals(State.EMPTY, t.grid[0][0].next(t).who());
	}

	/**
	 * Tests last condition in Reseller next method.
	 */
	@Test
	public void test2() {
		Town t = new Town(2, 2);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Casual(t, 1, 1);
		assertEquals(State.RESELLER, t.grid[0][0].next(t).who());
	}

	/**
	 * Tests to create Empty cell since there is more than 2 current Empty cells in
	 * neighborhood.
	 */
	@Test
	public void test4() {
		Town t = new Town(3, 3);
		t.grid[0][0] = new Reseller(t, 0, 0);
		t.grid[0][1] = new Empty(t, 0, 1);
		t.grid[0][2] = new Casual(t, 0, 2);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Casual(t, 1, 1);
		t.grid[1][2] = new Casual(t, 1, 2);
		t.grid[2][0] = new Casual(t, 2, 0);
		t.grid[2][1] = new Empty(t, 2, 1);
		t.grid[2][2] = new Casual(t, 2, 2);
		assertEquals(State.EMPTY, t.grid[0][0].next(t).who());
	}

	/**
	 * Tests to create Streamer cell since there is over 4 Casual cells in
	 * neighborhood.
	 */
	@Test
	public void test5() {
		Town t = new Town(3, 3);
		t.grid[0][0] = new Casual(t, 0, 0);
		t.grid[0][1] = new Casual(t, 0, 1);
		t.grid[0][2] = new Casual(t, 0, 2);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Reseller(t, 1, 1);
		t.grid[1][2] = new Casual(t, 1, 2);
		t.grid[2][0] = new Casual(t, 2, 0);
		t.grid[2][1] = new Empty(t, 2, 1);
		t.grid[2][2] = new Casual(t, 2, 2);
		assertEquals(State.STREAMER, t.grid[1][1].next(t).who());
	}

	// Test getProfit
	@Test
	public void test6() {
		Town t = new Town(3, 3);
		t.grid[0][0] = new Reseller(t, 0, 0);
		t.grid[0][1] = new Empty(t, 0, 1);
		t.grid[0][2] = new Casual(t, 0, 2);
		t.grid[1][0] = new Casual(t, 1, 0);
		t.grid[1][1] = new Casual(t, 1, 1);
		t.grid[1][2] = new Casual(t, 1, 2);
		t.grid[2][0] = new Casual(t, 2, 0);
		t.grid[2][1] = new Empty(t, 2, 1);
		t.grid[2][2] = new Casual(t, 2, 2);
		assertEquals(6, ISPBusiness.getProfit(t));
		System.out.println(t);
	}
}
