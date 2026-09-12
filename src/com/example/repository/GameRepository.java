package com.example.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.database.DatabaseConnection;
import com.example.model.Game;

public class GameRepository {

    Game currentGame;
    DatabaseConnection databaseConnection = new DatabaseConnection();

    public void saveGameToDB(Game game) throws SQLException {

        String sql ="""
                INSERT INTO game
                (gameId, status, numberToGuess, difficulty)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, game.getGameId());
                statement.setInt(2, game.getNumberToGuess());
                statement.setBoolean(3, game.isFinished());
                statement.setString(4, game.getDifficultyLevel().name());

                statement.executeUpdate();
             }
            
    }

        public List<Game> getGamesFromDB() throws SQLException {

            ArrayList<Game> games = new ArrayList<>();

            String sql = "SELECT * FROM game";

            try (Connection connection = databaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                    ResultSet resultSet =statement.executeQuery();
                    while(resultSet.next()){
                        int gameId = resultSet.getInt("gameId");
                        int numberToGuess = resultSet.getInt("numberToGuess");
                        boolean isFinished = resultSet.getBoolean("status");
                        String difficultyLevel = resultSet.getString("difficulty");

                        Game game = new Game(gameId, numberToGuess, isFinished, Game.DifficultyLevel.valueOf(difficultyLevel), 0);
                        games.add(game);
                    }
                    statement.close();
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                 }
            return games;
        }

}
