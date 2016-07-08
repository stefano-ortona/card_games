package ortona.games.card_games;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ortona.games.card_games.domain.cards.ShuffleAlgorithm;
import ortona.games.card_games.domain.cards.ShuffleAlgorithmFactory;
import ortona.games.card_games.domain.cards.ShuffleAlgorithm.AlgorithmType;
import ortona.games.card_games.domain.model.CardGame;
import ortona.games.card_games.domain.model.CardGame.GameType;
import ortona.games.card_games.domain.model.CardGameFactory;
import ortona.games.card_games.domain.model.Player;

/**
 * Class that handles the playing of a card game from a simple command line interface,
 * and echoes back a step-by-step description of the game to the console.
 * 
 * You can play multiple rounds of the same game, and decide when to stop after each round
 */
public class CardGameApp {

	private final static String LOG_FILE = "src/main/config/DefaultLog4j.properties";

	private final static Logger LOGGER = LoggerFactory.getLogger(CardGameApp.class.getName());

	private final static int DEFAULT_PLAYERS_NUMBER = 3;

	private static CardGame cardGame;

	private static ShuffleAlgorithm algorithm;

	private static int playersNumber;

	/**
	 * Main. Plays a card game from a command line interface.
	 * @param args the arguments to the game
	 * 
	 * The first argument is the name of the game, the second argument is the number of players, 
	 * the third argument is the randomization parameter to shuffle the deck of cards
	 * 
	 * First parameter is the only mandatory, if second and third are not provided then default numbers are used.
	 * Default for number of players: 3
	 * Defualt for randomisation algorithm: none (cards are not shuffled)
	 * 
	 * Example Invocation:
	 * 
	 * CardGameApp	BLACKJACK 5 RANDOM
	 * It plays black jack with 5 players using a random shuffle algorithm
	 */
	public static void main(String[] args) {

		//read the logger properties			
		PropertyConfigurator.configure(LOG_FILE);

		//firsr validate parameter
		initialiseAndValidateParameters(args);
		
		LOGGER.info("Great, we are ready to start! We are playing {} with {} players.",cardGame.getName(),playersNumber);
		if(algorithm != null)
			LOGGER.info("Deck of cards will be shuffled with {} algorithm.",algorithm.getName());


		Scanner scannerConsole = new Scanner(System.in);
		while(true){
			List<Player> winners = cardGame.playOneHand();
			if(winners.size()==0)
				LOGGER.info("Oh that is shameful, nobody won the current hand!");
			else
				LOGGER.info("Congratulate the winners for the current hand: WINNERS: {}.",winners);

			//ask the user if she wants to play another round
			LOGGER.info("Would you like to play another round? Say yes (y) to continue");
			String answer = scannerConsole.nextLine();

			if(!answer.equals("yes") && !answer.equals("y")){
				LOGGER.info("Thanks for playing, see you later!");
				break;
			}
			//reset the game and re-starts
			cardGame.resetStatus();
		}
		scannerConsole.close();

	}

	/**
	 * Initialise and validate the 3 input parameters to run the game 
	 * @param args
	 */
	private static void initialiseAndValidateParameters(String[] args){
		if(args.length <1 || args.length > 3)
			throw new CardGameException("I'm sorry, I did not understand your request. Please provide three input parameters "+
					"<CARD_GAME> <NUM_PLAYERS> <RANDOMIZATION_ALGORITHM>. Choose among the following values:" 
					+ System.getProperty("line.separator")+
					"CARD GAME = "+CardGame.GameType.friendlyPrint() + System.getProperty("line.separator") +
					"NUMBER OF PLAYERS = a number between 1 and 6" + System.getProperty("line.separator") + 
					"RANDOMIZATION ALGORITHM = "+ShuffleAlgorithm.AlgorithmType.friendlyPrint(), LOGGER);

		GameType gameType = null;
		//try to instantiate the game
		try{
			gameType = GameType.valueOf(args[0]);
		}
		catch(Exception e){
			throw new CardGameException("Sorry, I do not know the card game name you specified!", LOGGER);
		}

		//read number of players
		playersNumber = DEFAULT_PLAYERS_NUMBER;
		if(args.length>1){
			try{
				playersNumber = Integer.parseInt(args[1]);
			}
			catch(Exception e ){
				LOGGER.warn("Sorry, did not get the number of players you specified. Playing with default number: {}",DEFAULT_PLAYERS_NUMBER);
			}
			if(playersNumber<1 || playersNumber>7){
				LOGGER.warn("Sorry, the number of players must be between 1 and 6. Playing with default number: {}",DEFAULT_PLAYERS_NUMBER);
				playersNumber = DEFAULT_PLAYERS_NUMBER;
			}

		}

		//create the card game with the specified parameters
		cardGame = CardGameFactory.createGame(gameType, playersNumber);

		//get the randomization algorithm

		if(args.length > 2){
			try{
				AlgorithmType algorithmType = AlgorithmType.valueOf(args[2]);
				
				algorithm = ShuffleAlgorithmFactory.createAlgorithm(algorithmType);
				
				if(algorithm != null)
					cardGame.setRandomizationShuffle(algorithm);
			}
			catch(Exception e){
				LOGGER.warn("Sorry, I do not know the randomization algorithm you specified. I will not shuffle the deck of cards!", LOGGER);
			}

		}


	}
}
