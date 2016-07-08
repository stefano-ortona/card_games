package ortona.games.card_games.domain.model;

import org.junit.Assert;
import org.junit.Test;

import ortona.games.card_games.CardGameException;
import ortona.games.card_games.domain.model.CardGame.GameType;

public class CardGameFactoryTest {

	@Test
	public void testCreateGame(){
		CardGame game = CardGameFactory.createGame(GameType.BLACKJACK, 5);
		Assert.assertNotNull(game);

		//try to create a game with a wrong number of players
		try{
			game = CardGameFactory.createGame(GameType.BLACKJACK, 7);
			Assert.fail("Should have thrown an exception for wrong numbers of players here.");
		}
		catch(CardGameException e){
			//all good
		}
		
		//try to create a game different from black jack
		try{
			game = CardGameFactory.createGame(GameType.POKER, 5);
			Assert.fail("Should have thrown an exception for not existing game.");
		}
		catch(CardGameException e){
			//all good
		}
	}

}
