package src.com.example.numberGuessingGame.model;

public class Game {
    private final int gameId;
    private int numberToGuess;
    private int numberOfAttempts;
    private boolean finished;
    private String difficultyLevel;

    public Game(int gameId, int numberToGuess, String difficultyLevel) {
        this.gameId = gameId;
        this.numberToGuess = numberToGuess;
        this.numberOfAttempts = 0;
        this.finished = false;
        this.difficultyLevel = difficultyLevel;
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

    public String getDifficultyLevel() {
        return difficultyLevel;
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
