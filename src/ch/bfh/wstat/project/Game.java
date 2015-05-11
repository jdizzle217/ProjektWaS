package ch.bfh.wstat.project;

import java.math.BigDecimal;

/**
 * Implementation of the 'Iterated Prisoner's Dilemma Game'.
 *
 * @author strut1 &amp; weidj1
 */
public class Game {

	/**
	 * Standard gain for mutual collaboration.
	 */
	public static final BigDecimal GAIN_COLLABORATION = BigDecimal.valueOf(3);

	/**
	 * Standard gain for deception when other player collaborates.
	 */
	public static final BigDecimal GAIN_DECEPTION = BigDecimal.valueOf(5);

	/**
	 * Standard gain for collaboration when other player deceives.
	 */
	public static final BigDecimal GAIN_LOSS = BigDecimal.valueOf(0);

	/**
	 * Standard gain for mutual deception.
	 */
	public static final BigDecimal GAIN_DECEPTION_ATTEMPT = BigDecimal.valueOf(1);

	private final Player player1, player2; //participants

	private final int rounds; //game information
	private int round = 0;
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

		while (this.round < this.rounds)
			this.playRound();
	}

	/**
	 * Play a single round.
	 */
	public void playRound() {

		if (this.round >= this.rounds) //check if there are rounds left to play
			throw new IllegalStateException("Maximum number of rounds reached.");

		Move move1 = this.player1.getStrategy().determineNextMove(this.player1, this.player2); //determine the players' next moves
		Move move2 = this.player2.getStrategy().determineNextMove(this.player2, this.player1);

		if (move1 == Move.COOPERATE) //determine the round's gains and update players' histories
			if (move2 == Move.COOPERATE) {
				this.player1.updateHistory(move1, GAIN_COLLABORATION); //mutual collaboration (R, R)
				this.player2.updateHistory(move2, GAIN_COLLABORATION);

			} else {
				this.player1.updateHistory(move1, GAIN_LOSS); //player 1 loses (S, T)
				this.player2.updateHistory(move2, GAIN_DECEPTION);
			}

		else if (move2 == Move.COOPERATE) {
			this.player1.updateHistory(move1, GAIN_DECEPTION); //player 2 loses (T, S)
			this.player2.updateHistory(move2, GAIN_LOSS);

		} else {
			this.player1.updateHistory(move1, GAIN_DECEPTION_ATTEMPT); //mutual deception (P, P)
			this.player2.updateHistory(move2, GAIN_DECEPTION_ATTEMPT);
		}

		this.statistics.incrementEventFrequency(move1, move2); //update statistics

		this.round++; //increment round index
	}
}
