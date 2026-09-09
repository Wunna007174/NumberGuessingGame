package src.com.example.numberGuessingGame.repository;

import java.util.ArrayList;
import src.com.example.numberGuessingGame.model.Game;

public class GameRepository {

    private ArrayList<Game> games = new ArrayList<>();
    Game currentGame;

    public void saveGame(Game game) {
        games.add(game);
    }

    public Game findCurrentGame() {
        currentGame = games.get(games.size() - 1);
        return currentGame;
    }

    public void showGames() {
        for (Game game : games) {
            System.out.println("Game ID: " + game.getGameId() + 
                            ", Finished: " + game.isFinished() +
                            ", Correct Number: " + game.getNumberToGuess() +
                            ", Attempts: " + game.getNumberOfAttempts());
        }
    }
}
