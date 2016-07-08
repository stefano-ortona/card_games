package ortona.games.card_games.domain.cards;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * This is the class that implements a deck of cards in a card game.
 */
public class Deck implements DeckInterface {
	
	private List<Card> cards;
	
	public Deck(){
		this.cards = Lists.newLinkedList();
	}
	
	/* (non-Javadoc)
	 * @see codingtest.domain.cards.DEckInterface#getNextCard()
	 */
	public Card getNextCard(){
		if(!this.cards.isEmpty()){
			return this.cards.remove(0);
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see codingtest.domain.cards.DEckInterface#shuffle(codingtest.domain.cards.ShuffleAlgorithm)
	 */
	public void shuffle(ShuffleAlgorithm shuffleStrategy){
		if(!this.cards.isEmpty())
			shuffleStrategy.shuffle(cards);
	}
	
	
	/* (non-Javadoc)
	 * @see codingtest.domain.cards.DEckInterface#addCard(codingtest.domain.cards.Card)
	 */
	public void addCard(Card card){
		this.cards.add(card);
	}
	
	/* (non-Javadoc)
	 * @see codingtest.domain.cards.DEckInterface#getRemainingCards()
	 */
	public int getRemainingCards(){
		return this.cards.size();
	}
	
	
}
