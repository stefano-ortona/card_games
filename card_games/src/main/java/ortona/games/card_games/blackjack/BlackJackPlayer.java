package ortona.games.card_games.blackjack;

import java.util.List;

import com.google.common.collect.Lists;

import ortona.games.card_games.domain.cards.Card;
import ortona.games.card_games.domain.model.Player;
import ortona.games.card_games.domain.model.PlayerStatus;

/**
 * Class that implements a black jack player
 * @author ortona
 *
 */
public class BlackJackPlayer implements Player {
	
	private String name;
	
	private List<Card> currentCards;
	
	private int totalValueInHand;
	
	public BlackJackPlayer(String name){
		this.name = name;
		this.currentCards = Lists.newArrayList();
		this.totalValueInHand = 0;
	}

	public String getName() {
		return this.name;
	}

	public List<Card> getCards() {
		return this.currentCards;
	}

	/**
	 * Empty the cards hold by the player
	 */
	public void resetStatus() {
		this.currentCards.clear();
		this.totalValueInHand = 0;

	}

	/**
	 * Return the status of the player according to the total value in hand
	 */
	public PlayerStatus getStatus() {
		if(totalValueInHand < 17)
			return PlayerStatus.HIT;
		if(totalValueInHand>=17 && totalValueInHand <21)
			return PlayerStatus.STICK;
		if(totalValueInHand == 21)
			return PlayerStatus.BLACKJACK;
		return PlayerStatus.GO_BUST;
	}

	public void addCard(Card card) {
		//modify the total value in hand
		this.totalValueInHand+=card.getValue();
		this.currentCards.add(card);
	}
	
	/**
	 * Return the total value in hand of a player according to black jack game
	 */
	public int getTotalValueInHand(){
		return this.totalValueInHand;
	}
	
	public String toString(){
		return this.name;
	}

	public String friendlyPrint() {
		return this.name+" , cards: "+this.currentCards+", status: "+this.getStatus();
	}

}
