package ch.bfh.wstat.project.enhanced;

/**
 * Statistics about the 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 &amp; weidj1
 */
public class Statistics {

	private int total = 0;
	private final int[][] events = {{0, 0}, {0, 0}};

	/**
	 * Get the (absolute) frequency of a specific event. <br>
	 * The event is defined by the moves of players 1 and 2.
	 *
	 * @param move1 move of player 1
	 * @param move2 move of player 2
	 *
	 * @return the absolute frequency of the event {@code (move1, move2)}
	 */
	public int getEventFrequency(Move move1, Move move2) {

		return this.events[move1.ordinal()][move2.ordinal()];
	}

	/**
	 * Increment the (absolute) frequency of a specific event. <br>
	 * The event is defined by the moves of players 1 and 2.
	 *
	 * @param move1 move of player 1
	 * @param move2 move of player 2
	 */
	public void incrementEventFrequency(Move move1, Move move2) {

		this.total++;
		this.events[move1.ordinal()][move2.ordinal()]++;
	}

	/**
	 * Get the relative frequency of a specific event. <br>
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
