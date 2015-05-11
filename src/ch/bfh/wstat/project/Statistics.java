package ch.bfh.wstat.project;

/**
 * Statistics about the 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 & weidj1
 */
public class Statistics {

	private int total = 0;
	private final int[][] events = {{0, 0}, {0, 0}};

	/**
	 * Get the (absolute) frequency of a specific event. <br />
	 * The event is defined by the moves of players 1 and 2.
	 *
	 * @param move1 move of player 1
	 * @param move2 move of player 2
	 *
	 * @return the absolute frequency of the event {@code (move1, move2)}
	 */
	public int getEventFrequency(Move move1, Move move2) {

		return this.events[move1 == Move.COOPERATE ? 1 : 0][move2 == Move.COOPERATE ? 1 : 0];
	}

	/**
	 * Increment the (absolute) frequency of a specific event. <br />
	 * The event is defined by the moves of players 1 and 2.
	 *
	 * @param move1 move of player 1
	 * @param move2 move of player 2
	 */
	public void incrementEventFrequency(Move move1, Move move2) {

		this.total++;
		this.events[move1 == Move.COOPERATE ? 1 : 0][move2 == Move.COOPERATE ? 1 : 0]++;
	}

	/**
	 * Get the relative frequency of a specific event. <br />
	 * The event is defined by the moves of players 1 and 2.
	 *
	 * @param move1 move of player 1
	 * @param move2 move of player 2
	 *
	 * @return the relative frequency of the event {@code (move1, move2)}
	 */
	public double getEventRelativeFrequency(Move move1, Move move2) {

		return (double)this.getEventFrequency(move1, move2) / this.total;
	}
}
