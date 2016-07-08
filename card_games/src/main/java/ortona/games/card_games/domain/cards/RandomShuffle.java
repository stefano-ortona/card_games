package ortona.games.card_games.domain.cards;

import java.util.Collections;
import java.util.List;

public class RandomShuffle implements ShuffleAlgorithm {
	
	private final static String NAME = "RandomShuffle";

	public void shuffle(List<Card> cards) {
		Collections.shuffle(cards);

	}
	
	public String getName(){
		return NAME;
	}



}
