package ortona.games.card_games.domain.cards;

/**
 * Interface for a deck of cards
 * 
 * @author ortona
 *
 */
public interface DeckInterface {

	/**
	 * Get the next card and eliminate it from the dock
	 * @return
	 */
	public Card getNextCard();

	/**
	 * /**
	 * Shuffle the cards in the deck using the input shuffle strategy
	 * @param shuffleStrategy
	 */
	public void shuffle(ShuffleAlgorithm shuffleStrategy);

	/**
	 * Add a card to the current deck
	 * @param card
	 */
	void addCard(Card card);

	/**
	 * Get remaining cards in the deck
	 * @return the number of remaining cards in the deck
	 */
	int getRemainingCards();

}