package edu.iastate.cs228.hw1;

/**
 * @author Alec Frey
 * 
 * The Streamer class extends TownCell.
 *
 */
public class Streamer extends TownCell {

	/**
	 * Constructs Streamer cell with the use of the super class.
	 * 
	 * @param p
	 * @param r
	 * @param c
	 */
	public Streamer(Town p, int r, int c) {
		super(p, r, c);
	}

	/**
	 * Returns state of cell.
	 */
	@Override
	public State who() {
		return State.STREAMER;
	}

	/**
	 * Returns a new TownCell object with the next cell object.
	 */
	@Override
	public TownCell next(Town tNew) {
		int townCensus[] = new int[NUM_CELL_TYPE];
		census(townCensus);

		// Don't count current cell in census
		townCensus[STREAMER]--;

		if (townCensus[OUTAGE] + townCensus[EMPTY] <= 1) {
			return new Reseller(tNew, row, col);

		} else if (townCensus[RESELLER] > 0) {
			return new Outage(tNew, row, col);

		} else if (townCensus[OUTAGE] > 0) {
			return new Empty(tNew, row, col);

		} else if (townCensus[CASUAL] > 4) {
			return new Streamer(tNew, row, col);

		} else {
			return new Streamer(tNew, row, col);
		}
	}
}
