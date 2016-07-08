package ortona.games.card_games.blackjak;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ortona.games.card_games.CardGameException;
import ortona.games.card_games.blackjack.BlackJackCardGame;
import ortona.games.card_games.domain.model.Player;

public class BlackJackCardGameTest {
	
	private BlackJackCardGame cardGame;
	
	@Before
	public void bringUp(){
		cardGame = new BlackJackCardGame(4);
		
	}
	
	/**
	 * Cards have not been shuffled so we know exactly how the game should end
	 */
	@Test
	public void testPlayOneHand(){
		List<Player> winners = cardGame.playOneHand();
		Assert.assertTrue(winners.size()==1);
		Assert.assertEquals(winners.get(0).getName(),"Player1");
		
	}
	
	@Test
	public void testPlayOneHandOnePlayer(){
		cardGame = new BlackJackCardGame(1);
		List<Player> winners = cardGame.playOneHand();
		Assert.assertTrue(winners.size()==1);
		Assert.assertEquals(winners.get(0).getName(),"Player0");
		
	}
	
	@Test
	public void testWrongNumberOfPlayers(){
		try{
			cardGame = new BlackJackCardGame(7);
			Assert.fail("Should have thrown an exception here for wrong numbers of players.");
		}
		catch(CardGameException e){
			//all good
		}
	}

}
