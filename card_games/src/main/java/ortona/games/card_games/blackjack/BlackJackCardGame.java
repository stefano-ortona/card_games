package ortona.games.card_games.blackjack;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import ortona.games.card_games.CardGameException;
import ortona.games.card_games.domain.cards.Card;
import ortona.games.card_games.domain.cards.DeckInterface;
import ortona.games.card_games.domain.cards.DeckBuilder;
import ortona.games.card_games.domain.cards.ShuffleAlgorithm;
import ortona.games.card_games.domain.model.CardGame;
import ortona.games.card_games.domain.model.Player;
import ortona.games.card_games.domain.model.PlayerStatus;

/**
 * Class that implements black jack game
 * 
 * It can be instantiated with a number of players between 1 and 6, and the playOneHand() method runs a hand of black jack 
 * logging to console each round step by step
 * 
 * The method setRandomizationShuffle can be used to specify the randomization shuffle algorithm used to shuffle the deck
 * @author ortona
 *
 */
public class BlackJackCardGame implements CardGame{

	private final static String GAME_NAME = "Black Jack";

	private final static Logger LOGGER = LoggerFactory.getLogger(BlackJackCardGame.class.getName());

	private ShuffleAlgorithm randomizationAlgorithm;

	private List<Player> players;

	private DeckInterface deck;

	/**
	 * Constructor that initialize the players and the deck
	 * @param numPlayers in black jack we can have 
	 */
	public BlackJackCardGame(int numPlayers){
		if(numPlayers>6 || numPlayers<1)
			throw new CardGameException("Try to initialize the game with "+numPlayers+" players. "
					+ "Number of players must belong to [1,6].", LOGGER);

		this.players = Lists.newLinkedList();

		for(int i=0;i<numPlayers;i++){
			Player player = new BlackJackPlayer("Player"+i);
			this.players.add(player);

		}

		//set the status to the origin, includes creating the deck
		this.resetStatus();

	}

	/**
	 * Play one hand of the game and returns a list of winners
	 */
	public List<Player> playOneHand() {
		//copy the current players into a new data structure that can be modified
		List<Player> currentHandPlayer = Lists.newLinkedList(this.players);

		//first shuffle the cards
		if(this.randomizationAlgorithm != null){
			LOGGER.info("Shuffling the deck with {} randomization algorithm.",this.randomizationAlgorithm.getName());
			this.deck.shuffle(this.randomizationAlgorithm);
		}

		//give two cards to each player
		LOGGER.info("Giving players a initial round of 2 cards.");
		for(int i=0; i<2; i++){
			for(Player onePlayer:currentHandPlayer){
				if(this.deck.getRemainingCards() == 0)
					throw new CardGameException("Sorry but cannot complete the game since the deck does no "
							+ "longer have cards.", LOGGER);

				Card currentCard = this.deck.getNextCard();
				LOGGER.info("Player {} gets the card: {}",onePlayer,currentCard);
				onePlayer.addCard(currentCard);
			}
		}

		//first check that each player has not gone busted or hit 21
		LOGGER.info("Checking players initial status.");
		for(int indexPlayer=0;indexPlayer<currentHandPlayer.size();indexPlayer++){
			Player currentPlayer = currentHandPlayer.get(indexPlayer);
			LOGGER.info(currentPlayer.friendlyPrint());
			//if a player hit 21, get the winners
			if(currentPlayer.getStatus().equals(PlayerStatus.BLACKJACK)){
				LOGGER.info("A player hits 21, game has ended.");
				return this.getWinners(currentHandPlayer);
			}

			//if a player go bust, remove it from the current hand
			if(currentPlayer.getStatus().equals(PlayerStatus.GO_BUST)){
				LOGGER.info("Player {} eliminated from the game.",currentPlayer);
				currentHandPlayer.remove(indexPlayer);
				indexPlayer--;
			}
		}

		int turnsCount = 0;
		boolean nextTurn = true;

		//play turns until everyone does not hit another card
		while(nextTurn){
			nextTurn = false;
			turnsCount++;
			LOGGER.info("Playing turns number {}.",turnsCount);

			for(int indexPlayer=0;indexPlayer<currentHandPlayer.size();indexPlayer++){
				Player currentPlayer = currentHandPlayer.get(indexPlayer);

				if(currentPlayer.getStatus().equals(PlayerStatus.HIT)){
					LOGGER.info("Player {} is asking for another card.",currentPlayer);
					if(this.deck.getRemainingCards() == 0)
						throw new CardGameException("Sorry but cannot complete the game since the deck does no "
								+ "longer have cards.", LOGGER);

					Card currentCard = this.deck.getNextCard();
					LOGGER.info("Player {} gets the card: {}",currentPlayer,currentCard);
					currentPlayer.addCard(currentCard);

					LOGGER.info(currentPlayer.friendlyPrint());

					//now check the player has not gone busted or hit 21
					//if a player hit 21, get the winners
					if(currentPlayer.getStatus().equals(PlayerStatus.BLACKJACK)){
						LOGGER.info("A player hits 21, game has ended.");
						return this.getWinners(currentHandPlayer);
					}

					//if a player go bust, remove it from the current hand
					if(currentPlayer.getStatus().equals(PlayerStatus.GO_BUST)){
						LOGGER.info("Player {} eliminated from the game.",currentPlayer);
						currentHandPlayer.remove(indexPlayer);
						indexPlayer--;
					}
					else{
						//the player has not hit 21 nor has gone busted, another turn must be run
						nextTurn = true;
					}

				}

				else
					LOGGER.info("Player {} is not asking for another card.",currentPlayer);
			}


		}

		//all remaining players stick, get the winner
		LOGGER.info("No player is asking for another card, game has ended.");
		return this.getWinners(currentHandPlayer);
	}

	/**
	 * Set the randomization algorithm to shuffle the cards
	 */
	public void setRandomizationShuffle(ShuffleAlgorithm randomizationStrategy) {
		this.randomizationAlgorithm = randomizationStrategy;
	}

	public void resetStatus() {
		for(Player onePlayer:players)
			onePlayer.resetStatus();

		//create the deck of cards
		DeckBuilder deckBuilder = new BlackJackDeckBuilder();
		this.deck = deckBuilder.buildDeck();

	}

	public String getName() {
		return GAME_NAME;
	}

	/**
	 * Winners are the player with the highest score less or equal 21 among the surviving players
	 * 
	 * Note that a player with a value in hand greater than 21 never wins the game
	 * @param currentPlayers	current players playing the hand
	 * @return	list of winners
	 */
	private List<Player> getWinners(List<Player> currentPlayers){
		LOGGER.info("Game has ended, calculating the winner...");
		//first compute the highest score less than 21
		int maxScore = -1;
		for(Player onePlayer:currentPlayers){
			int currentScore = onePlayer.getTotalValueInHand();
			if(currentScore <= 21 && currentScore > maxScore)
				maxScore = currentScore;
		}

		//remove from the current player the not winners
		for(int indexPlayer = 0; indexPlayer < currentPlayers.size(); indexPlayer++){
			if(currentPlayers.get(indexPlayer).getTotalValueInHand() != maxScore){
				currentPlayers.remove(indexPlayer);
				indexPlayer--;
			}

		}

		//now current players contains only those players with maximum value less than 21. Can be empty -> nobody wins
		return currentPlayers;
	}
}
