package edu.iastate.cs228.hw1;

/**
 * 
 * @author Alec Frey
 * 
 * Abstract TownCell class that is extending in all the cell subclasses.
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;

	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;

	public static final int NUM_CELL_TYPE = 5;

	// Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	/**
	 * TownCell constructor that is used to assign plain, row, and col variables
	 * from cell classes.
	 * 
	 * @param p
	 * @param r
	 * @param c
	 */
	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}

	/**
	 * Checks all neighborhood cell types in the neighborhood. Refer to homework pdf
	 * for neighbor definitions (all adjacent neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 * 
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0;
		nCensus[EMPTY] = 0;
		nCensus[CASUAL] = 0;
		nCensus[OUTAGE] = 0;
		nCensus[STREAMER] = 0;

		int numCells = plain.getLength() * plain.getWidth();

		// Check if current cell is one of the corners & that grid bigger than a 2 x 2
		if ((isCornerCell(plain.grid[row][col]) == 1) && (numCells >= 6)) {
			topLeftNeighbors(nCensus);
		} else if ((isCornerCell(plain.grid[row][col]) == 2) && (numCells >= 6)) {
			topRightNeighbors(nCensus);
		} else if ((isCornerCell(plain.grid[row][col]) == 3) && (numCells >= 6)) {
			bottomLeftNeighbors(nCensus);
		} else if ((isCornerCell(plain.grid[row][col]) == 4) && (numCells >= 6)) {
			bottomRightNeighbors(nCensus);

			// Check if current cell is on the edge & that grid bigger than a 2 x 2
		} else if ((isEdgeCell(plain.grid[row][col])) == 1 && (numCells >= 6)) {
			topNeighbors(nCensus);
		} else if ((isEdgeCell(plain.grid[row][col])) == 2 && (numCells >= 6)) {
			leftNeighbors(nCensus);
		} else if ((isEdgeCell(plain.grid[row][col])) == 3 && (numCells >= 6)) {
			bottomNeighbors(nCensus);
		} else if ((isEdgeCell(plain.grid[row][col])) == 4 && (numCells >= 6)) {
			rightNeighbors(nCensus);

			// Cell isn't on edge or corner or is a 2 x 2
		} else {
			for (int i = 0; i < plain.getLength(); i++) {
				for (int j = 0; j < plain.getWidth(); j++) {

					if (plain.grid[i][j].who() == State.RESELLER) {
						nCensus[RESELLER]++;

					} else if (plain.grid[i][j].who() == State.EMPTY) {
						nCensus[EMPTY]++;

					} else if (plain.grid[i][j].who() == State.CASUAL) {
						nCensus[CASUAL]++;

					} else if (plain.grid[i][j].who() == State.OUTAGE) {
						nCensus[OUTAGE]++;

					} else if (plain.grid[i][j].who() == State.STREAMER) {
						nCensus[STREAMER]++;
					}
				}
			}
		}
	}

	/**
	 * Tests to see if specific cell is on one of the 4 corners of the grid.
	 * 
	 * Top-left returns 1 Top-right returns 2 Bottom-left returns 3 Bottom-right
	 * returns 4
	 * 
	 * @param tc
	 * @return which corner cell is (or 0 if not a corner)
	 */
	private int isCornerCell(TownCell tc) {
		if (tc.row == 0 && tc.col == 0) {
			return 1;

		} else if ((tc.row == 0) && (tc.col == tc.plain.getWidth() - 1)) {
			return 2;

		} else if ((tc.row == tc.plain.getLength() - 1) && (tc.col == 0)) {
			return 3;

		} else if ((tc.row == tc.plain.getLength() - 1) && (tc.col == tc.plain.getWidth() - 1)) {
			return 4;

		}
		return 0;
	}

	/**
	 * Tests to see if specific cell is on the edge of the grid.
	 * 
	 * Top returns 1 Left returns 2 Bottom returns 3 Right returns 4
	 * 
	 * @param tc
	 * @return which edge cell is (or 0 if not on edge)
	 */
	private int isEdgeCell(TownCell tc) {
		if (tc.row == 0) {
			return 1;

		} else if (tc.col == 0) {
			return 2;

		} else if (tc.row == tc.plain.getLength() - 1) {
			return 3;

		} else if (tc.col == tc.plain.getWidth() - 1) {
			return 4;

		}
		return 0;
	}

	/**
	 * Checks cells in neighborhood surrounding the top left corner.
	 * 
	 * Increments nCensus with cells at the surrounding locations.
	 */
	private void topLeftNeighbors(int[] nCensus) {
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= 1; j++) {

				if (plain.grid[i][j].who() == State.RESELLER) {
					nCensus[RESELLER]++;

				} else if (plain.grid[i][j].who() == State.EMPTY) {
					nCensus[EMPTY]++;

				} else if (plain.grid[i][j].who() == State.CASUAL) {
					nCensus[CASUAL]++;

				} else if (plain.grid[i][j].who() == State.OUTAGE) {
					nCensus[OUTAGE]++;

				} else if (plain.grid[i][j].who() == State.STREAMER) {
					nCensus[STREAMER]++;
				}
			}
		}
	}

	/**
	 * Checks cells in neighborhood surrounding the top right corner.
	 * 
	 * Increments nCensus with cells at the surrounding locations.
	 */
	private void topRightNeighbors(int[] nCensus) {
		for (int i = 0; i <= 1; i++) {
			for (int j = col - 1; j <= col; j++) {

				if (plain.grid[i][j].who() == State.RESELLER) {
					nCensus[RESELLER]++;

				} else if (plain.grid[i][j].who() == State.EMPTY) {
					nCensus[EMPTY]++;

				} else if (plain.grid[i][j].who() == State.CASUAL) {
					nCensus[CASUAL]++;

				} else if (plain.grid[i][j].who() == State.OUTAGE) {
					nCensus[OUTAGE]++;

				} else if (plain.grid[i][j].who() == State.STREAMER) {
					nCensus[STREAMER]++;
				}
			}
		}
	}

	/**
	 * Checks cells in neighborhood surrounding the bottom left corner.
	 * 
	 * Increments nCensus with cells at the surrounding locations.
	 */
	private void bottomLeftNeighbors(int[] nCensus) {
		for (int i = row - 1; i <= row; i++) {
			for (int j = 0; j <= 1; j++) {

				if (plain.grid[i][j].who() == State.RESELLER) {
					nCensus[RESELLER]++;

				} else if (plain.grid[i][j].who() == State.EMPTY) {
					nCensus[EMPTY]++;

				} else if (plain.grid[i][j].who() == State.CASUAL) {
					nCensus[CASUAL]++;

				} else if (plain.grid[i][j].who() == State.OUTAGE) {
					nCensus[OUTAGE]++;

				} else if (plain.grid[i][j].who() == State.STREAMER) {
					nCensus[STREAMER]++;
				}
			}
		}
	}

	/**
	 * Checks cells in neighborhood surrounding the bottom right corner.
	 * 
	 * Increments nCensus with cells at the surrounding locations.
	 */
	private void bottomRightNeighbors(int[] nCensus) {
		for (int i = row - 1; i <= row; i++) {
			for (int j = col - 1; j <= col; j++) {

				if (plain.grid[i][j].who() == State.RESELLER) {
					nCensus[RESELLER]++;

				} else if (plain.grid[i][j].who() == State.EMPTY) {
					nCensus[EMPTY]++;

				} else if (plain.grid[i][j].who() == State.CASUAL) {
					nCensus[CASUAL]++;

				} else if (plain.grid[i][j].who() == State.OUTAGE) {
					nCensus[OUTAGE]++;

				} else if (plain.grid[i][j].who() == State.STREAMER) {
					nCensus[STREAMER]++;
				}
			}
		}
	}

	/**
	 * Checks cells in neighborhood surrounding the top edge.
	 * 
	 * Increments nCensus with cells at the surrounding locations.
	 */
	private void topNeighbors(int[] nCensus) {
		for (int i = 0; i <= 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {

				if (plain.grid[i][j].who() == State.RESELLER) {
					nCensus[RESELLER]++;

				} else if (plain.grid[i][j].who() == State.EMPTY) {
					nCensus[EMPTY]++;

				} else if (plain.grid[i][j].who() == State.CASUAL) {
					nCensus[CASUAL]++;

				} else if (plain.grid[i][j].who() == State.OUTAGE) {
					nCensus[OUTAGE]++;

				} else if (plain.grid[i][j].who() == State.STREAMER) {
					nCensus[STREAMER]++;
				}
			}
		}
	}

	/**
	 * Checks cells in neighborhood surrounding the left edge.
	 * 
	 * Increments nCensus with cells at the surrounding locations.
	 */
	private void leftNeighbors(int[] nCensus) {
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = 0; j <= 1; j++) {

				if (plain.grid[i][j].who() == State.RESELLER) {
					nCensus[RESELLER]++;

				} else if (plain.grid[i][j].who() == State.EMPTY) {
					nCensus[EMPTY]++;

				} else if (plain.grid[i][j].who() == State.CASUAL) {
					nCensus[CASUAL]++;

				} else if (plain.grid[i][j].who() == State.OUTAGE) {
					nCensus[OUTAGE]++;

				} else if (plain.grid[i][j].who() == State.STREAMER) {
					nCensus[STREAMER]++;
				}
			}
		}
	}

	/**
	 * Checks cells in neighborhood surrounding the bottom edge.
	 * 
	 * Increments nCensus with cells at the surrounding locations.
	 */
	private void bottomNeighbors(int[] nCensus) {
		for (int i = row - 1; i <= row; i++) {
			for (int j = col - 1; j <= col + 1; j++) {

				if (plain.grid[i][j].who() == State.RESELLER) {
					nCensus[RESELLER]++;

				} else if (plain.grid[i][j].who() == State.EMPTY) {
					nCensus[EMPTY]++;

				} else if (plain.grid[i][j].who() == State.CASUAL) {
					nCensus[CASUAL]++;

				} else if (plain.grid[i][j].who() == State.OUTAGE) {
					nCensus[OUTAGE]++;

				} else if (plain.grid[i][j].who() == State.STREAMER) {
					nCensus[STREAMER]++;
				}
			}
		}
	}

	/**
	 * Checks cells in neighborhood surrounding the right edge.
	 * 
	 * Increments nCensus with cells at the surrounding locations.
	 */
	private void rightNeighbors(int[] nCensus) {
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col; j++) {

				if (plain.grid[i][j].who() == State.RESELLER) {
					nCensus[RESELLER]++;

				} else if (plain.grid[i][j].who() == State.EMPTY) {
					nCensus[EMPTY]++;

				} else if (plain.grid[i][j].who() == State.CASUAL) {
					nCensus[CASUAL]++;

				} else if (plain.grid[i][j].who() == State.OUTAGE) {
					nCensus[OUTAGE]++;

				} else if (plain.grid[i][j].who() == State.STREAMER) {
					nCensus[STREAMER]++;
				}
			}
		}
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}