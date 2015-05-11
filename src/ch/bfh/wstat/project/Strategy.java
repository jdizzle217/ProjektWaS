package ch.bfh.wstat.project;

import java.util.Random;

/**
 * Possible strategies a player (prisoner) can follow in the 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 & weidj1
 */
public enum Strategy {

	/**
	 * Random strategy. <br />
	 * Collaborate with a probability of 50%.
	 */
	RAND {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Move determineNextMove(Player currentPlayer, Player otherPlayer) {
			return random.nextDouble() <= .5 ? Move.COOPERATE : Move.DECEIVE;
		}
	},

	/**
	 * Probability strategy. <br />
	 * Collaborate with a probability {@code p}.
	 */
	PROB {

		/**
		 * Probability {@code p} to collaborate.
		 */
		public static final double PROBABILITY = .4;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Move determineNextMove(Player currentPlayer, Player otherPlayer) {
			return random.nextDouble() <= PROBABILITY ? Move.COOPERATE : Move.DECEIVE;
		}
	},

	/**
	 * Reaction strategy. <br />
	 * Collaborate with a probability {@code p_CC} if the other player collaborated in the last round and with a probability {@code p_CD} if he deceived.
	 */
	REAC {

		/**
		 * Probability {@code p_CC} to collaborate if the other player collaborated in last round.
		 */
		public static final double PROBABILITY_COLLABORATED = .60;

		/**
		 * Probability {@code p_CD} to collaborate if the other player deceived in last round.
		 */
		public static final double PROBABILITY_DECEIVED = .35;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Move determineNextMove(Player currentPlayer, Player otherPlayer) {
			throw new UnsupportedOperationException("Strategy not yet implemented."); //TODO: implement strategy
		}
	},

	/**
	 * Alternating strategy. <br />
	 * Collaborate with a probability {@code p_CC} if the player also collaborated in the last round and with a probability {@code p_CD} if he deceived.
	 */
	ALTE {

		/**
		 * Probability {@code p_CC} to collaborate if the player collaborated in last round as well.
		 */
		public static final double PROBABILITY_COLLABORATED = .40;

		/**
		 * Probability {@code p_CD} to collaborate if the player deceived in last round.
		 */
		public static final double PROBABILITY_DECEIVED = .65;

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Move determineNextMove(Player currentPlayer, Player otherPlayer) {
			throw new UnsupportedOperationException("Strategy not yet implemented."); //TODO: implement strategy
		}
	},

	/**
	 * Winning strategy. <br />
	 * Repeat the same move with a probability {@code p_R} if the player won more than the other player in the last round.
	 */
	WIN {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Move determineNextMove(Player currentPlayer, Player otherPlayer) {
			throw new UnsupportedOperationException("Strategy not yet implemented."); //TODO: implement strategy
		}
	},

	/**
	 * Our own strategy.
	 */
	OWN {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Move determineNextMove(Player currentPlayer, Player otherPlayer) {
			throw new UnsupportedOperationException("Strategy not yet implemented."); //TODO: implement strategy
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
