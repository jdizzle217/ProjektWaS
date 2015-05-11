
/***
 * Player : Implements for each player - Strategy, Movement History, ... 
 * 
 */

public class Player {
	
	String NameStrategy;
	int PlayerCurrentMove = -1;
	int PlayerStrategy = -1;
	int[] MoveHistory;
	float[] GainHistory;
	float MiddleGain;
	
	//constructor
	Player(int PlayerStrategy, int nbGames){
		this.PlayerStrategy = PlayerStrategy;
		MoveHistory = new int[nbGames];
		GainHistory = new float[nbGames];
	}

	// update data history of the player (end of each round)
	public void update(float Gain, int nGame){
		GainHistory[nGame]=Gain;
		MoveHistory[nGame]=PlayerCurrentMove;		
	}

}
