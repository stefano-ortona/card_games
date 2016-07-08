package ortona.games.card_games.blackjack;

import ortona.games.card_games.domain.cards.CardSuit;
import ortona.games.card_games.domain.cards.CardType;
import ortona.games.card_games.domain.cards.Deck;
import ortona.games.card_games.domain.cards.DeckBuilder;
import ortona.games.card_games.domain.cards.DeckInterface;

/**
 * This class implements a deck of black jack
 * 
 * A deck for black jack is made of 52 cards, one for each type for each suit
 */
public class BlackJackDeckBuilder implements DeckBuilder {
	
	public DeckInterface buildDeck() {
		DeckInterface blackJackDeck = new Deck();
		for(CardSuit oneSuit:CardSuit.values()){
			for(CardType oneType:CardType.values())
				blackJackDeck.addCard(new BlackJackCard(oneSuit, oneType));
				
		}
		
		return blackJackDeck;
	}

}
