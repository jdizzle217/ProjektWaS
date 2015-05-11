package ch.bfh.wstat.project;

/**
 * Iterated Prisoners Dilemma Game - main class
 */
public class Project {

	public static void main(String[] args) {

		int Strategy1 = 2; //default strategy RANDOM
		int Strategy2 = 1; //default strategy RANDOM
		int nbGames = 100000; // default number of turns

		if (args.length > 0)
			try {
				Strategy1 = Integer.parseInt(args[0]);
				Strategy2 = Integer.parseInt(args[1]);
				nbGames = Integer.parseInt(args[2]);
			} catch (NumberFormatException e) {
				System.err.println("The 3 arguments must be integers - strategy strategy nbgames");
				System.exit(1);
			}

		//create the two players
		Player Player1 = new Player(Strategy1, nbGames);
		Player Player2 = new Player(Strategy2, nbGames);

		//create game
		Game currentGame = new Game(Player1, Player2);

		// play game
		for (int i = 0; i < nbGames; i++)
			currentGame.play(i);

		System.exit(1);
	}
}
