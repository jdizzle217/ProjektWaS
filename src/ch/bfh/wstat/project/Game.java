package ch.bfh.wstat.project;

/**
 * Game : Implement iterated prisoner's dilemma game
 */
public class Game {

	//possible gain
	static float GAIN_COL = 3; // gain for mutual collaboration
	static float GAIN_TEM = 5; // gain for deception when other players collaborates
	static float GAIN_LOS = 0; // gain for collaboration when other players deceives
	static float GAIN_DEF = 1; // gain for mutual deception

	//two players
	Player Player1, Player2;

	//constructor
	Game(Player Player1, Player Player2) {
		this.Player1 = Player1;
		this.Player2 = Player2;
	}

	//round of game
	public void play(int nGame) {

		//get player moves
		Player1.PlayerCurrentMove = Strategy.nextMove(Player1, Player2, nGame);
		Player2.PlayerCurrentMove = Strategy.nextMove(Player2, Player1, nGame);

		//get result
		if (Player1.PlayerCurrentMove == Strategy.COOPERATE)
			if (Player2.PlayerCurrentMove == Strategy.COOPERATE) { 	// mutual collaboration (R,R)
				Statistics.CooperateCooperate++;
				Player1.update(GAIN_COL, nGame);
				Player2.update(GAIN_COL, nGame);
				//System.out.printf("Mutual collaboration\n");
			} else { 													// player1 loses (S,T)
				Statistics.CooperateDeceive++;
				Player1.update(GAIN_LOS, nGame);
				Player2.update(GAIN_TEM, nGame);
				//System.out.printf("Player 1 loses\n");
			}
		else if (Player2.PlayerCurrentMove == Strategy.COOPERATE) { 	// player2 loses (T,S)
			Statistics.DeceiveCooperate++;
			Player1.update(GAIN_TEM, nGame);
			Player2.update(GAIN_LOS, nGame);
			//System.out.printf("Player 2 loses\n");
		} else { 												// mutual deception (P,P)
			Statistics.DeceiveDeceive++;
			Player1.update(GAIN_DEF, nGame);
			Player2.update(GAIN_DEF, nGame);
			//System.out.printf("Mutual deception\n");
		}
	}
}
