package src.main.com.example.numberGuessingGame.service;

import java.util.ArrayList;
import java.util.Random;

import src.main.com.example.numberGuessingGame.model.Game;
import src.main.com.example.numberGuessingGame.repository.GameRepository;

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
        int maxAttempts = 0;
        int numberToGuess = 0;
            if (difficultyLevel == Game.DifficultyLevel.EASY || difficultyLevel == null) {
                numberToGuess = random.nextInt(50) + 1;
                maxAttempts = 10;
            } else if (difficultyLevel == Game.DifficultyLevel.MEDIUM) {
                numberToGuess = random.nextInt(100) + 1;
                maxAttempts = 8;
            } else if (difficultyLevel == Game.DifficultyLevel.HARD) {
                numberToGuess = random.nextInt(200) + 1;
                maxAttempts = 6;
            } else {
                throw new IllegalArgumentException("Invalid difficulty level: " + difficultyLevel);
            }
        game = new Game(gameIdCounter, numberToGuess, difficultyLevel, maxAttempts);
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

    public Game getCurrentGame() {
        return gameRepository.findCurrentGame();
    }

    public int RemainAttempts() {
        if (game == null) {
            return 0;
        }
        return Math.max(0, game.getMaxAttempts() - game.getNumberOfAttempts());
    }

    public ArrayList<Game> getGames() {
        return gameRepository.getGames();
    }

    public void ResultDecision(){
        if (game != null) {
            game.finishedGame();
        }
    }

}
