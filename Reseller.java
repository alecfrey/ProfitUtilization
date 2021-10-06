package edu.iastate.cs228.hw1;

/**
 * @author Alec Frey
 * 
 * The Reseller class extends TownCell.
 *
 */
public class Reseller extends TownCell {

	/**
	 * Constructs Reseller cell with the use of the super class.
	 * 
	 * @param p
	 * @param r
	 * @param c
	 */
	public Reseller(Town p, int r, int c) {
		super(p, r, c);
	}

	/**
	 * Returns state of cell.
	 */
	@Override
	public State who() {
		return State.RESELLER;
	}

	/**
	 * Returns a new TownCell object with the next cell object.
	 */
	@Override
	public TownCell next(Town tNew) {
		int townCensus[] = new int[NUM_CELL_TYPE];
		census(townCensus);

		// Don't count current cell in census
		townCensus[RESELLER]--;

		if (townCensus[CASUAL] < 4) {
			return new Empty(tNew, row, col);

		} else if (townCensus[EMPTY] > 2) {
			return new Empty(tNew, row, col);

		} else if (townCensus[CASUAL] > 4) {
			return new Streamer(tNew, row, col);

		} else {
			return new Reseller(tNew, row, col);
		}

	}

}
