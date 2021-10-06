package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Alec Frey
 * 
 * Public Town class used to create and initialize a town either via a
 * file or randomly.
 *
 */
public class Town {

	private int length; // Row and col (first and second indices)
	private int width;
	public TownCell[][] grid;

	/**
	 * Constructor to be used when user wants to generate grid randomly, with the
	 * given seed. This constructor does not populate each cell of the grid (but
	 * should assign a 2D array to it).
	 * 
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of
	 * catching it. Ensure that you close any resources (like file or scanner) which
	 * is opened in this function.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		File f = new File(inputFileName);
		Scanner s = new Scanner(f);

		int length = s.nextInt();
		int width = s.nextInt();

		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
		s.nextLine();

		for (int i = 0; i < length; i++) {
			String[] currentRow = s.nextLine().split(" ");
			for (int j = 0; j < width; j++) {

				if (currentRow[j].equals("C")) {
					grid[i][j] = new Casual(this, i, j);

				} else if (currentRow[j].equals("R")) {
					grid[i][j] = new Reseller(this, i, j);

				} else if (currentRow[j].equals("S")) {
					grid[i][j] = new Streamer(this, i, j);

				} else if (currentRow[j].equals("O")) {
					grid[i][j] = new Outage(this, i, j);

				} else if (currentRow[j].equals("E")) {
					grid[i][j] = new Empty(this, i, j);

				}
			}
		}
		s.close();
	}

	/**
	 * Returns width of the grid.
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns length of the grid.
	 * 
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following
	 * class object: Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				int usage = rand.nextInt(5);

				if (usage == TownCell.RESELLER) {
					TownCell tc = new Reseller(this, i, j);
					grid[i][j] = tc;

				} else if (usage == TownCell.EMPTY) {
					TownCell tc = new Empty(this, i, j);
					grid[i][j] = tc;

				} else if (usage == TownCell.CASUAL) {
					TownCell tc = new Casual(this, i, j);
					grid[i][j] = tc;

				} else if (usage == TownCell.OUTAGE) {
					TownCell tc = new Outage(this, i, j);
					grid[i][j] = tc;

				} else if (usage == TownCell.STREAMER) {
					TownCell tc = new Streamer(this, i, j);
					grid[i][j] = tc;
				}
			}
		}
	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell
	 * type. Each letter should be separated either by a single space or a tab. And
	 * each row should be in a new line. There should not be any extra line between
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";

		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {

				if (grid[i][j].who() == State.RESELLER) {
					s += "R ";

				} else if (grid[i][j].who() == State.EMPTY) {
					s += "E ";

				} else if (grid[i][j].who() == State.CASUAL) {
					s += "C ";

				} else if (grid[i][j].who() == State.OUTAGE) {
					s += "O ";

				} else if (grid[i][j].who() == State.STREAMER) {
					s += "S ";
				}
			}
			s += "\n";
		}
		return s;
	}
}
