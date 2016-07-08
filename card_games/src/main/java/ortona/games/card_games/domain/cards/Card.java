package ortona.games.card_games.domain.cards;

/**
 * This is the domain class that represents a card in a card game.
 * 
 * It is an abstract class because the method getValue depends on what game you are playing
 */
public abstract class Card implements CardInterface {
	
	//card fields are final since once they are set they cannot be modified
	protected final CardSuit suit;
	protected final CardType type;
	
	public Card(CardSuit suit, CardType type){
		this.suit = suit;
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see codingtest.domain.cards.CardInterface#getSuit()
	 */
	public CardSuit getSuit(){
		return this.suit;
	}
	
	/* (non-Javadoc)
	 * @see codingtest.domain.cards.CardInterface#getType()
	 */
	public CardType getType(){
		return this.type;
	}
	
	public String toString(){
		return this.type+" of "+this.suit;
	}
	
	/* (non-Javadoc)
	 * @see codingtest.domain.cards.CardInterface#getValue()
	 */
	public abstract int getValue();
	
}
