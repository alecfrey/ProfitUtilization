package edu.iastate.cs228.hw1;

/**
 * @author Alec Frey
 * 
 * The Casual class extends TownCell.
 *
 */
public class Outage extends TownCell {

	/**
	 * Constructs Casual cell with the use of the super class.
	 * 
	 * @param p
	 * @param r
	 * @param c
	 */
	public Outage(Town p, int r, int c) {
		super(p, r, c);
	}

	/**
	 * Returns state of cell.
	 */
	@Override
	public State who() {
		return State.OUTAGE;
	}

	/**
	 * Returns a new TownCell object with the next cell object.
	 */
	@Override
	public TownCell next(Town tNew) {
		int townCensus[] = new int[NUM_CELL_TYPE];
		census(townCensus);

		// Don't count current cell in census
		townCensus[OUTAGE]--;

		return new Empty(tNew, row, col);
	}
}
