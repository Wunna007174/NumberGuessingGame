package com.example.service;

import java.util.ArrayList;
import java.util.Random;

import com.example.model.Game;
import com.example.repository.GameRepository;

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
        gameIdCounter++;
    }

    public String makeGuess(int guess) {
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

    public int RemainAttempts() {
        if (game == null) {
            return 0;
        }
        return Math.max(0, game.getMaxAttempts() - game.getNumberOfAttempts());
    }

    public void ResultDecision(){
        if (game != null) {
            game.finishedGame();
        }
    }

    public ArrayList<Game> getAllGames() {
        try {
            return new ArrayList<>(gameRepository.getGamesFromDB());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Game getCurrentGame() {
        return game;
    }

    public void saveCurrentGame() {
        if (game != null) {
            try {
                gameRepository.saveGameToDB(game);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
