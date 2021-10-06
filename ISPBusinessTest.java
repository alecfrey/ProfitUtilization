package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Alec Frey
 * 
 * Public ISPBusinessTest class with JUnit test cases.
 * 
 *
 */
public class ISPBusinessTest {

	/**
	 * Tests to get profit of current cell by adding up amount of Casual cells.
	 */
	@Test
	public void testProfit() {
		Town town = new Town(2, 2);

		town.grid[0][0] = new Casual(town, 0, 0);
		town.grid[0][1] = new Empty(town, 0, 1);
		town.grid[1][0] = new Casual(town, 1, 0);
		town.grid[1][1] = new Outage(town, 1, 1);

		assertEquals(2, ISPBusiness.getProfit(town));
	}

	/**
	 * Tests updating plain with new cells.
	 */
	@Test
	public void testPlainUpdate() {
		Town town = new Town(2, 2);

		town.grid[0][0] = new Casual(town, 0, 0);
		town.grid[0][1] = new Empty(town, 0, 1);
		town.grid[1][0] = new Casual(town, 1, 0);
		town.grid[1][1] = new Outage(town, 1, 1);

		Town town2 = new Town(2, 2);
		town2.grid[0][0] = new Casual(town2, 0, 0);
		town2.grid[0][1] = new Reseller(town2, 0, 1);
		town2.grid[1][0] = new Casual(town2, 1, 0);
		town2.grid[1][1] = new Empty(town2, 1, 1);

		assertEquals(State.EMPTY, ISPBusiness.updatePlain(town).grid[1][1].who());
		System.out.println(town2.grid[1][1].who().toString().charAt(0));
	}
}
