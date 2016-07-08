package ortona.games.card_games;

import org.slf4j.Logger;

/**
 * General excpetion for a card game that prints all exceptions in the log file
 * 
 * @author ortona
 *
 */
@SuppressWarnings("serial")
public class CardGameException extends RuntimeException {

	public CardGameException(final String message, final Logger logger) {
		super(message);
		if (logger != null) {
			logger.error(message);
		}
	}

	public CardGameException(final String message, final Throwable cause, 
			final Logger logger) {
		super(message, cause);
		if (logger != null) {
			logger.error(message);
		}
	}

	public CardGameException(final String message, final Throwable cause) {
		super(message, cause);
	}
}


