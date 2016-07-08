package ortona.games.card_games.blackjak;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ortona.games.card_games.domain.cards.CardType;
import ortona.games.card_games.domain.cards.DeckInterface;
import ortona.games.card_games.blackjack.BlackJackDeckBuilder;
import ortona.games.card_games.domain.cards.CardInterface;
import ortona.games.card_games.domain.cards.CardSuit;

public class BlackJackDeckBuilderTest {
	
	private DeckInterface blackJackDeck;
	
	@Before
	public void bringUp(){
		BlackJackDeckBuilder deckBuilder = new BlackJackDeckBuilder();
		this.blackJackDeck = deckBuilder.buildDeck();
		
	}
	
	/**
	 * A black jack deck must be built with 52 cards, with each card of every type
	 */
	@Test
	public void buildDeckTest(){
		
		Assert.assertTrue(this.blackJackDeck.getRemainingCards() == 52);
		CardInterface currentCard;
		for(CardSuit oneSuit:CardSuit.values()){
			for(CardType oneType:CardType.values()){
				currentCard = this.blackJackDeck.getNextCard();
				Assert.assertEquals(oneSuit, currentCard.getSuit());
				Assert.assertEquals(oneType, currentCard.getType());
			}
		}
		
		Assert.assertTrue(this.blackJackDeck.getRemainingCards() == 0);
		
	}

}
