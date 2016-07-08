package ortona.games.card_games.domain.cards;

/**
 * Interface responsible of building a deck of cards for a specific game
 * 
 * @author ortona
 *
 */
public interface DeckBuilder {
	
	/**
	 * Build a deck of cards
	 * @return
	 */
	public DeckInterface buildDeck();

}
