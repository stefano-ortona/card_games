package ortona.games.card_games.domain.cards;

/**
 * Interface for a card in a card game
 * 
 * @author ortona
 *
 */
public interface CardInterface {

	public CardSuit getSuit();

	public CardType getType();

	/**
	 * Method that returns the value of a card in a specific game
	 * 
	 * @return the value of the card in a specific game, 0 if this value is unknown
	 */
	public int getValue();

}