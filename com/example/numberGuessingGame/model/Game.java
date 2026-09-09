package src.com.example.numberGuessingGame.model;

public class Game {
    private int gameId;
    private int numberToGuess;
    private int numberOfAttempts;
    private boolean finished;

    public Game(int gameId, int numberToGuess) {
        this.gameId = gameId;
        this.numberToGuess = numberToGuess;
        this.numberOfAttempts = 0;
        this.finished = false;
    }

    public int getGameId() {
        return gameId;
    }

    public int getNumberToGuess() {
        return numberToGuess;
    }

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public boolean isFinished() {
        return finished;
    }

    public void incrementAttempts() {
        numberOfAttempts++;
    }

    public void finishedGame() {
        finished  = true;
    }
}
