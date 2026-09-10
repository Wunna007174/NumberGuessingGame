package src.main.com.example.numberGuessingGame.model;

public class Game {
    private final int gameId;
    private int numberToGuess;
    private int numberOfAttempts;
    private boolean finished;
    private DifficultyLevel difficultyLevel;
    private int maxAttempts;

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
        maxAttempts = 0;
    }

    public Game(int gameId, int numberToGuess, DifficultyLevel difficultyLevel, int maxAttempts) {
        this.gameId = gameId;
        this.numberToGuess = numberToGuess;
        this.numberOfAttempts = 0;
        this.finished = false;
        this.difficultyLevel = difficultyLevel;
        this.maxAttempts = maxAttempts;
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

    public int getMaxAttempts() {
        return maxAttempts;
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
