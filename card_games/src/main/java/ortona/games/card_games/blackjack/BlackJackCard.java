package ortona.games.card_games.blackjack;

import ortona.games.card_games.domain.cards.Card;
import ortona.games.card_games.domain.cards.CardType;
import ortona.games.card_games.domain.cards.CardSuit;

/**
 * A black jack card that implements a specific value for each card
 * @author ortona
 *
 */
public class BlackJackCard extends Card {

	public BlackJackCard(CardSuit suit, CardType type) {
		super(suit, type);
	}

	@Override
	public int getValue() {
		switch(type){

		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		case TEN:
			return 10;
		case JACK:
			return 10;
		case QUEEN:
			return 10;
		case KING:
			return 10;
		case ACE:
			return 11;
		}

		return 0;
	}


}
