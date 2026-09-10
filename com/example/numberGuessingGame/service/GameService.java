package src.com.example.numberGuessingGame.service;

import java.util.ArrayList;
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

    public void startNewGame(Game.DifficultyLevel difficultyLevel) {
        int numberToGuess;
            if (difficultyLevel == Game.DifficultyLevel.EASY || difficultyLevel == null) {
                numberToGuess = random.nextInt(50) + 1;
            } else if (difficultyLevel == Game.DifficultyLevel.MEDIUM) {
                numberToGuess = random.nextInt(100) + 1;
            } else if (difficultyLevel == Game.DifficultyLevel.HARD) {
                numberToGuess = random.nextInt(200) + 1;
            } else {
                throw new IllegalArgumentException("Invalid difficulty level: " + difficultyLevel);
            }
        game = new Game(gameIdCounter, numberToGuess, difficultyLevel);
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

    public ArrayList<Game> getGames() {
        return gameRepository.getGames();
    }
}
