package ch.bfh.wstat.project;

/**
 * Iterated Prisoners Dilemma Game - main class
 */
public class Project {

	public static void main(String[] args) {

		int Strategy1 = 2; //default strategy RANDOM
		int Strategy2 = 1; //default strategy RANDOM
		int nbGames = 10; // default number of turns

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

		System.out.printf("(1,1): %6d time(s)%n", Statistics.CooperateCooperate);
		System.out.printf("(1,0): %6d time(s)%n", Statistics.DeceiveCooperate);
		System.out.printf("(0,1): %6d time(s)%n", Statistics.CooperateDeceive);
		System.out.printf("(0,0): %6d time(s)%n", Statistics.DeceiveDeceive);
		System.out.printf("total: %6d time(s)%n", nbGames);

		System.exit(1);
	}
}
