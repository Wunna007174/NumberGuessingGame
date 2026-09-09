package src.com.example.numberGuessingGame.service;

import java.util.Random;

import src.com.example.numberGuessingGame.model.Game;
import src.com.example.numberGuessingGame.repository.GameRepository;

public class GameService {
    private final GameRepository gameRepository;
    private final Random random;
    private Game game;

    private int gameIdCounter = 1;

    public GameService() { 
        gameRepository = new GameRepository(); 
        random = new Random();
    }

    public void startNewGame(){
        int numberToGuess = random.nextInt(100) + 1;
        game = new Game(gameIdCounter, numberToGuess);
        gameRepository.saveGame(game);
        gameIdCounter++;
    }

    public String makeGuess(int guess) {
        Game game = gameRepository.findCurrentGame();
        if (game == null) {
            return "No game in progress. Please start a new game.";
        }
        game.incrementAttempts();

        if (guess < game.getNumberToGuess()) {
            return "Your guess is too low.";
        } else if (guess > game.getNumberToGuess()) {
            return "Your guess is too high.";
        } else {
            game.finishedGame();
            return "Congratulations! You've guessed the number in " + game.getNumberOfAttempts() + " attempts.";
        }
    }

    public void showGames() {
        gameRepository.showGames();
    }


}
