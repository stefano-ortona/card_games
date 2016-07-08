package ortona.games.card_games.domain.cards;

import org.junit.Before;
import org.junit.Test;

import ortona.games.card_games.blackjack.BlackJackCard;
import junit.framework.Assert;

public class DeckTest {
	
	private Deck deck;
	
	@Before
	public void bringUp(){
		deck = new Deck();
		
		deck.addCard(new BlackJackCard(CardSuit.DIADMONDS, CardType.FIVE));
		deck.addCard(new BlackJackCard(CardSuit.SPADES, CardType.ACE));
	}
	
	@Test
	public void testGetNextCard(){
		CardInterface nextCard = deck.getNextCard();
		Assert.assertEquals(CardSuit.DIADMONDS, nextCard.getSuit());
		Assert.assertEquals(CardType.FIVE, nextCard.getType());
		
		Assert.assertTrue(deck.getRemainingCards() == 1);
		
		nextCard = deck.getNextCard();
		Assert.assertEquals(CardSuit.SPADES, nextCard.getSuit());
		Assert.assertEquals(CardType.ACE, nextCard.getType());
		
		Assert.assertTrue(deck.getRemainingCards() == 0);
		
		Assert.assertNull(deck.getNextCard());
		
		
	}
	
	@Test
	public void testGetRemainingCards(){
		Assert.assertTrue(deck.getRemainingCards() == 2);
		
		deck.addCard(new BlackJackCard(CardSuit.SPADES, CardType.ACE));
		
		Assert.assertTrue(deck.getRemainingCards() == 3);
		
		deck.getNextCard();
		deck.getNextCard();
		deck.getNextCard();
		
		Assert.assertTrue(deck.getRemainingCards() == 0);
		
		
	}

}
