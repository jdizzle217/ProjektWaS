package ch.bfh.wstat.project.legacy;

/*
 * Strategy: implement different strategies
 *
 * 1 - RAND     = collaborate with probability 0.5
 * 2 - PROB     = collaborate with probability p
 * 3 - ALT      = collaborate with probability p_CC if he has collaborated the last turn and with a probability p_CD if he has defected.
 * 4 - REAC     = collaborate with probability p_CC if the other player has collaborated the last turn and with a probability p_CD if he has defected.
 * 5 - WIN      = redo same action with probability p_R if he has won more than the other player the last turn
 * 6 - OWN      = collaborate with a high probability if the player gained more than the competitor in the majority of the last rounds
 * 8 - PROB_OPT = always defect = optimised PROP when competing against RAND
 * 9 - REAC_OPT = optimised REAC when competing against PROB_OPT
 */
public class Strategy {

	//type of strategy
	final public static int RAND = 1;
	final public static int PROB = 2;
	final public static int ALT = 3;
	final public static int REAC = 4;
	final public static int WIN = 5;
	final public static int OWN = 6;

//*** START OF MODIFIED CODE FRAGMENT ***
	final public static int PROB_OPT = 8; //constants for optimised strategies
	final public static int REAC_OPT = 9;
//*** END OF MODIFIED CODE FRAGMENT ***

	// possible MOVEs
	final static int COOPERATE = 1;
	final static int DEFECT = 0;

	// compute next move according to strategy
	public static int nextMove(Player Player1, Player Player2, int nGame) {

		int Move = -1;

		switch (Player1.PlayerStrategy) {

			case RAND:
				Move = (int)(Math.random() + 0.5); //random number between 0.5 and 1.5 casted to int
				break;

//*** START OF MODIFIED CODE FRAGMENT ***
			case PROB:
				Move = (int)(Math.random() + .4); //only cooperate with a probability of .4
				break;

			case PROB_OPT:
				Move = DEFECT; //never cooperate
				break;

			case REAC:
				double p;
				if (nGame == 0)
					p = .5; //initial probability of cooperation (always .5)
				else if (Player2.MoveHistory[nGame - 1] == COOPERATE)
					p = .6; //probability p_CC (if other player collaborated)
				else
					p = .35; //probability p_CD (if other player defected)
				Move = (int)(Math.random() + p);
				break;

			case REAC_OPT:
				if (nGame == 0)
					p = .5; //initial probability of cooperation (always .5)
				else if (Player2.MoveHistory[nGame - 1] == COOPERATE)
					p = .6; //probability p_CC (if other player collaborated)
				else
					p = 0; //probability p_CD (if other player defected)
				Move = (int)(Math.random() + p);
				break;

			case ALT:
				if (nGame == 0)
					p = .5; //initial probability of cooperation (always .5)
				else if (Player1.MoveHistory[nGame - 1] == COOPERATE)
					p = .4; //probability p_CC (to collaborated last time)
				else
					p = .65; //probability p_CC (to defected last time)
				Move = (int)(Math.random() + p);
				break;

			case OWN:
				int ttl = Math.min(7, nGame); //determine the number of rounds to analyse
				if (ttl == 0)
					p = .93;
				else {
					int won = 0; //count the number of won rounds
					for (int i = 0; i < ttl; i++) {
						float curGin = Player1.GainHistory[nGame - i - 1]; //fetch the gains of both the current player and the competitor
						float othGin = Player2.GainHistory[nGame - i - 1];
						if (curGin / (curGin + othGin) > .53f) //count round as a win if won more than 53% percent of the round's total gain
							won++;
					}
					p = ((double)won / ttl > .53 ? .93 : .03);
				}
				Move = (int)(Math.random() + p); //co-operate with a high probability if won the majority of analysed rounds
				break;
//*** END OF MODIFIED CODE FRAGMENT ***

			default:
				System.out.println("Strategy not implemented\n");
				System.exit(-1);
				break;
		}

		return Move;
	}
}
