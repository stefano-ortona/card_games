package ortona.games.card_games.domain.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ortona.games.card_games.CardGameException;
import ortona.games.card_games.domain.cards.ShuffleAlgorithm.AlgorithmType;
/**
 * Factory to create a shuffle algorithm given the name of the algorithm
 * 
 * @author ortona
 *
 */
public class ShuffleAlgorithmFactory {

	private final static Logger LOGGER = LoggerFactory.getLogger(ShuffleAlgorithmFactory.class.getName());

	/**
	 * Create and return a card game given the name of the gamr
	 * @param gameType
	 * @return
	 */
	public static ShuffleAlgorithm createAlgorithm(AlgorithmType algorithmType){

		ShuffleAlgorithm algorithm;
		if(algorithmType.equals(AlgorithmType.RANDOM)){
			algorithm = new RandomShuffle();
			return algorithm;
		}
		//all other cases have not been implemented
		throw new CardGameException("I'm sorry but other randomization algorithms are not available at the moment, "
				+ "please use "+AlgorithmType.RANDOM,LOGGER);
	}

}
