package com.example;

import java.sql.SQLException;

import com.example.controller.GameController;

public class Application {
    public static void main(String[] args) throws SQLException {
        GameController gameController = new GameController();
        gameController.start();
    }
}