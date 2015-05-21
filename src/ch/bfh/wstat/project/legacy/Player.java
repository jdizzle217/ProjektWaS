package ch.bfh.wstat.project.legacy;

/**
 * Player : Implements for each player - Strategy, Movement History, ...
 */
public class Player {

//String NameStrategy;
	int PlayerCurrentMove = -1;
	int PlayerStrategy = -1;
	int[] MoveHistory;
	float[] GainHistory;

//*** START OF MODIFIED CODE FRAGMENT ***
	float Gain = 0.f, MiddleGain = 0.f;
//*** END OF MODIFIED CODE FRAGMENT ***

	//constructor
	Player(int PlayerStrategy, int nbGames) {

		this.PlayerStrategy = PlayerStrategy;
		MoveHistory = new int[nbGames];
		GainHistory = new float[nbGames];
	}

	// update data history of the player (end of each round)
	public void update(float Gain, int nGame) {

		MoveHistory[nGame] = PlayerCurrentMove;
		GainHistory[nGame] = Gain;

//*** START OF MODIFIED CODE FRAGMENT ***
		this.Gain += Gain;
		MiddleGain = this.Gain / (nGame + 1);
//*** END OF MODIFIED CODE FRAGMENT ***
	}
}
