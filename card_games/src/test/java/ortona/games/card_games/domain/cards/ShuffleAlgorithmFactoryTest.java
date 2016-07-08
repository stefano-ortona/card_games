package ortona.games.card_games.domain.cards;

import org.junit.Assert;
import org.junit.Test;

import ortona.games.card_games.CardGameException;
import ortona.games.card_games.domain.cards.ShuffleAlgorithm.AlgorithmType;

public class ShuffleAlgorithmFactoryTest {
	
	@Test
	public void testCreateGame(){
		ShuffleAlgorithm algorithm = ShuffleAlgorithmFactory.createAlgorithm(AlgorithmType.RANDOM);
		Assert.assertNotNull(algorithm);

		
		//try to create an algorithm different from random
		try{
			algorithm = ShuffleAlgorithmFactory.createAlgorithm(AlgorithmType.PHAROAH);
			Assert.fail("Should have thrown an exception for not existing algorithm.");
		}
		catch(CardGameException e){
			//all good
		}
	}

}
