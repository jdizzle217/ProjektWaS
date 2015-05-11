package ch.bfh.wstat.project;

import java.math.BigDecimal;

/**
 * Information about a player's (prisoner's) move & gain in one round of the 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 & weidj1
 */
public class RoundInformation {

	private final Move move;
	private final BigDecimal gain;

	/**
	 * Construct a new round information.
	 *
	 * @param move player's move in this round
	 * @param gain player's gain from the move
	 */
	public RoundInformation(Move move, BigDecimal gain) {

		this.move = move;
		this.gain = gain;
	}

	/**
	 * Get the player's move in this round.
	 *
	 * @return player's move in this round
	 */
	public Move getMove() {
		return this.move;
	}

	/**
	 * Get the player's gain from the move.
	 *
	 * @return player's gain from the move
	 */
	public BigDecimal getGain() {
		return this.gain;
	}
}
