package ortona.games.card_games.domain.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ortona.games.card_games.CardGameException;
import ortona.games.card_games.blackjack.BlackJackCardGame;
import ortona.games.card_games.domain.model.CardGame.GameType;

/**
 * Factory to create a card game given the name of the game
 * 
 * @author ortona
 *
 */
public class CardGameFactory {

	private final static Logger LOGGER = LoggerFactory.getLogger(CardGameFactory.class.getName());

	/**
	 * Create and return a card game given the name of the gamr
	 * @param gameType
	 * @return
	 */
	public static CardGame createGame(GameType gameType, int numPlayers){

		CardGame cardGame;
		if(gameType.equals(CardGame.GameType.BLACKJACK)){
			cardGame = new BlackJackCardGame(numPlayers);
			return cardGame;

		}
		//all other cases have not been implemented
		throw new CardGameException("I'm sorry but other games are not available at the moment, please play "+CardGame.GameType.BLACKJACK,LOGGER);
	}

}
