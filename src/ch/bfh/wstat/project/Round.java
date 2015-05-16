package ch.bfh.wstat.project;

import java.math.BigDecimal;

/**
 * Information about a player's (prisoner's) move &amp; gain in one round of the 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 &amp; weidj1
 */
public class Round {

	private final int index;
	private final Player player;
	private final Move move;
	private final BigDecimal gain;

	/**
	 * Construct a new round information.
	 *
	 * @param index  index of the round
	 * @param player player this information references to
	 * @param move   player's move in this round
	 * @param gain   player's gain from the move
	 */
	public Round(int index, Player player, Move move, BigDecimal gain) {

		this.index = index;
		this.player = player;
		this.move = move;
		this.gain = gain;
	}

	/**
	 * Get the index of the round.
	 *
	 * @return index of the round
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Get the player this information references to.
	 *
	 * @return player this information references to
	 */
	public Player getPlayer() {
		return this.player;
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

	/**
	 * Get information about the previous round.
	 *
	 * @return information about the previous round
	 *
	 * @throws IndexOutOfBoundsException if there is no previous round
	 */
	public Round previous() {
		return this.player.getRound(this.index - 1);
	}

	/**
	 * Get information about the next round.
	 *
	 * @return information about the next round
	 *
	 * @throws IndexOutOfBoundsException if there is no next round
	 */
	public Round next() {
		return this.player.getRound(this.index + 1);
	}
}
