package src.com.example.numberGuessingGame.model;

public class Game {
    private final int gameId;
    private int numberToGuess;
    private int numberOfAttempts;
    private boolean finished;
    private DifficultyLevel difficultyLevel;

    public enum DifficultyLevel {
        EASY,
        MEDIUM,
        HARD
    }

    public Game() {
        this.gameId = 0;
        this.numberToGuess = 0;
        this.numberOfAttempts = 0;
        this.finished = false;
        this.difficultyLevel = DifficultyLevel.EASY;
    }

    public Game(int gameId, int numberToGuess, DifficultyLevel difficultyLevel) {
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

    public DifficultyLevel getDifficultyLevel() {
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
