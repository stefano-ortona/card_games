package ortona.games.card_games.blackjak;

import org.junit.Before;
import org.junit.Test;

import ortona.games.card_games.blackjack.BlackJackCard;
import ortona.games.card_games.blackjack.BlackJackPlayer;
import ortona.games.card_games.domain.cards.Card;
import ortona.games.card_games.domain.cards.CardSuit;
import ortona.games.card_games.domain.cards.CardType;
import ortona.games.card_games.domain.model.PlayerStatus;
import junit.framework.Assert;

public class BlackJackPlayerTest {
	
	private BlackJackPlayer player;
	
	@Before
	public void bringUp(){
		player = new BlackJackPlayer("fake_player");
		
		Card firstCard = new BlackJackCard(CardSuit.CLUBS, CardType.ACE);
		player.addCard(firstCard);
		
		Card secondCard = new BlackJackCard(CardSuit.DIADMONDS, CardType.SEVEN);
		player.addCard(secondCard);
		
	}
	
	@Test
	public void testGetStatus(){
		Assert.assertEquals(player.getStatus(), PlayerStatus.STICK);
		Assert.assertTrue(player.getTotalValueInHand() == 18);
		
		//check black jack status
		Card anotherCard = new BlackJackCard(CardSuit.SPADES, CardType.THREE);
		player.addCard(anotherCard);
		Assert.assertEquals(player.getStatus(), PlayerStatus.BLACKJACK);
		Assert.assertTrue(player.getTotalValueInHand() == 21);
	}
	
	

}
