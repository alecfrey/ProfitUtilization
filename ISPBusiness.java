package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Alec Frey
 *
 * The ISPBusiness class performs simulation over a grid plain with
 * cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {

	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * 
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());

		for (int i = 0; i < tOld.getLength(); i++) {
			for (int j = 0; j < tOld.getWidth(); j++) {
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
			}
		}
		return tNew;
	}

	/**
	 * Returns the profit for the current state in the town grid.
	 * 
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		int profit = 0;

		for (int i = 0; i < town.getLength(); i++) {
			for (int j = 0; j < town.getWidth(); j++) {
				if (town.grid[i][j].who() == State.CASUAL) {
					profit++;
				}
			}
		}
		return profit;
	}

	/**
	 * Main method. Interact with the user and ask if user wants to specify elements
	 * of grid via an input file (option: 1) or wants to generate it randomly
	 * (option: 2).
	 * 
	 * Depending on the user choice, create the Town object using respective
	 * constructor and if user choice is to populate it randomly, then populate the
	 * grid here.
	 * 
	 * Finally: For 12 billing cycle calculate the profit and update town object
	 * (for each cycle). Print the final profit in terms of %. You should print the
	 * profit percentage with two digits after the decimal point: Example if profit
	 * is 35.5600004, your output should be:
	 *
	 * 35.56%
	 * 
	 * Note that this method does not throw any exception, so you need to handle all
	 * the exceptions in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");

		int input = s.nextInt();
		int profit = 0;

		if (input == 1) {
			Scanner s2 = new Scanner(System.in);
			System.out.println("Please enter the file path:");
			String filePath = s2.nextLine();

			try {
				Town t = new Town(filePath);

				// -------Commented out code below prints montly grid and profit-----
				// System.out.println("Start:");
				// System.out.print(t);
				// System.out.println("Profit: " + getProfit(t));
				// System.out.println();
				profit += getProfit(t);

				for (int i = 1; i < 12; i++) {
					// System.out.printf("After itr: %d\n", i);
					// System.out.print(t);
					// System.out.println("Profit: " + getProfit(t));
					// System.out.println();
					t = updatePlain(t);
					profit += getProfit(t);
				}

				int potentialProfit = (t.getLength() * t.getWidth()) * 12;
				double percentage = profit * 100 / potentialProfit;

				System.out.printf("%.2f", percentage);
				System.out.println("%");

			} catch (FileNotFoundException e) {
				System.out.println("Error: File Not Found.");

			} finally {
				s2.close();
			}

		} else if (input == 2) {
			try {
				System.out.println("Provide rows, cols and seed integer separated by space:");
				int length = s.nextInt();
				int width = s.nextInt();
				int seed = s.nextInt();
				
				Town t = new Town(length, width);
				t.randomInit(seed);

				// -------Commented out code below prints montly grid and profit-----
				// System.out.println("Start:");
				// System.out.print(t);
				// System.out.println("Profit: " + getProfit(t));
				// System.out.println();
				profit += getProfit(t);

				for (int i = 1; i < 12; i++) {
					// System.out.printf("After itr: %d\n", i);
					// System.out.print(t);
					// System.out.println("Profit: " + getProfit(t));
					// System.out.println();
					t = updatePlain(t);
					profit += getProfit(t);
				}

				int potentialProfit = (t.getLength() * t.getWidth()) * 12;
				double percentage = profit * 100 / potentialProfit;

				System.out.printf("%.2f", percentage);
				System.out.println("%");

			} catch (InputMismatchException e) {
				System.out.println("Unexpected input.");
				
			} finally {
				s.close();
			}
		} else {
			System.out.println("Invalid option selection.");
		}
	}
}
