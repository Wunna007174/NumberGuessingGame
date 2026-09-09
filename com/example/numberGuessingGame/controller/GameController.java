package src.com.example.numberGuessingGame.controller;

import java.util.Scanner;

import src.com.example.numberGuessingGame.service.GameService;

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
        String difficultyLevel = difficultyLevelSelection();
        if (difficultyLevel == "Easy") {
            System.out.println("You have selected Easy difficulty. Guess a number between 1 and 50.");
        } else if (difficultyLevel == "Medium") {
            System.out.println("You have selected Medium difficulty. Guess a number between 1 and 100.");
        } else if (difficultyLevel == "Hard") {
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
         gameService.showGames();
    }

    private String difficultyLevelSelection() {
        System.out.println("Select difficulty level: ");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.print("Enter your choice (1-3): ");

        int choice = scan.nextInt();
        scan.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                return "Easy";
            case 2:
                return "Medium";
            case 3:
                return "Hard";
            default:
                System.out.println("Invalid choice. Defaulting to Easy.");
                return "Easy";
        }
    }
}
