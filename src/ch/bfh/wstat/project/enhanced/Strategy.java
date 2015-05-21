package ch.bfh.wstat.project.enhanced;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Possible strategies a player (prisoner) can follow in the 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 &amp; weidj1
 */
public enum Strategy {

	/**
	 * Random strategy. <br>
	 * Co-operate with a probability of 50%.
	 */
	/**
	 * Random strategy. <br>
	 * Co-operate with a probability of 50%.
	 */
	RAND {

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Move determineNextMove(Player currentPlayer, Player otherPlayer) {

				return random.nextInt(2) < 1 ? Move.COOPERATE : Move.DECEIVE;
			}
		},

	/**
	 * Probability strategy. <br>
	 * Co-operate with a probability {@code p}.
	 */
	PROB {

			/**
			 * Probability {@code p} to co-operate.
			 */
			public static final double PROBABILITY = .4;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Move determineNextMove(Player currentPlayer, Player otherPlayer) {

				return random.nextDouble() < PROBABILITY ? Move.COOPERATE : Move.DECEIVE;
			}
		},

	/**
	 * Alternating strategy. <br>
	 * Co-operate with a probability {@code p_CC} if the player also co-operated in the last round and with a probability {@code p_CD} if he deceived.
	 */
	ALT {

			/**
			 * Probability to co-operate in the first round.
			 */
			public static final double PROBABILITY_INITAL = .5;

			/**
			 * Probability {@code p_CC} to co-operate if the player co-operated in last round as well.
			 */
			public static final double PROBABILITY_COOPERATED = .4;

			/**
			 * Probability {@code p_CD} to co-operate if the player deceived in last round.
			 */
			public static final double PROBABILITY_DECEIVED = .65;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Move determineNextMove(Player currentPlayer, Player otherPlayer) {

				double p;
				if (currentPlayer.getNumberOfRounds() == 0)
					p = PROBABILITY_INITAL;
				else
					p = currentPlayer.getRound(-1).getMove() == Move.COOPERATE ? PROBABILITY_COOPERATED : PROBABILITY_DECEIVED;

				return random.nextDouble() < p ? Move.COOPERATE : Move.DECEIVE;
			}
		},

	/**
	 * Reaction strategy. <br>
	 * Co-operate with a probability {@code p_CC} if the other player co-operated in the last round and with a probability {@code p_CD} if he deceived.
	 */
	REAC {

			/**
			 * Probability to co-operate in the first round.
			 */
			public static final double PROBABILITY_INITAL = .5;

			/**
			 * Probability {@code p_CC} to co-operate if the other player co-operated in last round.
			 */
			public static final double PROBABILITY_COOPERATED = .6;

			/**
			 * Probability {@code p_CD} to co-operate if the other player deceived in last round.
			 */
			public static final double PROBABILITY_DECEIVED = .35;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Move determineNextMove(Player currentPlayer, Player otherPlayer) {

				double p;
				if (otherPlayer.getNumberOfRounds() == 0)
					p = PROBABILITY_INITAL;
				else
					p = otherPlayer.getRound(-1).getMove() == Move.COOPERATE ? PROBABILITY_COOPERATED : PROBABILITY_DECEIVED;

				return random.nextDouble() < p ? Move.COOPERATE : Move.DECEIVE;
			}
		},

	/**
	 * Winning strategy. <br>
	 * Repeat the same move with a probability {@code p_R} if the player won more than the other player in the last round.
	 */
	WIN {

			/**
			 * Probability to co-operate in the first round.
			 */
			public static final double PROBABILITY_INITAL = .5;

			/**
			 * Probability {@code p_R} to repeat the last action if the player won more than the other player in last round.
			 */
			public static final double PROBABILITY_WON = .6;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Move determineNextMove(Player currentPlayer, Player otherPlayer) {

				double p;
				if (currentPlayer.getNumberOfRounds() > 0 && otherPlayer.getNumberOfRounds() > 0
						&& currentPlayer.getRound(-1).getGain().compareTo(otherPlayer.getRound(-1).getGain()) > 0)
					p = currentPlayer.getRound(-1).getMove() == Move.COOPERATE ? PROBABILITY_WON : 1. - PROBABILITY_WON;
				else
					p = PROBABILITY_INITAL;

				return random.nextDouble() < p ? Move.COOPERATE : Move.DECEIVE;
			}
		},

	/**
	 * Our own strategy. <br>
	 * Co-operate with a high probability if the player gained more than the competitor in the majority of the last rounds.
	 */
	OWN {

			/**
			 * Number of rounds to analyse.
			 */
			public static final int HISTORY_COUNT = 7;

			/**
			 * Threshold when to consider a (number of) round(s) to be won.
			 */
			public static final String THRESHOLD_WON = ".53";
			private final BigDecimal THRESHOLD_WON_BIG_DECIMAL = new BigDecimal(THRESHOLD_WON);

			/**
			 * Probability to co-operate if the player won more (often) than the other player.
			 */
			public static final double PROBABILITY_WON = .93;

			/**
			 * Probability to co-operate if the player won less (often) than the other player.
			 */
			public static final double PROBABILITY_LOST = .03;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Move determineNextMove(Player currentPlayer, Player otherPlayer) {

				int ttl = Math.min(HISTORY_COUNT, Math.min(currentPlayer.getNumberOfRounds(), otherPlayer.getNumberOfRounds()));
				int won = 0;
				for (int i = 0; i < ttl; i++) {
					BigDecimal curGin = currentPlayer.getRound(-i - 1).getGain();
					BigDecimal othGin = otherPlayer.getRound(-i - 1).getGain();
					if (curGin.divide(curGin.add(othGin)).compareTo(this.THRESHOLD_WON_BIG_DECIMAL) > 0)
						won++;
				}

				double p = (double)won / ttl > this.THRESHOLD_WON_BIG_DECIMAL.doubleValue() ? PROBABILITY_WON : PROBABILITY_LOST;
//			if (currentPlayer.getNumberOfRounds() > 0 && currentPlayer.getRound(- 1).getMove() != Move.COOPERATE)
//				p = 1 - p;
				return random.nextDouble() < p ? Move.COOPERATE : Move.DECEIVE;
			}
		},

	/**
	 * Co-operation strategy. <br>
	 * Always co-operate.
	 */
	COOP {

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Move determineNextMove(Player currentPlayer, Player otherPlayer) {
				return Move.COOPERATE;
			}
		},

	/**
	 * Optimised probability (deception) strategy. <br>
	 * Never co-operate.
	 */
	DECV {

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Move determineNextMove(Player currentPlayer, Player otherPlayer) {
				return Move.DECEIVE;
			}
		},

	/**
	 * Optimised reaction strategy. <br>
	 * Co-operate with a probability {@code p_CC} if the other player co-operated in the last round and with a probability {@code p_CD} if he deceived.
	 */
	REAO {

			/**
			 * Probability to co-operate in the first round.
			 */
			public static final double PROBABILITY_INITAL = .5;

			/**
			 * Probability {@code p_CC} to co-operate if the other player co-operated in last round.
			 */
			public static final double PROBABILITY_COOPERATED = .6;

			/**
			 * Probability {@code p_CD} to co-operate if the other player deceived in last round.
			 */
			public static final double PROBABILITY_DECEIVED = 0;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public Move determineNextMove(Player currentPlayer, Player otherPlayer) {

				double p;
				if (otherPlayer.getNumberOfRounds() == 0)
					p = PROBABILITY_INITAL;
				else
					p = otherPlayer.getRound(-1).getMove() == Move.COOPERATE ? PROBABILITY_COOPERATED : PROBABILITY_DECEIVED;

				return random.nextDouble() < p ? Move.COOPERATE : Move.DECEIVE;
			}
		};

	private static Random random = new Random();

	/**
	 * Determine the player's next move.
	 *
	 * @param currentPlayer player to determine next move for
	 * @param otherPlayer   player to compete against
	 *
	 * @return the player's next move
	 */
	public abstract Move determineNextMove(Player currentPlayer, Player otherPlayer);
}
