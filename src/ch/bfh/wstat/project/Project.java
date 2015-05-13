package ch.bfh.wstat.project;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class of the project 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 &amp; weidj1
 */
public class Project {

	/**
	 * Main method &amp; entry point of the application.
	 *
	 * @param args arguments from the command line ({@code <strategy of player 1>} {@code <strategy of player 2>} {@code <number of rounds>})
	 */
	public static void main(String[] args) {

		Strategy[] strategies = new Strategy[2];
		int rounds = -1;

		if (args.length == 3) //if exactly three arguments have been passed, try to create game with these settings
			try {
				strategies[0] = recogniseStrategy(args[0]); //try to recognise strategy ordinals / names
				strategies[1] = recogniseStrategy(args[1]);
				rounds = Integer.parseInt(args[2]); //parse the number of rounds

			} catch (NumberFormatException | NoSuchElementException ex) {
				//throw new IllegalArgumentException("The 3 arguments must be: <strategy of player 1> <strategy of player 2> <number of rounds>.");
			}

		if (rounds <= 0) //if settings have not been passed from the command line, allow the user to interactively specify them
			try (Scanner in = new Scanner(System.in)) { //create a scanner to read from the console
				System.out.println("Please choose the player's strategies.\nYou have the following options:");
				for (Strategy stg: Strategy.values()) //display the strategy ordinals & numbers
					System.out.printf(">%d: %s%n", stg.ordinal(), stg.name());
				System.out.println();

				for (int i = 0; i < strategies.length; i++) { //read strategies for players 1 & 2
					System.out.printf("Please choose the strategy of player %d: ", i + 1); //show the user a hint what to enter
					while (true)
						try {
							strategies[i] = recogniseStrategy(in.nextLine()); //try to recognise the strategy
							break; //if recognition succeeded, quit loop and proceed

						} catch (ArrayIndexOutOfBoundsException | NoSuchElementException ex) { //if the user entered an invalid string
							System.out.print("Please enter a valid strategy ordinal or name: "); //show a message and try again
						}
				}

				System.out.print("Please enter the number of rounds to play: "); //show the user a hint what to enter
				while (true) {
					try {
						rounds = Integer.parseInt(in.nextLine()); //try to parse the string to a number
						if (rounds > 0) //if the user entered a positive number of rounds
							break; //quit loop and proceed

					} catch (NumberFormatException ex) { //if the user did not enter a number, proceed
					}

					System.out.print("Please enter a positive number: "); //if the user entered an invalid string, show a message and try again
				}
			}

		new Project().playGame(strategies[0], strategies[1], rounds); //create an object and play the game
	}

	private static Strategy recogniseStrategy(String name) {

		try {
			return Strategy.values()[Integer.parseInt(name)]; //try to parse the string to a number and retreive the strategy

		} catch (NumberFormatException ex) { //if the user did not specify a number, try to find a strategy with the specified name
			return Arrays.stream(Strategy.values()).filter(s -> s.name().equalsIgnoreCase(name)).findAny().get();
		}
	}

	/**
	 * Play a specific game.
	 *
	 * @param strategy1 strategy of player 1
	 * @param strategy2 strategy of player 2
	 * @param rounds    number of rounds
	 */
	public void playGame(Strategy strategy1, Strategy strategy2, int rounds) {

		System.out.println("\nGAME INFORMATION:"); //print information about the game
		System.out.printf("player 1 follows strategy %d (%s)%n", strategy1.ordinal(), strategy1.name());
		System.out.printf("player 2 follows strategy %d (%s)%n", strategy2.ordinal(), strategy2.name());
		System.out.printf("game will be played over %,d rounds%n", rounds);

		Player player1 = new Player(strategy1); //create the two players
		Player player2 = new Player(strategy2);

		Game game = new Game(player1, player2, rounds); //create the game
		game.playRounds(); //play all rounds

		Statistics statistics = game.getStatistics(); //get the statistics

		System.out.println("\nGAME STATISTICS:"); //print information about the game
		System.out.printf("(1, 1): %,7d time(s) - %5.1f%%%n", statistics.getEventFrequency(Move.COOPERATE, Move.COOPERATE), statistics.getEventRelativeFrequency(Move.COOPERATE, Move.COOPERATE) * 100.);
		System.out.printf("(1, 0): %,7d time(s) - %5.1f%%%n", statistics.getEventFrequency(Move.DECEIVE, Move.COOPERATE), statistics.getEventRelativeFrequency(Move.DECEIVE, Move.COOPERATE) * 100.);
		System.out.printf("(0, 1): %,7d time(s) - %5.1f%%%n", statistics.getEventFrequency(Move.COOPERATE, Move.DECEIVE), statistics.getEventRelativeFrequency(Move.COOPERATE, Move.DECEIVE) * 100.);
		System.out.printf("(0, 0): %,7d time(s) - %5.1f%%%n", statistics.getEventFrequency(Move.DECEIVE, Move.DECEIVE), statistics.getEventRelativeFrequency(Move.DECEIVE, Move.DECEIVE) * 100.);
		System.out.printf("total: %,8d time(s)%n", rounds);

		System.out.println("\nPLAYER STATISTICS:\n          total gain | middle gain"); //print information about the individual players
		System.out.printf("player 1: %,10.2f | %,11.2f%n", player1.getTotalGain(), player1.getMiddleGain());
		System.out.printf("player 2: %,10.2f | %,11.2f%n", player2.getTotalGain(), player2.getMiddleGain());
		System.out.printf("total: %,13.2f%n", player1.getTotalGain().add(player2.getTotalGain()));
	}
}
