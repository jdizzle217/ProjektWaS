package ch.bfh.wstat.project.legacy;

/**
 * Iterated Prisoners Dilemma Game - main class
 */
public class Project {

	public static void main(String[] args) {

		int Strategy1 = 1; //default strategy RANDOM
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

//*** START OF MODIFIED CODE FRAGMENT ***
		System.out.println("GAME INFORMATION:"); //print information about the game
		System.out.printf("player 1 follows strategy %d%n", Strategy1);
		System.out.printf("player 2 follows strategy %d%n", Strategy2);
		System.out.printf("game will be played over %,d rounds%n", nbGames);
//*** END OF MODIFIED CODE FRAGMENT ***

		//create the two players
		Player Player1 = new Player(Strategy1, nbGames);
		Player Player2 = new Player(Strategy2, nbGames);

		//create game
		Game currentGame = new Game(Player1, Player2);

		// play game
		for (int i = 0; i < nbGames; i++)
			currentGame.play(i);

//*** START OF MODIFIED CODE FRAGMENT ***
		System.out.println("\nGAME STATISTICS:"); //print information about the game
		System.out.printf("(1, 1): %,7d time(s) - %5.1f%%%n", Statistics.CooperateCooperate, Statistics.getRelativeFrequencyCooperateCooperate() * 100.);
		System.out.printf("(1, 0): %,7d time(s) - %5.1f%%%n", Statistics.CooperateDefect, Statistics.getRelativeFrequencyCooperateDefect() * 100.);
		System.out.printf("(0, 1): %,7d time(s) - %5.1f%%%n", Statistics.DefectCooperate, Statistics.getRelativeFrequencyDefectCooperate() * 100.);
		System.out.printf("(0, 0): %,7d time(s) - %5.1f%%%n", Statistics.DefectDefect, Statistics.getRelativeFrequencyDefectDefect() * 100.);
		System.out.printf("total: %,8d time(s)%n", Statistics.Rounds);

		System.out.println("\nPLAYER STATISTICS:\n          total gain | middle gain"); //print information about the individual players
		Player[] players = {Player1, Player2}; //create an array of the players to display information about
		for (int i = 0; i < players.length; i++)
			System.out.printf("player %d: %,10.2f | %,11.2f%n", i + 1, players[i].Gain, players[i].MiddleGain);
		System.out.printf("total: %,13.2f | %,11.2f%n", Statistics.Gain, Statistics.getMiddleGain());
//*** END OF MODIFIED CODE FRAGMENT ***
	}
}
