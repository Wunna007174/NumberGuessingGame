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
        gameService.startNewGame();
        System.out.println("A new game has started! Try to guess the number between 1 and 100.");

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
}
