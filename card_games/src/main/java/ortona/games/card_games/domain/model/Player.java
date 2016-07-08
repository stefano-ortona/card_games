package ortona.games.card_games.domain.model;

import java.util.List;

import ortona.games.card_games.domain.cards.Card;

/**
 * Interface representing a player in a card game
 * 
 * @author ortona
 *
 */
public interface Player {
	
	/**
	 * Get the name of the player
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * Get the cards currently hold by the player
	 * 
	 * @return
	 */
	public List<Card> getCards();
	
	/**
	 * Reset the status of the player
	 */
	public void resetStatus();
	
	/**
	 * Get the status of the current player
	 * @return
	 */
	public PlayerStatus getStatus();
	
	/**
	 * Add a card to the current player
	 */
	public void addCard(Card card);
	
	/**
	 * Get the current total value in the hand of the player
	 * @return
	 */
	public int getTotalValueInHand();
	
	/**
	 * Friendly print the current cards hold by the player and his status
	 * @return
	 */
	public String friendlyPrint();

}
