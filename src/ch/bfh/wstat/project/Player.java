package ch.bfh.wstat.project;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

/**
 * Player (prisoner) in the 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 &amp; weidj1
 */
public class Player {

	private final Strategy strategy;

	private final ArrayList<Round> rounds = new ArrayList<>();
	private BigDecimal totalGain = BigDecimal.ZERO;

	/**
	 * Construct a new player with a specific strategy.
	 *
	 * @param strategy strategy to follow
	 */
	Player(Strategy strategy) {

		this.strategy = strategy;
	}

	/**
	 * Get the number of rounds this player has played already.
	 *
	 * @return number of rounds this player has played already
	 */
	public int getNumberOfRounds() {
		return this.rounds.size();
	}

	/**
	 * Get information about a specific round.
	 *
	 * @param index index of the round if {@code index >= 0}, or nth to last round if {@code index < 0}
	 *
	 * @return information about the specified round
	 *
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public Round getRound(int index) {

		return this.rounds.get(index >= 0 ? index : this.rounds.size() + index);
	}

	/**
	 * Add the round's move and gain the player's history.
	 *
	 * @param move this round's move
	 * @param gain gain the move resulted in
	 *
	 * @return information about the added round
	 */
	public Round addRound(Move move, BigDecimal gain) {

		Round r = new Round(this.rounds.size(), this, move, gain);
		this.totalGain = this.totalGain.add(gain);

		this.rounds.add(r);
		return r;
	}

	/**
	 * Get the strategy the player follows.
	 *
	 * @return strategy the player follows
	 */
	public Strategy getStrategy() {
		return this.strategy;
	}

	/**
	 * Get the player's total gain up to the current round.
	 *
	 * @return player's total gain up to the current round
	 */
	public BigDecimal getTotalGain() {
		return this.totalGain;
	}

	/**
	 * Get the player's middle gain during the rounds already played.
	 *
	 * @return player's middle gain during the rounds already played
	 */
	public BigDecimal getMiddleGain() {
		return this.totalGain.divide(BigDecimal.valueOf(this.rounds.size()), MathContext.DECIMAL128);
	}
}
