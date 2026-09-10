package src.main.com.example.numberGuessingGame;

import src.main.com.example.numberGuessingGame.controller.GameController;

public class Application {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.start();
    }
}