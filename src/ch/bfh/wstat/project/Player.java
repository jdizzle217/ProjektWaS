package ch.bfh.wstat.project;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Player (prisoner) in the 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 & weidj1
 */
public class Player {

	private final Strategy strategy;

	private final ArrayList<RoundInformation> history = new ArrayList<>();
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
	 * Add the round's move and gain the player's history.
	 *
	 * @param move this round's move
	 * @param gain gain the move resulted in
	 */
	public void updateHistory(Move move, BigDecimal gain) {

		this.history.add(new RoundInformation(move, gain));
		this.totalGain = this.totalGain.add(gain);
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
		return this.totalGain.divide(BigDecimal.valueOf(this.history.size()));
	}
}
