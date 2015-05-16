package ch.bfh.wstat.project;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Implementation of the 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 &amp; weidj1
 */
public class Game {

	/**
	 * Standard gain for mutual co-operation.
	 */
	public static final BigDecimal GAIN_COOPERATION = BigDecimal.valueOf(3);

	/**
	 * Standard gain for deception when other player co-operates.
	 */
	public static final BigDecimal GAIN_WIN = BigDecimal.valueOf(5);

	/**
	 * Standard gain for co-operation when other player deceives.
	 */
	public static final BigDecimal GAIN_LOSS = BigDecimal.valueOf(0);

	/**
	 * Standard gain for mutual deception.
	 */
	public static final BigDecimal GAIN_DECEPTION = BigDecimal.valueOf(1);

	private final Player player1, player2; //participants

	private final int rounds; //game information
	private int roundIndex = 0;
	private final Statistics statistics = new Statistics();

	/**
	 * Construct a game.
	 *
	 * @param player1 first participant / player
	 * @param player2 second participant / player
	 * @param rounds  number of rounds to play
	 */
	public Game(Player player1, Player player2, int rounds) {

		this.player1 = player1;
		this.player2 = player2;
		this.rounds = rounds;
	}

	/**
	 * Get the game statistics.
	 *
	 * @return game statistics
	 */
	public Statistics getStatistics() {
		return this.statistics;
	}

	/**
	 * Play all remaining rounds. (complete game)
	 */
	public void playRounds() {

		while (this.roundIndex < this.rounds)
			this.playRound();
	}

	/**
	 * Play a single round.
	 */
	public void playRound() {

		if (this.roundIndex >= this.rounds) //check if there are rounds left to play
			throw new IllegalStateException("Maximum number of rounds reached.");

		Move move1 = this.player1.getStrategy().determineNextMove(this.player1, this.player2); //determine the players' next moves
		Move move2 = this.player2.getStrategy().determineNextMove(this.player2, this.player1);

		if (move1 == Move.COOPERATE) //determine the round's gains and update players' histories
			if (move2 == Move.COOPERATE) {
				this.player1.addRound(move1, GAIN_COOPERATION); //mutual co-operation (R, R)
				this.player2.addRound(move2, GAIN_COOPERATION);

			} else {
				this.player1.addRound(move1, GAIN_LOSS); //player 1 loses (S, T)
				this.player2.addRound(move2, GAIN_WIN);
			}

		else if (move2 == Move.COOPERATE) {
			this.player1.addRound(move1, GAIN_WIN); //player 2 loses (T, S)
			this.player2.addRound(move2, GAIN_LOSS);

		} else {
			this.player1.addRound(move1, GAIN_DECEPTION); //mutual deception (P, P)
			this.player2.addRound(move2, GAIN_DECEPTION);
		}

		this.statistics.incrementEventFrequency(move1, move2); //update statistics

		this.roundIndex++; //increment round index
	}

	/**
	 * Get the players' total gain up to the current round.
	 *
	 * @return players' total gain up to the current round
	 */
	public BigDecimal getTotalGain() {
		return this.player1.getTotalGain().add(this.player2.getTotalGain());
	}

	/**
	 * Get the players' middle gain during the rounds already played.
	 *
	 * @return players' middle gain during the rounds already played
	 */
	public BigDecimal getMiddleGain() {
		return this.getTotalGain().divide(BigDecimal.valueOf(this.roundIndex), MathContext.DECIMAL128);
	}
}
