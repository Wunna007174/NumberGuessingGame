package src.com.example.numberGuessingGame.controller;

import java.util.Scanner;
import java.util.ArrayList;

import src.com.example.numberGuessingGame.service.GameService;
import src.com.example.numberGuessingGame.model.Game;

public class GameController {
    private final GameService gameService;
    private final Scanner scan;

    public GameController() {
        gameService = new GameService();
        scan = new Scanner(System.in);
    }

    public void start() {
        System.out.println("================================");
        System.out.println("        Number Guessing Game");
        System.out.println("================================");

        boolean playAgain = true;

        while (playAgain) { 
            playGame(); 
            System.out.print("\nDo you want to play again? (y/n): "); 
            String answer = scan.nextLine(); 
            if (!answer.equalsIgnoreCase("y")) { 
                playAgain = false; 
            }
        }

        showHistory();

        System.out.println("\nThank you for playing! Goodbye.");

        scan.close();
    }

    public void playGame() {
        Game.DifficultyLevel difficultyLevel = difficultyLevelSelection();
        if (difficultyLevel == Game.DifficultyLevel.EASY) {
            System.out.println("You have selected Easy difficulty. Guess a number between 1 and 50.");
        } else if (difficultyLevel == Game.DifficultyLevel.MEDIUM) {
            System.out.println("You have selected Medium difficulty. Guess a number between 1 and 100.");
        } else if (difficultyLevel == Game.DifficultyLevel.HARD) {
            System.out.println("You have selected Hard difficulty. Guess a number between 1 and 200.");
        }

        gameService.startNewGame(difficultyLevel);

        while (true) {
            System.out.print("Enter your guess: ");
            int guess = scan.nextInt();
            scan.nextLine(); // Consume newline

            String result = gameService.makeGuess(guess);
            System.out.println(result);

            if (result.startsWith("Congratulations")) {
                break;
            }
    }
    }

    private void showHistory() {
         System.out.println("\n================================"); 
         System.out.println(" GAME HISTORY"); 
         System.out.println("================================");
            ArrayList<Game> games = gameService.getGames();
            for (Game game : games) {
                System.out.println("Game ID: " + game.getGameId() + 
                                ", Finished: " + game.isFinished() +
                                ", Correct Number: " + game.getNumberToGuess() +
                                ", Attempts: " + game.getNumberOfAttempts() +
                                ", Difficulty Level: " + game.getDifficultyLevel());
            }
    }

    private Game.DifficultyLevel difficultyLevelSelection() {
        System.out.println("Select difficulty level: ");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Enter your choice (1-3): ");

        int choice = scan.nextInt();
        scan.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                return Game.DifficultyLevel.EASY;
            case 2:
                return Game.DifficultyLevel.MEDIUM;
            case 3:
                return Game.DifficultyLevel.HARD;
            default:
                System.out.println("Invalid choice. Defaulting to Easy.");
                return Game.DifficultyLevel.EASY;
        }
    }
}
