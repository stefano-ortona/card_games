package ortona.games.card_games.domain.cards;

import java.util.List;

/**
 * Class to represent different shuffling algorithms to shuffle a deck of cards
 * @author ortona
 *
 */
public interface ShuffleAlgorithm {
	
	//different plausible shuffle algorithms
	public enum AlgorithmType{
		RANDOM,
		RIFFLE,
		PHAROAH;
		
		public static String friendlyPrint(){
			StringBuilder allValues = new StringBuilder();
			for(AlgorithmType oneAlgorithm : ShuffleAlgorithm.AlgorithmType.values())
				allValues.append(oneAlgorithm+" ");
			return allValues.toString();
		}
	}
	
	/**
	 * Shuffle the input list of cards
	 * @param cards the input list of cards that needs to be shuffled
	 */
	public void shuffle(List<Card> cards);
	
	/**
	 * Get the name of the shuffling algorithm
	 * @return
	 */
	public String getName();

}
