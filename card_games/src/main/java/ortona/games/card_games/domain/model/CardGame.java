package ortona.games.card_games.domain.model;

import java.util.List;

import ortona.games.card_games.domain.cards.ShuffleAlgorithm;

/**
 * Interface that represents a generic card game
 * 
 * @author ortona
 *
 */
public interface CardGame {

	/**
	 * Enum to specify the different types of games
	 * @author ortona
	 *
	 */
	public enum GameType{
		BLACKJACK,
		POKER,
		RUMMY;

		
		public static String friendlyPrint(){
			StringBuilder allValues = new StringBuilder();
			for(GameType oneGame : GameType.values())
				allValues.append(oneGame+" ");
			return allValues.toString();
		}

	}

	/**
	 * Get the name of the game
	 * @return
	 */
	public String getName();

	/**
	 * Play one hand of the current game.
	 * For each hand we first shuffle the deck, and then we play
	 * 
	 * @return a list of players who won the current hand
	 */
	public List<Player> playOneHand();

	/**
	 * Set the randomization strategy to shuffle the deck
	 * 
	 * @param randomizationStrategy
	 */
	public void setRandomizationShuffle(ShuffleAlgorithm randomizationStrategy);

	/**
	 * Reset the original status of the players and re-shuffle the deck as the beginning
	 */
	public void resetStatus();

}
