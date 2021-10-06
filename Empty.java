package edu.iastate.cs228.hw1;

/**
 * @author Alec Frey
 * 
 * The Empty class extends TownCell.
 *
 */
public class Empty extends TownCell {

	/**
	 * Constructs Empty cell with the use of the super class.
	 * 
	 * @param p
	 * @param r
	 * @param c
	 */
	public Empty(Town p, int r, int c) {
		super(p, r, c);
	}

	/**
	 * Returns state of cell.
	 */
	@Override
	public State who() {
		return State.EMPTY;
	}

	/**
	 * Returns a new TownCell object with the next cell object.
	 */
	@Override
	public TownCell next(Town tNew) {
		int townCensus[] = new int[NUM_CELL_TYPE];
		census(townCensus);

		// Don't count current cell in census
		townCensus[EMPTY]--;

		if (townCensus[OUTAGE] + townCensus[EMPTY] <= 1) {
			return new Reseller(tNew, row, col);

		} else {
			return new Casual(tNew, row, col);
		}
	}
}
